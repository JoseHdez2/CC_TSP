package tsp.algo;

import java.util.ArrayList;

import tsp.DistanceMatrix;
import tsp.typedef.Tour;
import tsp.typedef.TourManager;
import util.Sys;


/**
 *  Give most optimistic case (lowest possible) cost of an incomplete tour, given the distance matrix.
 */
public abstract class Prim {
    
    /**
     * Give most optimistic case (lowest possible) cost of an incomplete tour, given the distance matrix.
     * @param incompleteTour
     * @return
     * @throws Exception
     */
    static public double PrimHeuristic(Tour incompleteTour, DistanceMatrix dm) throws Exception{
        
        while (!TourManager.areAllNodesInTour(incompleteTour,dm)){
            incompleteTour = PrimStep(incompleteTour, dm);
        }
        
        return incompleteTour.getTotalCost();
    }
    
    /**
     * Find closest new node to any of the nodes and include it.
     * @param incompleteTour
     * @return
     * @throws Exception If no new nodes exist.
     */
    static protected Tour PrimStep(Tour incompleteTour, DistanceMatrix dm) throws Exception{
        
        ArrayList<Object> closestNodeResults = closestNodeNotInTour(incompleteTour, dm); 
        
        Integer closestNodeToTree = (Integer)closestNodeResults.get(0);       
        Double closestDistanceToTree = (Double)closestNodeResults.get(1);
        
        if (closestNodeToTree == null) throw new Exception("Executed Prim step yet no new nodes existed!");
        Sys.fout("Tree: %s \n adding closest new node to any node in tree: node %d with distance %f", incompleteTour, closestNodeToTree, closestDistanceToTree);
        incompleteTour.addNode(closestNodeToTree, closestDistanceToTree);
        
        return incompleteTour;
    }
    
    /**
     * Find new (not in tour) node that is the closest to any node in the tour.
     * NOTE: Returns null if no new nodes are available.
     * @param tour
     * @param pivotNode Provided node against which 
     * @param dm
     * @return ArrayList: 1st element is index of node (Integer), 2nd element is distance (Float).
     */
    static public ArrayList<Object> closestNodeNotInTour(Tour tour, DistanceMatrix dm){
        
        // Group nodes that are not in the tour.
        Tour notInTour = TourManager.nodesNotInTour(tour, dm);
        
        Integer closestNewNode = null;
        double closestNewNodeDistance = Double.MAX_VALUE;
        
        // For each node IN the tour...
        for (int i = 0; i < tour.size(); i++){
            String str = "Closest node to " + i + " (in tour)...";
            
            // Find closest node NOT in tour
            for (int j = 0; j < notInTour.size(); j++){
                
                // If this new node is closer, set as closest new node.
                if (dm.get(i, j) < closestNewNodeDistance){
                    closestNewNode = i;
                    closestNewNodeDistance = dm.get(i, j);
                }
            }
        }
        
        ArrayList<Object> results = new ArrayList<Object>();
        results.add(closestNewNode);
        results.add(closestNewNodeDistance);
        
        return results;
    }
}
