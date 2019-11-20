package TSP;

import java.util.ArrayList;
import java.util.Arrays;

public class Connection {
    private int[] cities;
    private double distance;

    public Connection(int cityA, int cityB, double d){
        cities = new int[]{cityA,cityB};
        distance = d;
    }

    public double getDistance() {
        return distance;
    }

    public int[] getCities() {
        return cities;
    }

    public double getTotalDistance(ArrayList<Connection> path){
        double total = 0;
        for(Connection c: path)
            total+=c.distance;
        return total;
    }

    @Override
    public String toString() {
        return "City A: "+cities[0]+" City B: "+cities[1]+" Distance: "+distance;
    }
}
