package com.criva.beam_word_count.bigquery;

import java.util.ArrayList;
import java.util.List;

import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO.Write.CreateDisposition;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO.Write.WriteDisposition;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PDone;

import com.google.api.services.bigquery.model.TableFieldSchema;
import com.google.api.services.bigquery.model.TableReference;
import com.google.api.services.bigquery.model.TableSchema;

public class WriteToBigQuery<T> extends PTransform<PCollection<T>, PDone> {

	private static final long serialVersionUID = 1L;
	
	private WriteToBigQueryData<T> data;
	
	public WriteToBigQuery(WriteToBigQueryData<T> data) {
		
		this.data = data;
	}

	@Override
	public PDone expand(PCollection<T> input) {
		
		input.apply("ConvertToRow", ParDo.of(new CreateRowFn<T>(data.getFieldMap())))
		.apply(BigQueryIO.writeTableRows()
				.to(getTable())
				.withSchema(getSchema())
				.withCreateDisposition(CreateDisposition.CREATE_IF_NEEDED)
				.withWriteDisposition(WriteDisposition.WRITE_APPEND));
		
		return PDone.in(input.getPipeline());
	}
	
	private TableReference getTable() {
		
		TableReference table = new TableReference();
		
		table.setDatasetId(data.getDatasetId());
		table.setProjectId(data.getProjectId());
		table.setTableId(data.getTableId());
		
		return table;
	}
	
	private TableSchema getSchema() {
		
		TableSchema schema = new TableSchema();
		List<TableFieldSchema> tableFields = new ArrayList<>();
		
		data.getFieldMap().entrySet().stream().forEach(
				entry -> {
					String key = entry.getKey();
					String type = entry.getValue().getType();
					tableFields.add(new TableFieldSchema().setName(key).setType(type));
				}
		);
		
		return schema.setFields(tableFields);
	}
}