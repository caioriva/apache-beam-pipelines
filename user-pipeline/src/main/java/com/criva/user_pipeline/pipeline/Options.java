package com.criva.user_pipeline.pipeline;

import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.StreamingOptions;
import org.apache.beam.sdk.options.Validation.Required;

public interface Options extends PipelineOptions, StreamingOptions {

	@Description("Pub/Sub subscription to read from")
	@Required
	String getSubscription();

	void setSubscription(String value);

	@Description("Window duration, in minutes")
	@Default.Integer(1)
	Integer getWindowDuration();

	void setWindowDuration(Integer value);

	@Description("BigQuery dataset where the tables willl be write to. Must already exist")
	@Required
	String getDataset();

	void setDataset(String value);

	@Description("Prefix used for BigQuery table names")
	@Default.String("user_pipeline")
	String getTableNamePrefix();

	void setTableNamePrefix(String value);

}
