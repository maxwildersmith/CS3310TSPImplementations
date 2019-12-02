package HeldKarp;

import TSP.TSP;

public class HeldKarp {
    TSP tsp;
    int[][][] cost;

    public HeldKarp(TSP tsp){
        this.tsp = tsp;
        cost = new int[tsp.getSize()][][];
    }

    public int[] run(){
        for(int i=0;i<tsp.getSize();i++){

        }
        return null;
    }

    public double cost(int start, int end, int[] via){
        if(via.length==0)
            return tsp.getDistance(start,end);

        return 0;
    }
}
