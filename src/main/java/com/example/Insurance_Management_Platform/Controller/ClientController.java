package com.example.Insurance_Management_Platform.Controller;

import com.example.Insurance_Management_Platform.Data.Client;
import com.example.Insurance_Management_Platform.Service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // 1. GET /api/clients: Fetch all clients.
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    // 2. GET /api/clients/{id}: Fetch a specific client by ID.
    @GetMapping("/{id}")
    public Optional<Client> getClientById(@PathVariable Integer id) {
        return clientService.getClientById(id);
    }

    // 3. POST /api/clients: Create a new client.
    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }

    // 4. PUT /api/clients/{id}: Update a client's information.
    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Integer id, @RequestBody Client client) {
        return clientService.updateClient(id, client);
    }

    // 5. DELETE /api/clients/{id}: Delete a client.
    @DeleteMapping("/{id}")
    public String deleteClient(@PathVariable Integer id) {
         return clientService.deleteClient(id);
    }
}
