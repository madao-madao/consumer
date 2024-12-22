package com.madaochi.kafkaconsumer.service;

import com.madaochi.kafkaconsumer.entity.ConsumerEntity;
import com.madaochi.kafkaconsumer.repository.ConsumerRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    private static final Logger log = LoggerFactory.getLogger(ConsumerService.class);

    private final ConsumerRepository consumerRepository;

    public ConsumerService(ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    @KafkaListener(topics = "kafka-topic", groupId = "my_consumer")
    public void listen(ConsumerRecord<String, String> message) {
        try {
            String messageValue = message.value();
            if(messageValue == null){
                log.warn("message is null");
                return;
            }
        ConsumerEntity consumerEntity = new ConsumerEntity(messageValue);
        System.out.println("Recieved message = " + messageValue);
        consumerRepository.save(consumerEntity);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
