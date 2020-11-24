package com.ig5.iwa.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    void sendMessageWithCallback(String message) {
        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(topic1, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {


                /*LOG.info("Message [{}] delivered with offset {}",
                        message,
                        result.getRecordMetadata().offset());*/
            }

            @Override
            public void onFailure(Throwable ex) {
                /*LOG.warn("Unable to deliver message [{}]. {}",
                        message,
                        ex.getMessage());*/
            }
        });
    }



}
