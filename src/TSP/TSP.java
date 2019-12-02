package TSP;

import java.awt.event.MouseAdapter;
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

    public static TSP autoGenerate(int size){
        int[] x = new int[size];
        int[] y = new int[size];
        String[] names = new String[size];
        for(int i=0;i<size;i++){
            names[i]=i+1+"";
            x[i] = (int)(Math.random()*size*2);
            y[i] = (int)(Math.random()*size*2);
        }
        double[][] distances = new double[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                distances[i][j]= Math.hypot(x[i]-x[j],y[i]-y[j]);
            }
        }

        return new TSP(names,distances);
    }

    public void printCities(){
        System.out.println("Cities: ");
        for(int i=0;i<cities.length;i++)
            System.out.println("\t"+i+". "+cities[i]);
    }
}
