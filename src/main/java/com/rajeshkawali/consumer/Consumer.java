package com.rajeshkawali.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rajeshkawali.config.RabbitMQConfig;
import com.rajeshkawali.model.OrderStatus;

@Component
public class Consumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void consumeMessageFromQueue(OrderStatus orderStatus) {
        System.out.println("Message recieved from queue : " + orderStatus);
    }
}
