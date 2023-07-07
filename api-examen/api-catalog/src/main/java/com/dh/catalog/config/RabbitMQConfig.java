package com.dh.catalog.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "backendExchange";
    public static final String TOPIC_ADD_NEW_SERIE = "com.dh.backend.addNewSerie";
    public static final String QUEUE_ADD_NEW_SERIE ="queueAddNewSerie";

    public static final String TOPIC_ADD_NEW_MOVIE = "com.dh.backend.addNewMovie";
    public static final String QUEUE_ADD_NEW_MOVIE ="queueAddNewMovie";

    @Bean
    public TopicExchange appExchange() {

        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue queueAddNewSerie(){

        return new Queue(QUEUE_ADD_NEW_SERIE);
    }

    @Bean
    public Queue queueAddNewMovie(){

        return new Queue(QUEUE_ADD_NEW_MOVIE);
    }

    @Bean
    public Binding declareBindingSpecificSerie(){
        return BindingBuilder.bind(queueAddNewSerie()).to(appExchange()).with(TOPIC_ADD_NEW_SERIE);
    }

    @Bean
    public Binding declareBindingSpecificMovie(){
        return BindingBuilder.bind(queueAddNewMovie()).to(appExchange()).with(TOPIC_ADD_NEW_MOVIE);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
