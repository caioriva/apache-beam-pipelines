package com.criva.user_pipeline.pipeline;

import org.apache.beam.sdk.transforms.DoFn;

import com.criva.user_pipeline.data.User;
import com.google.gson.Gson;

public class ParseUserDataFn extends DoFn<String, User> {

	private Gson gson;
	
	public ParseUserDataFn() {

		gson = new Gson();
	}

	@ProcessElement
	public void processElement(ProcessContext context) {
		
		User user = gson.fromJson(context.element(), User.class);
		context.output(user);
	}
}
