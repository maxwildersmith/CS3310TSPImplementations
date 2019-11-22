package TSP;

import java.util.Arrays;

public class TSP {
    private String[] cities;
    private double[][] distances;

    public TSP(String[] cities, double[][] distances){
        this.cities = cities;
        this.distances = distances;
    }

    public void printDistances(){
        for(double[] row: distances)
            System.out.println(Arrays.toString(row));
    }

    public int getSize(){
        return cities.length;
    }

    public double getDistance(int startCity, int endCity){
        return distances[startCity][endCity];
    }

    public String pathToNames(int[] path){
        String out = "";
        for(int i: path){
            out+=", "+cities[i];
        }
        return out.substring(2);
    }

    public void printCities(){
        System.out.println("Cities: ");
        for(int i=0;i<cities.length;i++)
            System.out.println("\t"+i+". "+cities[i]);
    }
}
