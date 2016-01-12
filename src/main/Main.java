package main;

import java.io.Console;
import java.io.File;
import java.util.Scanner;

import input.ProblemInstance;
import input.Sys;
import tsp.Solver;
import typedef.NodeArray;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("Traveling Salesman Problem");
        
        // Load problem
        ProblemInstance prob = null;

        Console console = System.console();
        try{
//            String str = console.readLine("Load file: samples/");
            System.out.print("Load file: samples/");
            Scanner keyboard = new Scanner(System.in);
            String str = keyboard.nextLine();
            prob = loadInput("samples/" + str);
//            prob = loadInput("samples/dummy.xml");
            
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        
//         System.out.println(prob.getDistanceMatrix());
        
        // Solve problem
        Solver s = new Solver(prob.getDistanceMatrix());
        NodeArray bestTour = s.solve();
        
        // Show result
        Sys.fout("El mejor tour es %s", bestTour);
        Sys.fout("La distancia minima es %f", bestTour.getTotalCost());
        
        // Optimize problem
        NodeArray optBestTour = s.optimize(bestTour);
        
        // Show result optimization
        Sys.fout("El mejor tour es %s", optBestTour);
        Sys.fout("La distancia minima es %f", optBestTour.getTotalCost());
    }
    
    public static ProblemInstance loadInput(String inputFilePath) throws Exception{
        ProblemInstance prob = new ProblemInstance(new File(inputFilePath));
        Sys.out("Problem loaded.");
        return prob;
    }
    

}
