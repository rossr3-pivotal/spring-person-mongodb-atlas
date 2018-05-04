package com.rickross.mongo.world.springperson;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.mongo.MongoClientFactory;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;

@Configuration
@ConditionalOnClass(MongoClient.class)
@ConditionalOnMissingBean(type = "org.springframework.data.mongodb.MongoDbFactory")
public class MongoDbAtlasAutoConfiguration {

	private final MongoClientOptions options;
	private MongoProperties properties;
	private Environment environment;
	private MongoClientFactory factory;

	private MongoClient mongo;
		
	@Autowired
	private MongoDbAtlasServiceProperties atlasProperties;

	
	public MongoDbAtlasAutoConfiguration(MongoProperties properties, ObjectProvider<MongoClientOptions> options, Environment environment)
	{
		this.properties = properties;
		this.options = options.getIfAvailable();
		this.environment = environment;		
	}
	
	@PreDestroy
	public void close() {
		if (this.mongo != null) {
			this.mongo.close();
			this.mongo = null;
		}
	}
	
	@Bean
	@ConditionalOnMissingBean
	public MongoClient mongo() {		
		if (this.factory == null)
		{			
			// override the default URI with the one from the user provided service
			// See the class MongoDbAtlasServiceProperties for the details
			properties.setUri(atlasProperties.getUri());

			this.factory = new MongoClientFactory(properties, environment);
		}
		this.mongo = this.factory.createMongoClient(this.options);
		return this.mongo;
	}
}
