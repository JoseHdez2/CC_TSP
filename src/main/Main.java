package main;

import tsp.TSP;

public class Main {
    public static void main(String[] args) {
        try{
            System.out.println("Traveling Salesman Problem");
            TSP tsp = new TSP("samples/a280.xml");
            tsp.doThing();
            
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
