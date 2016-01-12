package main;

import java.io.File;

import input.Sys;
import tsp.ProblemInstance;
import tsp.Solver;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("Traveling Salesman Problem");
        
        // Load problem
        ProblemInstance prob = null;
        try{
//            prob = loadInput("samples/a280.xml");
            prob = loadInput("samples/dummy.xml");
            
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        
        // Solve problem
        Solver s = new Solver(prob.getDistanceMatrix());
        s.solve();
        
        // Show result
        
    }
    
    public static ProblemInstance loadInput(String inputFilePath) throws Exception{
        ProblemInstance prob = new ProblemInstance(new File(inputFilePath));
        Sys.out("Problem loaded.");
        return prob;
    }
    

}
