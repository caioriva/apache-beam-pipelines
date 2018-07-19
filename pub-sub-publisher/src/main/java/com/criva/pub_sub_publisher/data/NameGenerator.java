package com.criva.pub_sub_publisher.data;

import java.util.Random;

public class NameGenerator {

		private String[] beginning;
		
		private String[] middle;
		
		private String[] end;
		
		private Random rand;
		
		public NameGenerator() {
			
			beginning = new String[] { "Kr", "Ca", "Ra", "Mrok", "Cru",
			         "Ray", "Bre", "Zed", "Drak", "Mor", "Jag", "Mer", "Jar", "Mjol",
			         "Zork", "Mad", "Cry", "Zur", "Creo", "Azak", "Azur", "Rei", "Cro",
			         "Mar", "Luk" };
			
			middle = new String[] { "air", "ir", "mi", "sor", "mee", "clo",
			         "red", "cra", "ark", "arc", "miri", "lori", "cres", "mur", "zer",
			         "marac", "zoir", "slamar", "salmar", "urak" };
			
			end = new String[] { "d", "ed", "ark", "arc", "es", "er", "der",
			         "tron", "med", "ure", "zur", "cred", "mur" };
			
			rand = new Random();
		}

		public String getRandomName() {
			
			return beginning[rand.nextInt(beginning.length)] +
					middle[rand.nextInt(middle.length)] +
					end[rand.nextInt(end.length)];
		}   
}
