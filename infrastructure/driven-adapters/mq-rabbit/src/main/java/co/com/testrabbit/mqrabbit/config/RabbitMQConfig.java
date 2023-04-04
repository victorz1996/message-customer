package co.com.testrabbit.mqrabbit.config;

import co.com.testrabbit.messagelistener.MessageListener;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    @Value("${broker.exchange}")
    private String directExchangeName;

    @Value("${broker.queueName}")
    private String queueName;
    @Value("${broker.queueNameImpares}")
    private String queueNameImpares;

    @Value("${broker.routingKey}")
    private String routingKey;

    @Value("${broker.routingKeyImpares}")
    private String routingKeyImpares;

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(directExchangeName);
    }

    @Bean
    Queue queue() {
        return QueueBuilder.durable(queueName).build();
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(routingKey);
    }

    @Bean
    Queue queueImpares() {
        return QueueBuilder.durable(queueNameImpares).build();
    }

    @Bean
    Binding bindingImpares() {
        return BindingBuilder.bind(queueImpares()).to(exchange()).with(routingKeyImpares);
    }

    @Bean
    MessageListener receiver() {
        return new MessageListener();
    }
}
