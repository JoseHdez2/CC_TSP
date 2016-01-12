package typedef;

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
    
    /**
     * @return The number of columns of this matrix.
     */
    public int width(){
        return distances.length;
    }
    
    /**
     * @return The number of rows of this matrix.
     */
    public int height(){
        return distances[0].length;
    }
    
    public String toString(){
        String str = "";
        
        for (int j = 0; j < this.height(); j++){
            for (int i = 0; i < this.width(); i++){
                str += String.format("%5.5f ", this.get(i,j));
            }
            str += "\n";
        }
        
        return str;
    }
}
