package BruteForce;

import TSP.City;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Creating a Travelling Salesman Problem with the brute force solution");
        System.out.println("Creating cities...");
        ArrayList<City> cities = new ArrayList<>();
        boolean done = false;
        Scanner in = new Scanner(System.in);
        while(!done){
            System.out.println("Enter city name");
            String name = in.nextLine();
            cities.add(new City(name));
            System.out.println("Enter more? (y/n)");
            done = in.nextLine().trim().toLowerCase().charAt(0)!='y';
        }

        for(int i=0;i<cities.size();i++){
            System.out.println("Which cites would you like to connect to "+cities.get(i).getName()+" separated by commas");
            for(int j=i+1;j<cities.size();j++)
                System.out.println(j+". - "+cities.get(j).getName());
            String[] linkStrings = in.nextLine().trim().split(",");
            int[] links = new int[linkStrings.length];
            for(int j=0;j<links.length;j++) {
                links[i] = Integer.parseInt(linkStrings[j]);
                System.out.println(links[i]);
            }
            for(int j=0;j<links.length;j++){
                System.out.println(links[j]+"  Enter the distance between "+cities.get(i).getName()+" and "+cities.get(links[j]).getName());
                cities.get(i).addConnection(i,links[j],in.nextDouble());
            }
        }
    }

}
