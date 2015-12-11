package tsp;

import tsp.typedef.Tour;
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
        
        while (!Manager.allNodesInTour(incompleteTour,dm)){
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
        Integer closestNodeToTree = null;
        double closestDistanceToTree = Double.MAX_VALUE;
        
        // Use each node as pivot to look for closest nodes.
        for (int i = 0; i < incompleteTour.size(); i++){
            Integer closestNodeToPivot = Manager.closestNodeNotInTour(incompleteTour, i, dm);
            double closestDistanceToPivot = 0.0;
            if (closestNodeToPivot != null){
                closestDistanceToPivot = dm.get(i, closestNodeToPivot);
            } else continue;
            if(closestDistanceToPivot < closestDistanceToTree){
                closestNodeToTree = closestNodeToPivot;
                closestDistanceToTree = closestDistanceToPivot;
            }
        }
        
        if (closestNodeToTree == null) throw new Exception("Executed Prim step yet no new nodes existed!");
        Sys.fout("add closest distance: %f", closestDistanceToTree);
        incompleteTour.addNode(closestNodeToTree, closestDistanceToTree);
        
        return incompleteTour;
    }
}
