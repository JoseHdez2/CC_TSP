package tsp;

import java.util.ArrayList;
import java.util.Collections;

import tsp.typedef.NodeArray;

/**
 * @author jose
 * 
 *  Class holding all the algorithms for solving a TSP.
 */
public class Solver extends SolverHelper {
    
    final Integer FST_NODE = 0; // Arbitrary first node for solutions.
    
    Solver(DistanceMatrix dm){
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

        Integer[] ib = {FST_NODE}; // initial branch: what we branch off from to find optimal solution.
        ArrayList<NodeArray> validBranches = new ArrayList<NodeArray>();
        validBranches.add(createTour(ib));
        
        while(!validBranches.isEmpty()){
            Collections.sort(validBranches);
            NodeArray bestBranch = validBranches.remove(0);
            ArrayList<NodeArray> subBranches = expandBranch(bestBranch);
            for (int i = 0; i < subBranches.size(); i++){
                if()
            }
        }
    }
}
