package com.criva.beam_word_count.wordcount;

import java.util.stream.Stream;

import org.apache.beam.sdk.transforms.DoFn;

public class PickWordsFn extends DoFn<String, String> {

	@ProcessElement
	public void processElement(ProcessContext context) {
		
		String[] words = context.element().split("[^\\p{L}]+");
		
		Stream.of(words).filter(
			word -> !word.isEmpty()
		).forEach(
			
			word -> {
						context.output(word);
					}
		);
	}
}
