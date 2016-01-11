package tsp;

import tsp.typedef.NodeArray;

/**
 * @author jose
 * 
 *  Class holding all the algorithms for solving a TSP.
 */
public class Solver extends SolverHelper {
    
    Solver(DistanceMatrix dm){
        super(dm);
    }
    
    /**
     * Step 1: Nearest Neighbor algorithm for getting an initial feasible solution.
     */
    NodeArray nearestNeighbor(){
        NodeArray ift = new NodeArray(); // Initial feasible tour
        
        while(!hasAllNodes(ift)){
            
        }
        
        return ift;
    }

}
