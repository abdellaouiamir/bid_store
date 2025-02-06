package com.ms.ecommerce.client;

import com.ms.ecommerce.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository repository;
    private final ClientMapper mapper;
    public String createClient(ClientRequest request) {
        var client = repository.save(mapper.toClient(request));
        return client.getId();
    }

    public void updateClient(ClientRequest request) {
        var client = repository.findById(request.id()).orElseThrow(() -> new CustomerNotFoundException(
                String.format("Customer with id '%s' not found", request.id())
        ));
        mergeClient(client, request);
        repository.save(client);
    }

    private void mergeClient(Client client, ClientRequest request) {
        if(StringUtils.isNotBlank(request.firstName())){
            client.setFirstName(request.firstName());
        }
        if(StringUtils.isNotBlank(request.lastName())){
            client.setLastName(request.lastName());
        }
        if(StringUtils.isNotBlank(request.email())){
            client.setEmail(request.email());
        }
        if(request.address() != null){
            client.setAddress(request.address());
        }
    }

    public List<ClientResponse> findAllClients() {
        return repository.findAll()
                .stream()
                .map(mapper::fromClient)
                .collect(Collectors.toList());
    }

    public Boolean existById(String clientId) {
        //repository.findById(clientId).isPresent(); // return true if the client exist
        return repository.existsById(clientId);
    }

    public ClientResponse findById(String clientId) {
        return repository.findById(clientId)
                .map(mapper::fromClient)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Customer with id '%s' not found", clientId)));
    }

    public void deleteById(String clientId) {
        repository.deleteById(clientId);
    }
}
