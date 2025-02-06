package com.ms.ecommerce.email;

import lombok.Getter;

public enum EmailTemplates {
    PAYMENT_NOTIFICATION("payment-notification.html", "Payment Confirmation"),
    WINNER_NOTIFICATION("winner-notification.html", "Winner");

    @Getter
    private final String template;
    @Getter
    private final String subject;
    EmailTemplates(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }
}
