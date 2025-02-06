package com.ms.ecommerce.email;

import com.ms.ecommerce.kafka.PaymentMethod;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendPaymentEmail(String destinationEmail, String customerName, BigDecimal amount, PaymentMethod paymentMethod) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name()
        );
        mimeMessageHelper.setFrom("amir@gmail.com");
        final String templateName = EmailTemplates.PAYMENT_NOTIFICATION.getTemplate();
        Map<String, Object> model = new HashMap<>();
        model.put("customerName", customerName);
        model.put("amount", amount);
        model.put("paymentMethod", paymentMethod);
        model.put("destinationEmail", destinationEmail);

        Context context = new Context();
        context.setVariables(model);
        mimeMessageHelper.setSubject(EmailTemplates.PAYMENT_NOTIFICATION.getSubject());

        try {
            String htmlTemplate = templateEngine.process(templateName, context);
            mimeMessageHelper.setText(htmlTemplate, true);
            mimeMessageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info("Email sent successfully :: %s", destinationEmail);
        } catch (MessagingException e) {
            log.warn("Warining: can t sent email to %s error:"+e.getMessage(), destinationEmail);
        }
    }
    @Async
    public void sendWinnerEmail(String destinationEmail, String customerName, BigDecimal amount, String bidReference) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name()
        );
        mimeMessageHelper.setFrom("amir@gmail.com");
        final String templateName = EmailTemplates.WINNER_NOTIFICATION.getTemplate();
        Map<String, Object> model = new HashMap<>();
        model.put("customerName", customerName);
        model.put("amount", amount);
        model.put("bidReference", bidReference);
        model.put("destinationEmail", destinationEmail);

        Context context = new Context();
        context.setVariables(model);
        mimeMessageHelper.setSubject(EmailTemplates.WINNER_NOTIFICATION.getSubject());

        try {
            String htmlTemplate = templateEngine.process(templateName, context);
            mimeMessageHelper.setText(htmlTemplate, true);
            mimeMessageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info("Email sent successfully :: %s", destinationEmail);
        } catch (MessagingException e) {
            log.warn("Warining: can t sent email to %s error:" + e.getMessage(), destinationEmail);
        }
    }
}
