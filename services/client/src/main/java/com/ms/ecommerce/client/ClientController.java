package com.ms.ecommerce.client;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<String> createClient(@RequestBody @Valid ClientRequest request) {
        return ResponseEntity.ok(clientService.createClient(request));
    }

    @PutMapping
    public ResponseEntity<Void> updateClient(@RequestBody @Valid ClientRequest request) {
        clientService.updateClient(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<ClientResponse>> getAllClients() {
        return ResponseEntity.ok(clientService.findAllClients());
    }

    @GetMapping("/exits/{client-id}")
    public ResponseEntity<Boolean> existById(@PathVariable("client-id") String clientId) {
        return ResponseEntity.ok(clientService.existById(clientId));
    }

    @GetMapping("/{client-id}")
    public ResponseEntity<ClientResponse> findById(@PathVariable("client-id") String clientId) {
        return ResponseEntity.ok(clientService.findById(clientId));
    }

    @DeleteMapping("/{client-id}")
    public ResponseEntity<Void> deleteById(@PathVariable("client-id") String clientId) {
        clientService.deleteById(clientId);
        return ResponseEntity.accepted().build();
    }
}
