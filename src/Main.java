import BruteForce.BruteForce;

import TSP.TSP;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Creating a Travelling Salesman Problem with the brute force solution");
        System.out.println("Creating cities...");
        TSP tsp = null;

        Scanner in = new Scanner(System.in);
        System.out.println("Load cities from file?");
        if('y'!=in.nextLine().trim().toLowerCase().charAt(0)) {
            boolean done = false;
            ArrayList<String> cities = new ArrayList<>();
            while (!done) {
                System.out.println("Enter city name:");
                String name = in.nextLine();
                cities.add(name);
                System.out.println("Enter more? (y/n)");
                done = in.nextLine().trim().toLowerCase().charAt(0) != 'y';
            }

            double[][] distances = new double[cities.size()][cities.size()];

            for (int i = 0; i < distances.length; i++) {
                in = new Scanner(System.in);
                for(int j = i+1;j<distances.length;j++){
                    System.out.println("Enter the distance between " + cities.get(i) + " and " + cities.get(j));
                    double distance = in.nextDouble();
                    distances[i][j] = distance;
                    distances[j][i] = distance;
                }
            }

            tsp = new TSP(cities.toArray(String[]::new),distances);
        } else {
            tsp = readFromFile();
        }
        if(tsp==null)
            return;

        tsp.printCities();
        tsp.printDistances();
        BruteForce bf = new BruteForce(tsp);
        bf.run();
        System.out.println("Optimal path: " + tsp.pathToNames(bf.getPath())+" with distance of "+bf.getDistance());

    }

    private static TSP readFromFile(){
        JFileChooser fileChooser = new JFileChooser(System.getProperty("java.class.path"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("txt files","txt"));
        int returnVal = fileChooser.showOpenDialog(null);
        double[][] distances = null;
        String[] cities = null;
        try {
            Scanner file;
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                file = new Scanner(fileChooser.getSelectedFile());
                cities = file.nextLine().split(",");
                distances = new double[cities.length][cities.length];
                for(int i=0;i<cities.length;i++){
                    String[] vals = file.nextLine().split(" ");
                    for(int j=0;j<vals.length;j++)
                        distances[i][j] = Double.parseDouble(vals[j]);
                }
            }
            else
                return null;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new TSP(cities, distances);
    }
}
