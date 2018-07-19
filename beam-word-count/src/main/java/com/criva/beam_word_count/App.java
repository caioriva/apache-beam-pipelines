package com.criva.beam_word_count;

import com.criva.beam_word_count.pipeline.PipelineConstructor;
import com.criva.beam_word_count.wordcount.WordCountBigQueryPipelineConstructor;

public class App {

	public App(String[] args, PipelineConstructor pipelineConstructor) {

		pipelineConstructor.construct(args).run().waitUntilFinish();
	}

	public static void main(String[] args) {

		//new App(args, new WordCountPipelineConstructor());
		new App(args, new WordCountBigQueryPipelineConstructor());
	}
}