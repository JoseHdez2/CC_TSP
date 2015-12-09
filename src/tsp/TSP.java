package tsp;

import java.io.File;

import tsp.typedef.Tour;

public class TSP {
    
    ProblemInstance prob;
    Tour bestTour;
    double bestValue4bestTour;
    
    public TSP(String inputFilePath) throws Exception{
        loadInput(inputFilePath);
    }
    
    public void loadInput(String inputFilePath) throws Exception{
        prob = new ProblemInstance(new File(inputFilePath));
        bestTour = new Tour();
        bestValue4bestTour = Math.pow(1, 100);
        System.out.println("Tudo bem");
        System.out.println(NN_Algorithm());
    }
    
    /**
     * Construction algorithm
     */
    protected Tour NN_Algorithm(){
        DistanceMatrix dm = prob.getDistanceMatrix();
        
        int FIRST_NODE = 0;
        bestTour.addNode(FIRST_NODE, 0.0);
        
        Integer latestNode = FIRST_NODE;
        
        // Stop when we reach the first node (for the second time!)
        while (latestNode != FIRST_NODE && bestTour.size() > 1 || bestTour.size() == 1){
            Integer closestNewNode = null;
            double closestNewNodeDistance = Double.MAX_VALUE;
            
            // Find new (not in tour) node that is the closest to the latest node in the tour.
            for (int i = 0; i < dm.width(); i++){
                if(bestTour.contains(i)) continue;  // Skip nodes already in the tour.
                
                // If this new node is closer, set as closest new node.
                Double dist = dm.get(latestNode, i);
                if (dist < closestNewNodeDistance){
                    closestNewNode = i;
                    closestNewNodeDistance = dist;
                }
            }
            
            // Means we exhausted all new nodes.
            // We "forcibly" close the loop; the first node need not be neither close nor new.
            if (closestNewNode == null){
                closestNewNode = FIRST_NODE;
                closestNewNodeDistance = dm.get(latestNode, FIRST_NODE);
            }
            
            bestTour.addNode(closestNewNode, closestNewNodeDistance);
            
            // We set the recently added node as the latest node in the tour, for a new iteration.
            latestNode = closestNewNode;
        }
        
        return bestTour;
    }
    
    /**
     * Refinement algorithm
     */
    protected void _2_OPT(){
        
    }
    
    public String toString(){
        return prob.toString();
    }
}
