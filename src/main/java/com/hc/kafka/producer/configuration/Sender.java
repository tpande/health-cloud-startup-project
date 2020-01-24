package com.hc.kafka.producer.configuration;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.hc.model.Member;

import java.util.ArrayList;
import java.util.List;

@Service
public class Sender {

    private static final Logger LOG = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private KafkaTemplate<String, Member> kafkaTemplate;
    
    @Value("${app.topic.message1}")
    private String topicMessage1;

    @Value("${app.topic.message2}")
    private String topicMessage2;

    public void sendMessage(Member member){
    	
    	System.out.println("Kafka Message Posted for Member ::::"+member.getId());

       Message<Member> message = MessageBuilder
                .withPayload(member)
                .setHeader(KafkaHeaders.TOPIC, topicMessage1)
                .setHeader(KafkaHeaders.MESSAGE_KEY, "999")
                .setHeader(KafkaHeaders.PARTITION_ID, 0)
                .setHeader("X-Custom-Header", "Sending Custom Header with Spring Kafka Message1")
                .build();

        LOG.info("sending message='{}' to topic='{}'", member, topicMessage1);
        kafkaTemplate.send(message);
    }

}