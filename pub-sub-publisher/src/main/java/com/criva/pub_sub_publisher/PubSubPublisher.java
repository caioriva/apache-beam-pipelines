package com.criva.pub_sub_publisher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.criva.pub_sub_publisher.data.User;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.cloud.ServiceOptions;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.gson.Gson;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;

public class PubSubPublisher {

	private static final String PROJECT_ID = ServiceOptions.getDefaultProjectId();
	
	private Publisher publisher;
	private List<ApiFuture<String>> futures;
	private Gson gson;
	
	public PubSubPublisher() {
		
		gson = new Gson();
	}

	public void publishNeverEnding(String topicId) {
		
	}
	
	public void publishLimited(String topicId, Integer limit) {
		
		try {

			ProjectTopicName topicName = ProjectTopicName.of(PROJECT_ID, topicId);
			publisher = Publisher.newBuilder(topicName).build();
			futures = new ArrayList<>();
			
			for(int i = 0; i < limit; i++) {
				
				ByteString data = ByteString.copyFromUtf8(
						gson.toJson(
								User.getRandomUser()
								)
						);
				
				PubsubMessage pubsubMessage = PubsubMessage.newBuilder()
						.setData(data).build();
				
				futures.add(publisher.publish(pubsubMessage));
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		} finally {
			
			try {
					
				ApiFutures.allAsList(futures).get().stream().forEach(
						message -> System.out.println(message)
				);
			
				if(publisher != null) {
					
					publisher.shutdown();
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		}
	}
}
