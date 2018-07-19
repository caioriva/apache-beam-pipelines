package com.criva.beam_word_count.wordcount;

import org.apache.beam.sdk.transforms.SimpleFunction;
import org.apache.beam.sdk.values.KV;

public class KeyValueToStringFn extends SimpleFunction<KV<String, Long>, String>{

	@Override
	public String apply(KV<String, Long> input) {
		
		return input.getKey() + ": " + input.getValue();
	}
}
