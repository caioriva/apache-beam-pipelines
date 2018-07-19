package com.criva.beam_word_count.wordcount;

import java.util.HashMap;
import java.util.Map;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.extensions.gcp.options.GcpOptions;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.values.KV;

import com.criva.beam_word_count.bigquery.BigQueryField;
import com.criva.beam_word_count.bigquery.BigQueryOptions;
import com.criva.beam_word_count.bigquery.WriteToBigQuery;
import com.criva.beam_word_count.bigquery.WriteToBigQueryData;
import com.criva.beam_word_count.pipeline.PipelineConstructor;
import com.google.api.services.bigquery.model.TableRow;


@SuppressWarnings("unused")
public class WordCountBigQueryPipelineConstructor implements PipelineConstructor{

	@Override
	public Pipeline construct(String[] args) {
	
		BigQueryOptions options = PipelineOptionsFactory
									.fromArgs(args)
									.withValidation()
									.as(BigQueryOptions.class);
		
		Pipeline pipeline = Pipeline.create(options);
		
		pipeline
    	.apply(TextIO.read().from(options.getInput()))
    	.apply(new CountWords())
    	.apply(new WriteToBigQuery<KV<String, Long>>(
    			new WriteToBigQueryData<>(options.as(GcpOptions.class).getProject(), 
    										options.getDataset(), 
    										options.getTableNamePrefix() + WordCountConstants.REGISTER_TABLE,
    										createFieldsMap())));
    	
    	
		return pipeline;
	}
	
	private Map<String, BigQueryField<KV<String, Long>>> createFieldsMap() {
		
		Map<String, BigQueryField<KV<String, Long>>> map = new HashMap<>();
		
		map.put(WordCountConstants.REGISTER_TABLE_WORD_COLUMN, 
				new BigQueryField<KV<String, Long>>("STRING", 
														(c) -> c.element().getKey()));
		map.put(WordCountConstants.REGISTER_TABLE_COUNT_COLUMN, 
					new BigQueryField<KV<String, Long>>("INTEGER", 
															(c) -> c.element().getValue()));
		return map;
	}
}