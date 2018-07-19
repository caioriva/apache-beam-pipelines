package com.criva.beam_word_count.bigquery;

import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.Validation.Required;

import com.criva.beam_word_count.pipeline.Options;

public interface BigQueryOptions extends Options {

	@Description("BigQuery dataset where the tables willl be write to. Must already exist")
	@Required
	String getDataset();
	
	void setDataset(String value);
	
	@Description("Prefix used for BigQuery table names")
	@Default.String("word_count")
	String getTableNamePrefix();
	
	void setTableNamePrefix(String value);	
}