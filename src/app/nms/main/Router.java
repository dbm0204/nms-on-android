package com.example.ben.myapplication;
import android.content.Context;

public class Router extends DrawableNetworkComponent implements Comparable<Router>
{
	public final String name;
	public Link[] adjacencies;
	public double minDistance = Double.POSITIVE_INFINITY;
	public Router previous;
	public Router(Context context, String name) {
		super(context, Type.ROUTER);
		this.name = name;
	}
	public String toString(){
		return name;
	}
	public int compareTo(Router other)
	{
		return Double.compare(minDistance,other.minDistance);

	}

}
