//package com.example.ben.myapplication;
package app.nms.main;
import android.content.Context;

import app.nms.graphics.DrawableNetworkComponent;
import app.nms.utils.Link;


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
