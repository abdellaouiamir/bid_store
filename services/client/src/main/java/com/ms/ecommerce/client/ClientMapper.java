package com.ms.ecommerce.client;

import org.springframework.stereotype.Service;

@Service
public class ClientMapper {
    public Client toClient(ClientRequest request) {
        if (request == null) {
            return null;
        }
        return Client.builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(request.address())
                .build();
    }

    public ClientResponse fromClient(Client client) {
        return new ClientResponse(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                client.getAddress()
        );
    }
}
