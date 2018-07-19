package com.criva.pub_sub_publisher;


public class App 
{
    public App(String[] args) {
		
    	new PubSubPublisher().publishLimited(args[0], Integer.parseInt(args[1]));
	}

	public static void main( String[] args ) {
        
		new App(args);
    }
}
