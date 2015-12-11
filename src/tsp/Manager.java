package tsp;

import tsp.typedef.Tour;

public abstract class Manager {
    /**
     * @param tour
     * @param dm
     * @return True if all of the graph nodes appear at least once in the tour.
     */
    static public boolean allNodesInTour(Tour tour, DistanceMatrix dm){
        for (int i = 0; i < dm.width(); i++){
            if (!tour.contains(i)) return false;
        }
        return true;
    }
    
    /**
     * Find new (not in tour) node that is the closest to the provided node (excluding the provided node).
     * NOTE: Returns null if no new nodes are available.
     * @param tour
     * @param pivotNode Provided node against which 
     * @param dm
     * @return Index of node closest to pivotNode.
     */
    static public Integer closestNodeNotInTour(Tour tour, int pivotNode, DistanceMatrix dm){
        Integer closestNewNode = null;
        double closestNewNodeDistance = Double.MAX_VALUE;
        
     // Find new (not in tour) node that is the closest to the provided node.
        for (int i = 0; i < dm.width(); i++){
            if(tour.contains(i)) continue;  // Skip nodes already in the tour.
            if(i == pivotNode) continue;   // Skip comparisons with itself.
            
            // If this new node is closer, set as closest new node.
            Double dist = dm.get(pivotNode, i);
            if (dist < closestNewNodeDistance){
                closestNewNode = i;
                closestNewNodeDistance = dist;
            }
        }
        
        return closestNewNode;
    }
}
