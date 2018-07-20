package com.criva.user_pipeline.bigquery;

import java.util.Map;

import org.apache.beam.sdk.transforms.DoFn;

import com.google.api.services.bigquery.model.TableRow;

public class CreateRowFn<T> extends DoFn<T, TableRow>{
	
	private Map<String, BigQueryField<T>> fields;

	public CreateRowFn(Map<String, BigQueryField<T>> fields) {
		
		this.fields = fields;
	}

	@ProcessElement
	public void processElement(ProcessContext c) {
		
		TableRow row = new TableRow();
		
		fields.entrySet().stream().forEach(
				entry -> {
					
					String key = entry.getKey();
					row.set(key, entry.getValue().getFunction().apply(c));
				}
		);
		
		c.output(row);
	}
}
