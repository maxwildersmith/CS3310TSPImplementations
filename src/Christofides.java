import TSP.TSP;

import java.util.ArrayList;

public class Christofides 
{
	TSP tsp;
	double finalDistance;
	Integer[] finalPath;
	
	public Christofides(TSP tsp)
	{
		this.tsp = tsp;
		finalPath = new Integer[tsp.getSize()];
	}


	/**
	 * Uses Prim's Algorithm to find the Minimum Spanning Tree and record the order in
	 * which vertices are visited
	 * @return Integer[] array of visited vertices
	 */
	public Integer[] run()
	{
		int length = tsp.getSize();
		ArrayList<Integer> visited = new ArrayList();
		ArrayList<Integer> unvisited = new ArrayList();
		double[] distances = new double[length];
		int[] edges = new int[length];
		for(int i = 1; i<length; i++)
		{
			unvisited.add(i);
		}
		visited.add(0);
		while(unvisited.size()>0)
		{
			double min = Double.MAX_VALUE;
			int end = -1;
			for(int i = 0; i<visited.size(); i++)
			{
				int start = visited.get(i);
				for(int j = 0; j<unvisited.size(); j++)
				{
					int finish = unvisited.get(j);
					double dist = tsp.getDistance(start, finish);
					dist+=distances[i];
					if(dist<min)
					{
						min = dist;
						end = j;
					}
				}
			}
			visited.add(unvisited.get(end));
			unvisited.remove(end);
			distances[visited.size()]=min;
		}
		finalPath = visited.toArray(finalPath);
		return finalPath;
	}
	
	/**
	 * Calculates the distance between the nodes in FinalPath and sums them all up
	 * @return Distance of the path
	 */
	public double getDistance()
	{
		double dist=0;
		for(int i = 0; i<finalPath.length-1; i++)
		{
			dist+=tsp.getDistance(finalPath[i],finalPath[i+1]);
		}
		return dist+=tsp.getDistance(finalPath[finalPath.length-1],0);
	}	
	
	/**
	 * @return int[] representing the shortest path
	 */
	public int[] getPath()
	{
		int[] path = new int[finalPath.length+1];
		for(int i = 0; i<finalPath.length; i++)
		{
			path[i]=finalPath[i];
		}
		path[finalPath.length]=0;
		return path;
	}
}
