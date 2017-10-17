package com.myretail.product.config;

import com.mongodb.MongoClient;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;

@Configuration
@ConfigurationProperties(prefix = "datasource")
public class MongoConfig {

    private String mongoDbUrl;
    private String mongoDbName;

    public void setMongoDbUrl(String mongoDbUrl) {
        this.mongoDbUrl = mongoDbUrl;
    }

    public void setMongoDbName(String mongoDbName) {
        this.mongoDbName = mongoDbName;
    }

    @Bean
    public MongoTemplate mongoTemplate() throws IOException {
        EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
        mongo.setBindIp(mongoDbUrl);
        MongoClient mongoClient = mongo.getObject();
        return new MongoTemplate(mongoClient, mongoDbName);
    }
}
