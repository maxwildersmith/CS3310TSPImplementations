package TSP;

import java.util.Arrays;

public class TSP {
    private String[] cities;
    private double[][] distances;

    /**
     * Creates a new TSP with a list of city names, and the distance matrix
     * @param cities String[] of city names
     * @param distances double[][] the distances between the cities
     */
    public TSP(String[] cities, double[][] distances){
        this.cities = cities;
        this.distances = distances;
    }

    /**
     * Prints the distance matrix
     */
    public void printDistances(){
        for(double[] row: distances)
            System.out.println(Arrays.toString(row));
    }

    /**
     * Returns the number of cities, i.e. the size of the scenario
     * @return The size of the TSP scenario based on the number of cities
     */
    public int getSize(){
        return cities.length;
    }

    /**
     * Gets the distance between two cities based on index
     * @param startCity The index of the start city
     * @param endCity The index of the end city
     * @return The distance between the two cities
     */
    public double getDistance(int startCity, int endCity){
        return distances[startCity][endCity];
    }

    /**
     * Prints the names of the cities in the order of a certain path
     * @param path The path of cities to go through
     * @return The String of namesa
     */
    public String pathToNames(int[] path){
        String out = "";
        for(int i: path){
            out+=", "+cities[i];
        }
        return out.substring(2);
    }

    /**
     * Automatically generates a TSP based on a given size by randomly plotting points
     * @param size The number of cities to make
     * @return The TSP for the randomly generated TSP
     */
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

    /**
     * Prints the cities for a certain TSP
     */
    public void printCities(){
        System.out.println("Cities: ");
        for(int i=0;i<cities.length;i++)
            System.out.println("\t"+i+". "+cities[i]);
    }
}
