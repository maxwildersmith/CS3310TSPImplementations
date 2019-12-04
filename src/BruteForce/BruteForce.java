package BruteForce;

import TSP.TSP;

import java.util.ArrayList;

public class BruteForce {
    TSP tsp;
    double finalDistance;
    int[] finalPath;

    /**
     * Constructs a Brute Force based approach for a TSP problem with the given distance matrix.
     * @param tsp The TSP scenario
     */
    public BruteForce(TSP tsp){
        this.tsp = tsp;
    }

    /**
     * Runs the Brbute Force algorithm to find the shortest path
     * and returns this path in an int[] of city indices
     */
    public int[] run(){
        ArrayList<int[]> paths = new ArrayList<>();

        for(int i=0; i<tsp.getSize(); i++){
            int[] visited = new int[tsp.getSize()];
            visited[i] = 1;
            int[] path = new int[tsp.getSize()];
            path[0] = i;
            nextLayer(path.clone(), visited.clone(),1, paths);
        }
        int shortestIndex = getMinDistance(paths);

        finalDistance = traversePath(paths.get(shortestIndex));
        finalPath = paths.get(shortestIndex);
        return paths.get(shortestIndex);
    }

    /**
     * Returns the final optimal path
     * @return An int[] representing the optimal path
     */
    public int[] getPath(){
        return finalPath;
    }

    /**
     * Returns the optimal distance
     * @return The optimal distance from the optimal path
     */
    public double getDistance(){
        return finalDistance;
    }

    /**
     * Gets the index of the shortest path
     * @param paths An ArrayList of paths
     * @return The index of the shortest path
     */
    public int getMinDistance(ArrayList<int[]> paths){
        double shortest = Double.MAX_VALUE;
        int index = -1;
        for(int i=0;i<paths.size();i++){
            double tmpD = traversePath(paths.get(i));
            if(tmpD<shortest){
                shortest = tmpD;
                index = i;
            }
        }
        return index;
    }

    /**
     * Gets the distance it takes to travel a certain path
     * @param path an int[] for the path to travel
     * @return The distance travelled for this path
     */
    private double traversePath(int[] path){
        double distance = 0;
        for(int i=0; i<path.length-1; i++)
            distance += tsp.getDistance(path[i],path[i+1]);
        distance += tsp.getDistance(path[path.length-1],path[0]);
        return distance;
    }

    /**
     * Calculates the next layer of possible paths to explore
     * @param path The current path of cites
     * @param visited An int[] that has a value as 1 if the city in that index is visited
     * @param i Which iteration this method is on
     * @param paths An ArrayList of all possible paths
     */
    private void nextLayer(int[] path, int[] visited, int i, ArrayList<int[]> paths){
        if(i==visited.length)
            paths.add(path);
        else
            for(int j = 0; j<tsp.getSize();j++)
                if (visited[j] == 0) {
                    visited[j] = 1;
                    path[i] = j;
                    i++;
                    nextLayer(path.clone(), visited.clone(),i, paths);
                    visited[j] = 0;
                    i--;
                }
    }
}
