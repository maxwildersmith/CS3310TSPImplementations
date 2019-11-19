import TSP.City;
import TSP.Connection;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Creating a Travelling Salesman Problem with the brute force solution");
        System.out.println("Creating cities...");
        ArrayList<City> cities = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        System.out.println("Load cities from file?");
        if('y'!=in.nextLine().trim().toLowerCase().charAt(0)) {
            boolean done = false;
            while (!done) {
                System.out.println("Enter city name");
                String name = in.nextLine();
                cities.add(new City(name));
                System.out.println("Enter more? (y/n)");
                done = in.nextLine().trim().toLowerCase().charAt(0) != 'y';
            }

            for (int i = 0; i < cities.size() - 1; i++) {
                in = new Scanner(System.in);
                System.out.println("Which cites would you like to connect to " + cities.get(i).getName() + " separated by commas");
                for (int j = i + 1; j < cities.size(); j++)
                    System.out.println(j + ". - " + cities.get(j).getName());
                String[] linkStrings = in.nextLine().trim().split(",");
                int[] links = new int[linkStrings[0].length() > 0 ? linkStrings.length : 0];
                for (int j = 0; j < links.length && linkStrings[0].length() > 0; j++) {
                    try {
                        links[j] = Integer.parseInt(linkStrings[j]);
                    } catch (NumberFormatException ignored) {
                    }
                }
                for (int link : links) {
                    System.out.println("Enter the distance between " + cities.get(i).getName() + " and " + cities.get(link).getName());
                    double distance = in.nextDouble();
                    cities.get(i).addConnection(i, link, distance);
                    cities.get(link).addConnection(i, link, distance);
                }
            }
        } else {
            cities = readFromFile();
        }
        if(cities==null)
            return;



        printCityConnections(cities);
    }

    private static void printCityConnections(ArrayList<City> cities){
        for(City c: cities){
            System.out.println("Connections for "+c.getName());
            for(Connection cc: c.getConenctions())
                System.out.println("\tFrom "+cities.get(cc.getCities()[0])+" to "+cities.get(cc.getCities()[1])+" is "+cc.getDistance());
            System.out.println();
        }
    }

    private static ArrayList<City> readFromFile(){
        ArrayList<City> cities = new ArrayList<>();
        JFileChooser fileChooser = new JFileChooser(System.getProperty("java.class.path"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("txt files","txt"));
        int returnVal = fileChooser.showOpenDialog(null);
        try {
            Scanner file;
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                file = new Scanner(fileChooser.getSelectedFile());
                String[] cityStrings = file.nextLine().split(",");
                String[] connectionStrings = file.nextLine().split(",");
                for(String name: cityStrings)
                    cities.add(new City(name));
                for(String connection: connectionStrings){
                    String[] vals = connection.split(" ");
                    int a = Integer.parseInt(vals[0]), b = Integer.parseInt(vals[1]);
                    double d = Integer.parseInt(vals[2]);
                    cities.get(a).addConnection(a,b,d);
                    cities.get(b).addConnection(a,b,d);
                }
            }
            else
                return null;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return cities;
    }

}
