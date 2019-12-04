import BruteForce.BruteForce;

import Christofides.Christofides;
import TSP.TSP;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {


    public static final boolean print = true, auto=true;
    public static void main(String[] args) {
        System.out.println("Creating a Travelling Salesman Problem with the brute force solution");
        System.out.println("Creating cities...");
        TSP tsp = null;

        Scanner in = new Scanner(System.in);


        if(auto) {
            System.out.println("Enter max time in seconds: ");
            long maxTime = (long)(in.nextDouble()*1000);
            long bfTime = 0, cTime = 0, start, end;
            int size = 5;
            boolean runBf = true;

            ArrayList<Long> sizes = new ArrayList<>();
            ArrayList<Long> bfTimes = new ArrayList<>();
            ArrayList<Long> cTimes = new ArrayList<>();
            while(bfTime<maxTime*.75&&cTime<maxTime*.75){
                try {
                    tsp = TSP.autoGenerate(size);
                    sizes.add((long) size);
                    Christofides c = new Christofides(tsp);
                    start = System.currentTimeMillis();
                    c.run();
                    end = System.currentTimeMillis();
                    cTime = end - start;
                    if (print)
                        System.out.println("Optimal path with Christofides of size " + tsp.getSize() + " in " + cTime);
                    cTimes.add(cTime);
                }catch (OutOfMemoryError e){
                    System.out.println("Out of memory");
                    bfTime=maxTime*2;
                }

                sizes.add((long) size);
                if(runBf) {
                    try {
                        tsp = TSP.autoGenerate(size);
                        System.gc();
                        BruteForce bf = new BruteForce(tsp);
                        start = System.currentTimeMillis();
                        bf.run();
                        end = System.currentTimeMillis();
                        bfTime = end - start;
                        if(print)
                            System.out.println("Optimal path with brute force: size "+tsp.getSize()+" in "+(bfTime));
                        bfTimes.add(bfTime);
                    } catch (OutOfMemoryError e){
                        System.out.println("Out of heap space for brute force");
                        runBf = false;
                    }

                } else {
                    bfTimes.add((long) -1);
                }
                size+=1;
            }
            createCSV("BruteForce.csv",sizes,bfTimes);
            createCSV("Christofides.csv",sizes,cTimes);
            return;
        }

        System.out.println("Load cities from file?");
        if('y'!=in.nextLine().trim().toLowerCase().charAt(0)) {
            tsp = userInput();
        } else {
            tsp = readFromFile();
        }
        if(tsp==null)
            System.err.println("Matrix improperly configured.");

        tsp.printCities();
        tsp.printDistances();

        BruteForce bf = new BruteForce(tsp);
        long start = System.currentTimeMillis();
        bf.run();
        long end = System.currentTimeMillis();
        System.out.println("Optimal path: " + tsp.pathToNames(bf.getPath())+" with distance of "+bf.getDistance()+" in "+(end-start));

        Christofides c = new Christofides(tsp);
        start = System.currentTimeMillis();
        c.run();
        end = System.currentTimeMillis();
        System.out.println("Optimal path: " + tsp.pathToNames(c.getPath())+" with distance of "+c.getDistance()+" in "+(end-start));

    }

    /**
     * Creates a TSP based on user input.
     * @return The TSP generated based on what the user requested
     */
    private static TSP userInput(){
        Scanner in = new Scanner(System.in);
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

        return new TSP(cities.toArray(String[]::new),distances);
    }

    /**
     * Launches the JFileChooser, and lets the user select a file to load in cities from
     * @return The TSP that is generated from the file
     */
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

    /**
     * Static helper method to export a CSV file for a given set of algorithm times and sizes. If the CSV file
     * already exists, it will append to the file, and add additional array size values if needed.
     * @param filename The name of the file to create.
     * @param sizes An ArrayList of the data set sizes.
     * @param times An ArrayList of the time to perform a given operation.
     */
    private static void createCSV(String filename, ArrayList<Long> sizes, ArrayList<Long> times){
        File csv = new File(""+filename);
        try {
            if(csv.createNewFile()) {
                PrintWriter file = new PrintWriter(new FileWriter(csv));
                file.print("Size (n)");
                for (Long size : sizes)
                    file.print("," + size);
                file.print("\nTimes (ms)");
                for(Long time:times)
                    file.print(","+time);
                file.println();
                file.close();

            } else {
                Scanner fileReader = new Scanner(csv);
                String currentSizes = fileReader.nextLine();
                int size=currentSizes.split(",").length-1;
                if(size<sizes.size()){
                    for(int i=size;i<sizes.size();i++)
                        currentSizes+=","+sizes.get(i);
                    currentSizes+="\n";
                    while(fileReader.hasNextLine())
                        currentSizes+=fileReader.nextLine()+"\n";
                    FileWriter writer = new FileWriter(csv);
                    writer.write(currentSizes);
                    writer.close();
                }

                PrintWriter file = new PrintWriter(new FileWriter(csv,true));
                file.print("Times (ms)");
                for(Long time:times)
                    file.print(","+time);
                file.println();
                file.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
