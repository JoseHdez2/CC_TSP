package tsp;

import tsp.typedef.NodeArray;

/**
 * @author jose
 *  Holds helper functions for the Solver class. Intended to help Solver code be more succinct.
 */
public class SolverHelper {

    DistanceMatrix dm;
    
    SolverHelper(DistanceMatrix dm){
        this.dm = dm;
    }
    
    // Used when adding closest unvisited node.
    protected NodeArray unvisitedNodes(NodeArray na){
        // Get all nodes in graph.
        NodeArray allNodes = new NodeArray();
        for(int i = 0; i < dm.width(); i++) allNodes.add(i);
        
        // Discard those not in tour.
        NodeArray unvisited = new NodeArray(allNodes);
        for (int i = 0; i < na.size(); i++) unvisited.remove(i);
        
        return unvisited;
    }
    
    protected Integer findClosestUnvisited(NodeArray na, Integer node){
        NodeArray unvisited = unvisitedNodes(na);
        Integer closestUnvisited = null;
        Double closestUnvisitedDist = Double.MAX_VALUE;
        for (Integer un : unvisited){
            if (dm.get(node, un) < closestUnvisitedDist){
                closestUnvisited = un;
                closestUnvisitedDist = dm.get(node, un);
            }
        }
        return closestUnvisited;
    }
    
    // Used in NN construction algorithm
    protected void addNodeClosestToLast(NodeArray na){
        Integer cu = findClosestUnvisited(na, na.getLastNode());
        na.addNode(cu, dm.get(na.getLastNode(), cu));
    }
    
    // Used for knowing when to stop.
    protected boolean hasAllNodes(NodeArray na){
        for (int i = 0; i < dm.width(); i++){
            if (!na.contains(i)) return false;
        }
        return true;
    }
    
    // Used in Prim heuristic
    protected void addNodeClosestToAny(NodeArray na){
        Integer cuOverall = null;   // "Closest unvisited overall" overall = to any node.
        Double cuOverallDist = Double.MAX_VALUE;
        for (int i = 0; i < dm.width(); i++){
            Integer cuToI = findClosestUnvisited(na, i); // "Closest unvisited to i", i is current node.
            if (dm.get(i, cuToI) < cuOverallDist){
                cuOverall = findClosestUnvisited(na, i);
                cuOverallDist = dm.get(i, cuToI);
            }
        }
        na.addNode(cuOverall, dm.get(na.getLastNode(), cuOverall));
    }
}
