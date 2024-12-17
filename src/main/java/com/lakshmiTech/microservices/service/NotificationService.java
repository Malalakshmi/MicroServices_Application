package com.lakshmiTech.microservices.service;

import com.lakshmiTech.microservices.order.event.OrderPlacedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;


@Service
public class NotificationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    private final JavaMailSender javaMailSender;

    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @KafkaListener(topics = "order-placed")
    public void listen( OrderPlacedEvent orderPlacedEvent){
        log.info("Got message from order-placed topic {} ", orderPlacedEvent);

        //send email to the customer
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom("ayyamlakshmiperumal420@gmail.com");
            messageHelper.setTo(orderPlacedEvent.getEmail().toString()); //bydefault it was using character type. so we are converting it into string
            messageHelper.setSubject(String.format("Your Order with OrderNumber %s is placed successfully", orderPlacedEvent.getOrderNumber()));
            messageHelper.setText(String.format("""
    Hi %s %s

    Your %s order with order number %s is now placed successfully.

    Best Regards,
    Lakshmi Shop
""",
                    orderPlacedEvent.getFirstName(),       // Replace %s with first name
                    orderPlacedEvent.getLastName(),        // Replace %s with last name
                    "order",                               // Replace with the type of order (like "purchase", "subscription", etc.)
                    orderPlacedEvent.getOrderNumber()));   // Replace %s with order number
        };
        try {
            javaMailSender.send(messagePreparator);
            log.info("Order Notifcation email sent!!");
        } catch (MailException e) {
            log.error("Exception occurred when sending mail", e);
            throw new RuntimeException("Exception occurred when sending mail to springshop@email.com", e);
        }
    }
}