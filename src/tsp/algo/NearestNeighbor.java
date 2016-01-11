package tsp.algo;

import tsp.DistanceMatrix;
import tsp.typedef.Tour;
import tsp.typedef.TourManager;
import util.Sys;

public abstract class NearestNeighbor {

    static public Tour constructionAlgorithm(DistanceMatrix dm){
        Tour initialTour = new Tour();
        
        initialTour.addNode(0, 0.0);
        
        // Keep adding nodes until the tour is closed.
        while (!initialTour.isClosedTour()){
            Integer closestNewNode = null;
            double closestNewNodeDistance = Double.MAX_VALUE;
            
            // Find new (not in tour) node that is the closest to the latest node added into the tour.
            closestNewNode = TourManager.closestNodeNotInTour(initialTour, initialTour.getLastNode(), dm);
            
            // true means we exhausted all new nodes.
            // So we "forcibly" close the loop; the first node need not be neither close (and is not new).
            if (closestNewNode == null){
                closestNewNode = FIRST_NODE;
                closestNewNodeDistance = dm.get(, FIRST_NODE);
            } else {
                closestNewNodeDistance = dm.get(initialTour.getLastNode(), closestNewNode);
            }
            
            Sys.fout("closestNode:%s, closestNewNodeDistance:%s", closestNewNode, closestNewNodeDistance);
            initialTour.addNode(closestNewNode, closestNewNodeDistance);
        }
        
        return initialTour;
    }
}
