package tsp;

public class DistanceMatrix {
    double[][] distances;
    
    /**
     * @param grade Number of nodes.
     */
    public DistanceMatrix(int grade){
        this.distances = new double[grade][grade];
    }
    
    public double get(int i, int j){
        return distances[i][j];
    }
    
    public void set(int i, int j, double dist){
        distances[i][j] = dist;
    }
}
