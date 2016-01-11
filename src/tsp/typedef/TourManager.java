package tsp.typedef;

import tsp.DistanceMatrix;

public abstract class TourManager {
    
    static public Tour nodesNotInTour(Tour tour, DistanceMatrix dm){
        
        // Get all nodes in tour.
        Tour allNodes = new Tour();
        for(int i = 0; i < dm.width(); i++) allNodes.add(i);
        
        // Discard those not in tour.
        Tour notInTour = new Tour(allNodes);
        for (int i = 0; i < tour.size(); i++) notInTour.remove(i);
        // Tour notInTour = CollectionUtils.substract(allNodes, tour);
        
        return notInTour;
    }
    
    /**
     * @param tour
     * @param dm
     * @return True if all of the graph nodes appear at least once in the tour.
     */
    static public boolean areAllNodesInTour(Tour tour, DistanceMatrix dm){
        for (int i = 0; i < dm.width(); i++){
            if (!tour.contains(i)) return false;
        }
        return true;
    }
    

}
