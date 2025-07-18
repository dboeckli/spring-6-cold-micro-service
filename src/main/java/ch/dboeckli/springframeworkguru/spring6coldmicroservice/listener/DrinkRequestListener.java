package ch.dboeckli.springframeworkguru.spring6coldmicroservice.listener;

import ch.dboeckli.springframeworkguru.spring6coldmicroservice.config.KafkaConfig;
import ch.dboeckli.springframeworkguru.spring6coldmicroservice.services.DrinkRequestProcessor;
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

    private final DrinkRequestProcessor drinkRequestProcessor;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public static final String GROUP_ID = "ColdListener";

    @KafkaListener(groupId = GROUP_ID, topics = KafkaConfig.DRINK_REQUEST_COLD_TOPIC)
    public void listenDrinkRequest(DrinkRequestEvent event) {
        log.info("### I am listening - Cold drink request" + event);

        drinkRequestProcessor.processDrinkRequest(event);
        
        kafkaTemplate.send(KafkaConfig.DRINK_PREPARED_TOPIC, DrinkPreparedEvent.builder()
            .beerOrderLineDTO(event.getBeerOrderLineDTO())
            .build());

    }

}
