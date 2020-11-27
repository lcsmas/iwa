package com.ig5.iwa.kafka;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.protocol.Message;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.logging.Logger;

@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private String topic = "iwaTopic";

    public void send(String message){
        this.kafkaTemplate.send(topic,message);
    }


}
