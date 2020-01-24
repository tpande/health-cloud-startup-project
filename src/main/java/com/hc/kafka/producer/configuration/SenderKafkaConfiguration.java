package com.hc.kafka.producer.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.hc.model.Member;

import org.apache.kafka.common.serialization.StringSerializer;

@Configuration
public class SenderKafkaConfiguration {
	
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
	
	@Bean
	public ProducerFactory<String,Member> producerFactory(){
		
		Map<String,Object> config = new HashMap<>();
		
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		
		return new DefaultKafkaProducerFactory<>(config);
	}
	
	
    @Bean
    public KafkaTemplate<String, Member> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}