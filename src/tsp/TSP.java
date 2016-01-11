package tsp;

import java.io.File;

import tsp.algo.Prim;
import tsp.typedef.NodeArray;
import tsp.typedef.NodeArray;
import util.Sys;

public class TSP {
    
    ProblemInstance prob;
    NodeArray bestTour;
    double bestValue4bestTour;
    
    public TSP(String inputFilePath) throws Exception{
        loadInput(inputFilePath);
    }
    
    public void loadInput(String inputFilePath) throws Exception{
        prob = new ProblemInstance(new File(inputFilePath));
        bestTour = new NodeArray();
        bestValue4bestTour = Math.pow(1, 100);
        Sys.out("Problem loaded.");
    }
    
    public void doThing() throws Exception{
        NodeArray t = NN_Algorithm();
        Sys.outLn(t, t.getTotalCost());
        NodeArray p = new NodeArray();
        p.add(0);
        double primResult = Prim.PrimHeuristic(p, prob.getDistanceMatrix());
        Sys.out("Prim Result = ", primResult);
        Sys.out(p);
    }
    
    /**
     * Construction algorithm
     */
    protected NodeArray NN_Algorithm(){
        DistanceMatrix dm = prob.getDistanceMatrix();
        NodeArray initialFeasibleTour = new NodeArray();
        
        int FIRST_NODE = 0;
        initialFeasibleTour.addNode(FIRST_NODE, 0.0);
        
        Integer latestNode = FIRST_NODE;
        
        // Keep adding nodes until the tour is closed.
        while (!initialFeasibleTour.isClosedTour()){
            Integer closestNewNode = null;
            double closestNewNodeDistance = Double.MAX_VALUE;
            
            // Find new (not in tour) node that is the closest to the latest node added into the tour.
            closestNewNode = TourManager.closestNodeNotInTour(initialFeasibleTour, initialFeasibleTour.getLastNode(), dm);
            
            // true means we exhausted all new nodes.
            // So we "forcibly" close the loop; the first node need not be neither close (and is not new).
            if (closestNewNode == null){
                closestNewNode = FIRST_NODE;
                closestNewNodeDistance = dm.get(latestNode, FIRST_NODE);
            } else {
                closestNewNodeDistance = dm.get(initialFeasibleTour.getLastNode(), closestNewNode);
            }
            
            Sys.fout("closestNode:%s, closestNewNodeDistance:%s", closestNewNode, closestNewNodeDistance);
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
