package com.ansv.notificationsystem.service.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class RabbitMqSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;


    @Value("${spring.rabbitmq.exchange-received:#{null}}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey-received:#{null}}")
    private String routingkey;


    public void sender(String username) {
        rabbitTemplate.convertAndSend(exchange, routingkey, username);
    }

}
