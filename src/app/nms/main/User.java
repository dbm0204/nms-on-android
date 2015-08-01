package com.example.ben.myapplication;

import android.content.Context;


public class User extends DrawableNetworkComponent implements Comparable<User> {
	public final String name;
	public Link[] adjacencies;
	public double minDistance = Double.POSITIVE_INFINITY;
	public User previous;
	public User(Context context, String name) {
		super(context, Type.USER);
		this.name = name;
	}
	public String toString(){
		return name;
	}
	public int compareTo(User other){
		return Double.compare(minDistance,other.minDistance);

	}


}
