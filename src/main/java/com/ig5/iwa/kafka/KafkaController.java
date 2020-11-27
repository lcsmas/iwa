package com.ig5.iwa.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(value = "api/v1/kafka")
public class KafkaController {

    @Autowired
    private KafkaProducer producer;
    public final List<SseEmitter> emitters = new ArrayList<>();


    @PostMapping(value = "/publish/longitude/{longitude}/latitude/{latitude}/userId/{userId}/state/{state}")
    public void sendMessageToKafkaTopic(@PathVariable String longitude,@PathVariable String latitude,@PathVariable String userId,@PathVariable String state) {

        // Set Date to the location received
        Date newDate = new Date();
        long currentDate = newDate.getTime();
        String location =  userId + "," + state + "," + latitude + ","+ longitude + "," + currentDate;
        System.out.println(location);

        this.producer.send(location);
    }

    @GetMapping(value = "/emitter")
    public SseEmitter sendEventToClient(){
        SseEmitter emitter = new SseEmitter();
        emitters.add(emitter);

        emitter.onCompletion(new Runnable() {
            @Override
            public void run() {
                emitters.remove(emitter);
            }
        });

        emitter.onTimeout(new Runnable() {
            @Override
            public void run() {
                emitters.remove(emitter);
            }
        });

        return emitter;
    }

    public List<SseEmitter> getEmitters() {
        return emitters;
    }

    public SseEmitter getLatestEmitter() {
        return (emitters.isEmpty()) ? null : emitters.get(emitters.size()-1);
    }

}

