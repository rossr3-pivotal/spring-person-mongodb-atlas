package com.rickross.mongo.world.springperson;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/***
 * A helper class to retrieve the URI for MongoDB Atlas from Cloud Foundry VCAP_SERVICES environment variable
 * A limitation of this implementation is that the the name of the user provided service must be mongodb-atlas,
 * which is the value listed in the @ConfigurationProperties annotation below. 
 * 
 * @author rross
 *
 */
@Configuration
@ConfigurationProperties("vcap.services.mongodb-atlas.credentials")
public class MongoDbAtlasServiceProperties {
	private String uri;

	public MongoDbAtlasServiceProperties() {
		this.uri = null;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
}
