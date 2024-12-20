package ch.dboeckli.springframeworkguru.spring6coldmicroservice.listener;

import ch.dboeckli.springframeworkguru.spring6coldmicroservice.config.KafkaConfig;
import ch.guru.springframework.spring6restmvcapi.events.DrinkPreparedEvent;
import ch.guru.springframework.spring6restmvcapi.events.DrinkRequestEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DrinkRequestListener {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(groupId = "IceColdListener", topics = KafkaConfig.DRINK_REQUEST_ICE_COLD_TOPIC)
    public void listenDrinkRequest(DrinkRequestEvent event) {
        log.info("### I am listening - Cold drink request");

        //drinkRequestProcessor.processDrinkRequest(event);

        kafkaTemplate.send(KafkaConfig.DRINK_PREPARED_TOPIC, DrinkPreparedEvent.builder()
            .beerOrderLineDTO(event.getBeerOrderLineDTO())
            .build());

    }

}
