package tsp;

import java.io.File;

public class TSP {
    
    ProblemInstance prob;
    BestTour bestour;
    double bestValue4bestTour;
    
    public void loadInput(String inputFilePath) throws Exception{
        prob = new ProblemInstance(new File(inputFilePath));
        bestour = null;
        bestValue4bestTour = Math.pow(1, 100);
        
        
    }
    
    /**
     * Construction algorithm
     */
    protected void NN_Algorithm(){
        
    }
    
    /**
     * Refinement algorithm
     */
    protected void _2_OPT(){
        
    }
}
