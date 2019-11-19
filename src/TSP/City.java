package TSP;

import java.util.ArrayList;

public class City {
    private ArrayList<Connection> connections;
    private String name;

    public City(String name){
        this.name = name;
        connections = new ArrayList<>();
    }

    public void addConnection(int index1, int index2, double distance){
        connections.add(new Connection(index1, index2,distance));
    }

    public Connection[] getConenctions(){
        return connections.toArray(Connection[]::new);
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
