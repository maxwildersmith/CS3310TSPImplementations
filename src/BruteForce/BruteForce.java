package BruteForce;

import TSP.City;
import TSP.Connection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BruteForce {
    ArrayList<City> cities;
    int startCity;
    ArrayList<Connection> route;
    double[][] distances;

    public BruteForce(ArrayList<City> cities){
        this.cities = cities;
    }

    public ArrayList<Connection> run(){
        System.out.println("Select starting city: ");
        for(int i=0;i<cities.size();i++){
            System.out.print("\t"+i+". "+cities.get(i).getName());
        }
        Scanner in = new Scanner(System.in);
        startCity = in.nextInt();
        distances = new double[cities.size()][cities.size()];

        for(City city: cities){
            for(Connection c: city.getConenctions()){
                distances[c.getCities()[0]][c.getCities()[1]] = c.getDistance();
            }
        }

        print2DArr(distances);

        return null;
    }


    public void print2DArr(double[][] arr){
        for(double[] row: arr)
            System.out.println(Arrays.toString(row));
    }


    public void nextLayer(int startIndex, int[] visited){
        visited[startIndex] = 1;
        for(Connection c: cities.get(startIndex).getConenctions()){
            //if(c.getCities()[1])
        }
    }


}
