package com.ms.ecommerce.client;

import lombok.*;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Validated
public class Address {
    private String street;
    private String city;
    private String state;
    private String zip;
    private String houseNumber;
}
