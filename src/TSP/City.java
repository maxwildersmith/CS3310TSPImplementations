package TSP;

import java.util.ArrayList;

public class City {
    private ArrayList<Connection> connections;
    private String name;

    public City(String name){
        this.name = name;
        connections = new ArrayList<>();
    }

    public void addConnection(City c, double distance){
        connections.add(new Connection(c,distance));
    }

    public Connection[] getConenctions(){
        return connections.toArray(Connection[]::new);
    }

    public String getName(){
        return name;
    }
}
