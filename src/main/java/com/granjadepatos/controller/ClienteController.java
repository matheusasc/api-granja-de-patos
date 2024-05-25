package com.granjadepatos.controller;

import com.granjadepatos.model.ClienteModel;
import com.granjadepatos.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    ResponseEntity<List<ClienteModel>> getClientes() {
        List<ClienteModel> clientes = clienteService.getClientes();
        if (clientes.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clientes);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    ResponseEntity<ClienteModel> getCliente(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(clienteService.getCliente(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ClienteModel());
        }
    }

    @PostMapping
    ResponseEntity<ClienteModel> setCliente(@RequestBody ClienteModel cliente) {
        clienteService.setCliente(cliente);
        return ResponseEntity.created(null).body(cliente);
    }

    @PutMapping("/{id}")
    ResponseEntity<ClienteModel> setSituacao(@RequestBody ClienteModel cliente, @PathVariable Long id){
        try {
            return ResponseEntity.accepted().body(clienteService.putCliente(id, cliente));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ClienteModel());
        }
    }
}
