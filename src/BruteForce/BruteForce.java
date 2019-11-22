package BruteForce;

import TSP.TSP;

import java.util.ArrayList;

public class BruteForce {
    TSP tsp;
    double finalDistance;
    int[] finalPath;

    public BruteForce(TSP tsp){
        this.tsp = tsp;
    }

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

    public int[] getPath(){
        return finalPath;
    }

    public double getDistance(){
        return finalDistance;
    }

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

    private double traversePath(int[] path){
        double distance = 0;
        for(int i=0; i<path.length-1; i++)
            distance += tsp.getDistance(path[i],path[i+1]);
        distance += tsp.getDistance(path[path.length-1],path[0]);
        return distance;
    }

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
