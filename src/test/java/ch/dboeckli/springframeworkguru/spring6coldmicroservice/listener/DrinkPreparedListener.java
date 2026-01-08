package ch.dboeckli.springframeworkguru.spring6coldmicroservice.listener;

import ch.dboeckli.springframeworkguru.spring6coldmicroservice.config.KafkaTopics;
import ch.guru.springframework.spring6restmvcapi.events.DrinkPreparedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@Profile("test")
@Slf4j
public class DrinkPreparedListener {

    public AtomicInteger messageCounter = new AtomicInteger(0);

    @KafkaListener(topics = KafkaTopics.DRINK_PREPARED_TOPIC, groupId = "cold-listener")
    public void listen(DrinkPreparedEvent event) {
        log.info("### DrinkPreparedListener:  I'm listening..." + event);

        messageCounter.incrementAndGet();
    }
    
}
