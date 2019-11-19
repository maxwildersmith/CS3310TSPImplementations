package TSP;

public class Connection {
    private int[] cities;
    private double distance;

    public Connection(int cityA, int cityB, double d){
        cities = new int[]{cityA,cityB};
        distance = d;
    }
}
