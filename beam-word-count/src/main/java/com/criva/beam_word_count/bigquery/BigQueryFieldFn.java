package com.criva.beam_word_count.bigquery;


import java.io.Serializable;

import org.apache.beam.sdk.transforms.DoFn;

import com.google.api.services.bigquery.model.TableRow;

public interface BigQueryFieldFn<T> extends Serializable {

	Object apply(DoFn<T,TableRow>.ProcessContext context);
}
