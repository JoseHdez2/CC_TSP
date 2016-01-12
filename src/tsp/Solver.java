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
        NodeArray itCopy = new NodeArray(it);
        
        while(!hasAllNodes(itCopy)){
            addNodeClosestToAny(itCopy);
        }
        
        return itCopy.getTotalCost();
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
                String str = String.format("Checking %s (prim=%f) ...", csb, csbh);
                
                // if sub-branch heuristic exceeds top bound, discard.
                if(csbh >= bestTourVal){
                    str += String.format(" >= %f (best); branch discarded", bestTourVal); Sys.out(str);
                    subBranches.remove(i); i--; continue;
                }
                
                // if sub-branch is a complete tour: set as new best if applicable, then discard.
                else if(hasAllNodes(csb)){
                    str += String.format("has all nodes...");
                    // TODO: remove this line if prim is supposed to go back home.
                    csb.addNode(csb.getLastNode(), dm.get(csb.getLastNode(), FST_NODE));
                    if(csb.getTotalCost() < bestTourVal){
                        str += String.format("%f (cur) < %f (best); new best tour!", bestTourVal); 
                        str += "-----------------------------------------------"; Sys.out(str);
                        bestTour = csb; bestTourVal = csb.getTotalCost();
                    }
                    subBranches.remove(i); i--; continue;
                } else {
                    str += String.format("< %f (best); valid branch.", bestTourVal); Sys.out(str);
                }
                
                // otherwise, just leave the sub-branch alone.
            }
            // Mark remaining sub-branches as valid branches, to continue a new iteration/expansion.
            validBranches.addAll(subBranches);
        }
        
        return bestTour;
    }
    
    /**
     * Extra step: optimize solution with 2-opt
     */
    public NodeArray twoOpt(NodeArray na){
        NodeArray nna = new NodeArray(na); 
        Sys.fout("%s [dist = %f] ",nna,nna.getTotalCost());
        boolean improvement;
        do {
            improvement = false;
            double bestDist = nna.getTotalCost();
            
            for (int i = 1; i < nna.size()-3; i++){
                for (int k = i+1; k < nna.size()-2; k++){
                    NodeArray swapped = twoOptSwap(nna, i, k);
                    double newDist = swapped.getTotalCost();
                    
                    if (newDist < bestDist){
                        nna = swapped;
                        improvement = true;
                        Sys.fout("(i=%d,k=%d) %s [dist = %f] (mejora)",i,k,swapped,newDist);
                    } else {
                        Sys.fout("(i=%d,k=%d) %s [dist = %f] (no mejora)",i,k,swapped,newDist);
                    }
                    if(improvement) break;
                }
                if(improvement) break;
            }
            
        } while(improvement);
        
        return nna;
    }
    
    public NodeArray solve(){
        return branchAndBound();
    }
    
    public NodeArray optimize(NodeArray na){
        return twoOpt(na);
    }
}
