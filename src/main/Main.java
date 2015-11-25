package main;

import java.io.File;

import tsp.ProblemInstance;

public class Main {
    public static void main(String[] args) {
        try{
            System.out.println("Traveling Salesman Problem");
            ProblemInstance pi = new ProblemInstance(new File("samples/a280.xml"));
            System.out.println(pi);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
