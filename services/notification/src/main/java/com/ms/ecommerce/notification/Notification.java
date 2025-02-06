package com.ms.ecommerce.notification;

import com.ms.ecommerce.kafka.bid.BidWinner;
import com.ms.ecommerce.kafka.payment.PaymentNotification;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Notification {
    @Id
    private String id;
    private NotificationType type;
    private LocalDateTime notificationTime;
    private BidWinner bidWinner;
    private PaymentNotification paymentNotification;
}
