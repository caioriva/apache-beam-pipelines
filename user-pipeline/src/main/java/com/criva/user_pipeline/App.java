package com.criva.user_pipeline;

import com.criva.user_pipeline.pipeline.UserPipelineConstructor;

public class App  {
	
    public static void main( String[] args ) {
    
    	new UserPipelineConstructor().construct(args).run().waitUntilFinish();
    }
}
