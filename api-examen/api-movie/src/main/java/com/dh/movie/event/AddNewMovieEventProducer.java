package com.dh.movie.event;


import com.dh.movie.config.RabbitMQConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class AddNewMovieEventProducer {


    private final RabbitTemplate rabbitTemplate;

    public AddNewMovieEventProducer(RabbitTemplate rabbitTemplate) {

        this.rabbitTemplate = rabbitTemplate;
    }


    public void publishAddNewMovieEvent(AddNewMovieEventProducer.Data message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.TOPIC_ADD_NEW_MOVIE, message);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Data {
        private int id;
        private String name;
        private String genre;
    }
}
