package com.example.order._event;

import lombok.*;
import org.springframework.kafka.core.*;
import org.springframework.stereotype.*;

@Component
@RequiredArgsConstructor
public class KafkaUtils {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendEvent(String event, Object data) {
        kafkaTemplate.send(event, EventSerializer.serialize(data));
    }

}
