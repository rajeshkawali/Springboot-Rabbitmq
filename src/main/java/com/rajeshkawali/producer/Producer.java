package com.rajeshkawali.producer;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajeshkawali.config.RabbitMQConfig;
import com.rajeshkawali.model.Order;
import com.rajeshkawali.model.OrderStatus;

@RestController
@RequestMapping("/order")
public class Producer {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/{hotelName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String hotelName) {
        order.setId(UUID.randomUUID().toString());
        OrderStatus orderStatus = new OrderStatus(order, "PROCESS", "Your name is " + hotelName);
        template.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, orderStatus);
        return "Success !!";
    }
}
