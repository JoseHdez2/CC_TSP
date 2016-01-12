package tsp;

import java.util.ArrayList;
import java.util.Collections;

import input.Sys;
import typedef.DistanceMatrix;
import typedef.NodeArray;

/**
 * @author jose
 * 
 *  Class holding all the algorithms for solving a TSP.
 */
public class Solver extends SolverHelper {
    
    final Integer FST_NODE = 0; // Arbitrary first node for solutions.
    
    public Solver(DistanceMatrix dm){
        super(dm);
    }
    
    /**
     * Step 1: Nearest Neighbor algorithm for getting an initial feasible solution.
     */
    NodeArray nearestNeighbor(){
        NodeArray ift = new NodeArray(); // Initial feasible tour
        ift.addNode(FST_NODE, 0d);
        
        while(!hasAllNodes(ift)){
            addNodeClosestToLast(ift);
        }
        ift.addNode(FST_NODE, dm.get(ift.getLastNode(), FST_NODE)); // go back to start
        
        return ift;
    }
    
    /**
     * Step 2: Prim heuristic for getting low bounds.
     */
    Double primHeuristic(NodeArray it){
        // it = incomplete tour
        
        while(!hasAllNodes(it)){
            addNodeClosestToAny(it);
        }
        
        return it.getTotalCost();
    }
    
    /**
     * Last step: Branch and bound
     */
    NodeArray branchAndBound(){
        NodeArray bestTour = nearestNeighbor(); // Initial feasible tour
        Double bestTourVal = bestTour.getTotalCost(); 

        Integer[] ib = {FST_NODE}; // initial branch: what we branch off from to find optimal solution.
        
        // valid branches: those that keep below the top bound
        ArrayList<NodeArray> validBranches = new ArrayList<NodeArray>();
        validBranches.add(createTour(ib));
        
        while(!validBranches.isEmpty()){
            // Sort valid branches from best to worst heuristic value.
            Collections.sort(validBranches);
            // Take best branch (smallest value) and expand it into sub-branches.
            NodeArray bestBranch = validBranches.remove(0); // Smallest heuristic was placed first.
            Sys.fout("Expanding branch %s", bestBranch);
            
            // Work with each sub-branch of the newly expanded branch.
            ArrayList<NodeArray> subBranches = expandBranch(bestBranch);
            for (int i = 0; i < subBranches.size(); i++){
                NodeArray csb = subBranches.get(i);  // csb = current sub-branch
                Double csbh = primHeuristic(csb);  // csbh = csb heuristic value
                
                // if sub-branch heuristic exceeds top bound, discard.
                if(csbh >= bestTourVal){
                    subBranches.remove(i); i--; continue;
                }
                
                // if sub-branch is a complete tour: set as new best if applicable, then discard.
                if(hasAllNodes(csb)){
                    // TODO: remove this line if prim is supposed to go back home.
                    csb.addNode(csb.getLastNode(), dm.get(csb.getLastNode(), FST_NODE));
                    if(csb.getTotalCost() < bestTourVal){
                        bestTour = csb; bestTourVal = csb.getTotalCost();
                    }
                    subBranches.remove(i);
                }
                
                // otherwise, just leave the sub-branch alone.
            }
            // Mark remaining sub-branches as valid branches, to continue a new iteration/expansion.
            validBranches.addAll(subBranches);
        }
        
        return bestTour;
    }
    
    public NodeArray solve(){
        return branchAndBound();
    }
}
//TODO: if sub-branch (or complete tour?) has a twist, apply 2-opt
