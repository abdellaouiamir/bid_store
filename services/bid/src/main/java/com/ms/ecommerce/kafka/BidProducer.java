package com.ms.ecommerce.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BidProducer {

    private final KafkaTemplate<String, BidWinner> kafkaTemplate;
    public void sendBidWinner(BidWinner bidWinner) {
        log.info("Sending bid winner to topic: {}", bidWinner);
        Message<BidWinner> message = MessageBuilder
                .withPayload(bidWinner)
                .setHeader(KafkaHeaders.TOPIC, "bid-topic")
                .build();
        kafkaTemplate.send(message);
    }
}
