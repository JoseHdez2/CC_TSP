package tsp;

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

}
