package tsp.typedef;

import java.util.ArrayList;

import tsp.DistanceMatrix;

public class NodeArray extends ArrayList<Integer>{
    ArrayList<Double> costs = new ArrayList<Double>();
    
    public NodeArray(){
        super();
    }
    
    /** Copy constructor */
    public NodeArray(NodeArray other){
        super();
        this.addAll(other);
        costs.addAll(other.costs);
    }
    
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
    
    static public NodeArray nodesNotInTour(NodeArray nodeArray, DistanceMatrix dm){
        
        // Get all nodes in tour.
        NodeArray allNodes = new NodeArray();
        for(int i = 0; i < dm.width(); i++) allNodes.add(i);
        
        // Discard those not in tour.
        NodeArray notInTour = new NodeArray(allNodes);
        for (int i = 0; i < nodeArray.size(); i++) notInTour.remove(i);
        // Tour notInTour = CollectionUtils.substract(allNodes, tour);
        
        return notInTour;
    }
}
