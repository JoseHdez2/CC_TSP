package main;

import java.util.ArrayList;

import tsp.TSP;

public class Main {
    
    public static void doSome(ArrayList<Integer> a){
        a.add(3);
    }
    
    public static void main(String[] args) {
        
        ArrayList<Integer> a = new ArrayList<Integer>();
        System.out.println(a);
        doSome(a);
        System.out.println(a);
        /*
        try{
            System.out.println("Traveling Salesman Problem");
//            TSP tsp = new TSP("samples/a280.xml");
            TSP tsp = new TSP("samples/dummy.xml");
            tsp.doThing();
            
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }*/
    }
}
