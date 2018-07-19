package com.criva.pub_sub_publisher.data;

import java.util.Random;

public class GroupGenerator {

	private String[] groups;
	
	private Random rand;

	public GroupGenerator() {

		groups = new String[] { "A1", "B2", "C3", "D4", "E5", "F6", "G7", "H8", "I9",
				"J1", "K2", "L3", "M4", "N5", "O6", "P7", "Q8", "R9", "S1", "T2",
				"U3", "V4", "W5", "X6", "Y7", "Z8" };
		
		rand = new Random();
	}
	
	public String getRandomGroup() {
		
		return groups[rand.nextInt(groups.length)];
	}
}
