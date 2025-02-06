package com.ms.ecommerce.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "client-service",
        url = "${application.config.client-url}"
)
public interface ClientClient {

    @GetMapping("/{client-id}")
    Optional<ClientResponse> findClientById(@PathVariable("client-id") String clientId);
}
