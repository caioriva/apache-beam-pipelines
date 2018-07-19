package com.criva.beam_word_count.pipeline;

import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.Validation.Required;

public interface Options extends PipelineOptions {
	
	@Description("Path to where the data will be read from")
	@Default.String("gs://apache-beam-samples/shakespeare/kinglear.txt")
	String getInput();
	
	void setInput(String value);
	
	@Description("Path to where the data will be write to")
	@Required
	String getOutput();
	
	void setOutput(String value);
}
