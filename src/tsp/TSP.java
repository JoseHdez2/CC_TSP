package tsp;

import java.io.File;

import tsp.typedef.Tour;
import util.Sys;

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
        Tour t = NN_Algorithm();
        Sys.outLn(t, t.getTotalCost());
        Tour p = new Tour();
        p.add(0);
        double optimistic = Prim.PrimHeuristic(p, prob.getDistanceMatrix());
        System.out.println(optimistic);
    }
    
    /**
     * Construction algorithm
     */
    protected Tour NN_Algorithm(){
        DistanceMatrix dm = prob.getDistanceMatrix();
        Tour initialFeasibleTour = new Tour();
        
        int FIRST_NODE = 0;
        initialFeasibleTour.addNode(FIRST_NODE, 0.0);
        
        Integer latestNode = FIRST_NODE;
        
        // Keep adding nodes until the tour is closed.
        while (!initialFeasibleTour.isClosedTour()){
            Integer closestNewNode = null;
            double closestNewNodeDistance = Double.MAX_VALUE;
            
            // Find new (not in tour) node that is the closest to the latest node added into the tour.
            closestNewNode = Manager.closestNodeNotInTour(initialFeasibleTour, initialFeasibleTour.getLastNode(), dm);
            
            // true means we exhausted all new nodes.
            // So we "forcibly" close the loop; the first node need not be neither close (and is not new).
            if (closestNewNode == null){
                closestNewNode = FIRST_NODE;
                closestNewNodeDistance = dm.get(latestNode, FIRST_NODE);
            }
            
            initialFeasibleTour.addNode(closestNewNode, closestNewNodeDistance);
            
            // We set the recently added node as the latest node in the tour, for a new iteration.
            latestNode = closestNewNode;
        }
        
        return initialFeasibleTour;
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
