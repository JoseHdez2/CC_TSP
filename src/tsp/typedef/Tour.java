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
    
    public int getLastNode(){
        return this.get(this.size()-1);
    }
    
    /**
     * @return  True if last node is same as first and there is more than one node.
     */
    public boolean isClosedTour(){
        // Return false if tour has less than two stops.
        if (this.size() < 2) return false;
        // Return false if first and last nodes don't match.
        if (this.getLastNode() != this.get(0)) return false;
        return true;
    }
}
