package com.criva.beam_word_count.bigquery;

import java.io.Serializable;

public class BigQueryField<T> implements Serializable{

	private String type;
	private BigQueryFieldFn<T> function;
	
	public BigQueryField(String type, BigQueryFieldFn<T> function) {
		
		super();
		this.type = type;
		this.function = function;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BigQueryFieldFn<T> getFunction() {
		return function;
	}
	public void setFunction(BigQueryFieldFn<T> function) {
		this.function = function;
	}
	
	
}
