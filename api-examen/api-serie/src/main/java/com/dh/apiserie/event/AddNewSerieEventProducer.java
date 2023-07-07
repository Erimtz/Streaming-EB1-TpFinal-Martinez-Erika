package com.dh.apiserie.event;


import com.dh.apiserie.config.RabbitMQConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class AddNewSerieEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public AddNewSerieEventProducer(RabbitTemplate rabbitTemplate) {

        this.rabbitTemplate = rabbitTemplate;
    }


    public void publishAddNewSerieEvent(AddNewSerieEventProducer.Data message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.TOPIC_ADD_NEW_SERIE, message);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Data {
        private String id;
        private String name;
        private String genre;
    }

}
