package tsp.typedef;

import java.util.ArrayList;

public class Tour extends ArrayList<Integer>{
    ArrayList<Double> costs = new ArrayList<Double>();
    
    public double getTotalCost(){
        Double totalCost = 0.0;
        for (Double c : costs){
            totalCost += c;
        }
        return totalCost;
    }
    
    public void addNode(int i, Double cost){
        add(i);
        costs.add(cost);
    }
}
