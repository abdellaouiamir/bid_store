package com.ms.ecommerce.payment;

import com.ms.ecommerce.notification.NotificationProducer;
import com.ms.ecommerce.notification.PaymentNotification;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationProducer notificationProducer;
    public Integer createPayment(@Valid PaymentRequest request) {
        var payment = paymentRepository.save(paymentMapper.toPayment(request));
        // send notification
        notificationProducer.sendNotification(new PaymentNotification(
                request.amount(),
                request.paymentMethod(),
                request.client().firstName(),
                request.client().lastName(),
                request.client().email()
        ));
        return payment.getId();
    }
}
