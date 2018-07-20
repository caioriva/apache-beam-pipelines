package com.criva.user_pipeline.pipeline;

import java.util.HashMap;
import java.util.Map;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.extensions.gcp.options.GcpOptions;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.WithTimestamps;
import org.apache.beam.sdk.transforms.windowing.FixedWindows;
import org.apache.beam.sdk.transforms.windowing.Window;
import org.apache.beam.sdk.values.KV;
import org.joda.time.Duration;
import org.joda.time.Instant;

import com.criva.user_pipeline.bigquery.BigQueryField;
import com.criva.user_pipeline.bigquery.WriteToBigQuery;
import com.criva.user_pipeline.bigquery.WriteToBigQueryData;
import com.criva.user_pipeline.data.User;

import com.google.api.services.bigquery.model.TableRow;

public class UserPipelineConstructor {

	public Pipeline construct(String[] args) {
		
		Options options = PipelineOptionsFactory
				.fromArgs(args)
				.withValidation()
				.as(Options.class);
		
		options.setStreaming(true);
		
		Pipeline pipeline = Pipeline.create(options);
		
		pipeline.apply("PubSubReading", 
				PubsubIO.readStrings().fromSubscription(options.getSubscription()))
		.apply("ParsingUserData", 
				ParDo.of(new ParseUserDataFn()))
		.apply("AddingTimestamps", 
				WithTimestamps.of(
						(User user) -> new Instant(user.getTimestamp())
				))
		.apply("Windowing", 
				Window.<User>into(FixedWindows.of(
						Duration.standardMinutes(options.getWindowDuration())
				)))
		.apply("CountingUsersPerGroup", new CountUsersPerGroup())
		.apply("WritingToBigQuery", 
				new WriteToBigQuery<KV<String, Long>>(new WriteToBigQueryData<>(
						options.as(GcpOptions.class).getProject(), 
						options.getDataset(), 
						options.getTableNamePrefix() + "_total_users_group",
						createFieldsMap())));
		
		return pipeline;
	}
	
	private Map<String, BigQueryField<KV<String, Long>>> createFieldsMap() {
		
		Map<String, BigQueryField<KV<String, Long>>> map = new HashMap<>();
		
		map.put("group", 
				new BigQueryField<KV<String, Long>>("STRING", 
														(c) -> c.element().getKey()));
		map.put("total_users", 
				new BigQueryField<KV<String, Long>>("INTEGER", 
														(c) -> c.element().getValue()));
		return map;
	}
}
