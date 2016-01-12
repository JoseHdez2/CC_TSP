package main;

import java.io.File;

import input.ProblemInstance;
import input.Sys;
import tsp.Solver;
import typedef.NodeArray;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("Traveling Salesman Problem");
        
        // Load problem
        ProblemInstance prob = null;
        try{
            prob = loadInput("samples/a280.xml");
//            prob = loadInput("samples/dummy.xml");
            
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        
        // Solve problem
        Solver s = new Solver(prob.getDistanceMatrix());
        NodeArray bestTour = s.solve();
        
        // Show result
        Sys.fout("El mejor tour es %s", bestTour);
        Sys.fout("La distancia minima es %f", bestTour.getTotalCost());
    }
    
    public static ProblemInstance loadInput(String inputFilePath) throws Exception{
        ProblemInstance prob = new ProblemInstance(new File(inputFilePath));
        Sys.out("Problem loaded.");
        return prob;
    }
    

}
