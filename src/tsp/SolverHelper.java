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
    
    protected addNode(NodeArray na, Integer node){
        na.addNode(node, dm.get(na.getLastNode(),0));
        return na;
    }
    
    protected boolean hasAllNodes(NodeArray na){
        for (int i = 0; i < dm.width(); i++){
            if (!na.contains(i)) return false;
        }
        return true;
    }
    
}
