package com.criva.beam_word_count.wordcount;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.MapElements;

import com.criva.beam_word_count.pipeline.Options;
import com.criva.beam_word_count.pipeline.PipelineConstructor;

public class WordCountPipelineConstructor implements PipelineConstructor {

	@Override
	public Pipeline construct(String[] args) {
		
		Options options = PipelineOptionsFactory
    			.fromArgs(args)
    			.withValidation()
    			.as(Options.class);
    	
    	Pipeline pipeline = Pipeline.create(options);
    	
    	pipeline
    	.apply(TextIO.read().from(options.getInput()))
    	.apply(new CountWords())
    	.apply(MapElements.via(new KeyValueToStringFn()))
    	.apply(TextIO.write().to(options.getOutput()));
    	
    	return pipeline;
	}
}