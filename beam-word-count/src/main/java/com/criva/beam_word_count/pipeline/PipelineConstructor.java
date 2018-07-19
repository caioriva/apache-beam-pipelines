package com.criva.beam_word_count.pipeline;

import org.apache.beam.sdk.Pipeline;

public interface PipelineConstructor {

	Pipeline construct(String[] args);
}
