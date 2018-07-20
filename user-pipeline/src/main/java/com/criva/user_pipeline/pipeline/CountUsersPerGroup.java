package com.criva.user_pipeline.pipeline;

import org.apache.beam.sdk.transforms.Count;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TypeDescriptors;

import com.criva.user_pipeline.data.User;

public class CountUsersPerGroup extends PTransform<PCollection<User>, PCollection<KV<String, Long>>> {

	@Override
	public PCollection<KV<String, Long>> expand(PCollection<User> input) {
		
		return input.apply("GettingGroupOnly", 
				MapElements.into(TypeDescriptors.strings()).via(
						(User user) -> user.getGroup()
				))
				.apply("CountingGroup", 
						Count.perElement());
	}
}
