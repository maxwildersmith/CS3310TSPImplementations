package BruteForce;

import TSP.City;
import TSP.Connection;

import java.util.ArrayList;
import java.util.Scanner;

public class BruteForce {
    ArrayList<City> cities;

    public BruteForce(ArrayList<City> cities){
        this.cities = cities;
    }

    public ArrayList<Connection> run(){
        System.out.println("Select starting city: ");
        for(int i=0;i<cities.size();i++){
            System.out.print("\t"+i+". "+cities.get(i).getName());
        }
        Scanner in = new Scanner(System.in);


        return null;
    }
}
