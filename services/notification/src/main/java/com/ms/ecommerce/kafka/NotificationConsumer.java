package com.ms.ecommerce.kafka;

import com.ms.ecommerce.email.EmailService;
import com.ms.ecommerce.kafka.bid.BidWinner;
import com.ms.ecommerce.kafka.payment.PaymentNotification;
import com.ms.ecommerce.notification.Notification;
import com.ms.ecommerce.notification.NotificationRepository;
import com.ms.ecommerce.notification.NotificationType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentNotification(PaymentNotification paymentNotification) throws MessagingException {
        log.info("Received Payment Notification: {}", paymentNotification);
        notificationRepository.save(Notification.builder()
                .type(NotificationType.PAYMENT_NOTIFICATION)
                .notificationTime(LocalDateTime.now())
                .paymentNotification(paymentNotification)
                .build());
        // to do send email
        var clientName = paymentNotification.clientFirstName()+" "+paymentNotification.clientLastName();
        emailService.sendPaymentEmail(
                paymentNotification.clientEmail(),
                clientName,
                paymentNotification.amount(),
                paymentNotification.paymentMethod()
        );
    }
    @KafkaListener(topics = "bid-topic")
    public void consumeBidWinnerNotification(BidWinner bidWinner) throws MessagingException {
        log.info("Received Bid Winner Notification: {}", bidWinner);
        notificationRepository.save(Notification.builder()
                .type(NotificationType.WINNER_NOTIFICATION)
                .notificationTime(LocalDateTime.now())
                .bidWinner(bidWinner)
                .build()
        );
        // to do send email
        var clientName = bidWinner.client().firstName()+" "+bidWinner.client().lastName();
        emailService.sendWinnerEmail(
                bidWinner.client().email(),
                clientName,
                bidWinner.bidAmount(),
                String.valueOf(bidWinner.bidId())
        );
    }

}
