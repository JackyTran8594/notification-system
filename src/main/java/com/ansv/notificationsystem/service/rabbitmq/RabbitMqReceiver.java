package com.ansv.notificationsystem.service.rabbitmq;

import com.ansv.notificationsystem.dto.response.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RabbitMqReceiver implements RabbitListenerConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMqReceiver.class);

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage(UserDTO user){
        logger.info("User Details Received is.. " + user.getUsername());
//        MemberDTO memberDTO = new MemberDTO();
//        memberDTO.setEmail(user.getEmail());
//        memberDTO.setName(user.getFullName());
//        memberDTO.setEmail(user.getEmail());
//        memberDTO.setUsername(user.getUsername());
//        memberDTO.setPhone(user.getPhone_number());
//        memberService.save(memberDTO);
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }

}
