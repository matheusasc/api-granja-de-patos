package com.granjadepatos.service;

import com.granjadepatos.model.ClienteModel;
import com.granjadepatos.model.PatoModel;
import com.granjadepatos.repository.ClienteRepository;
import com.granjadepatos.repository.PatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    List<ClienteModel> clientes = new ArrayList<>();

    @Autowired
    private ClienteRepository clienteRespository;

    @GetMapping
    public List<ClienteModel> getClientes() {
        return clienteRespository.findAll();
    }

    public ClienteModel getCliente(Long id) {
        Optional<ClienteModel> optionalCliente = clienteRespository.findById(id);
        if(optionalCliente.isPresent())
            return optionalCliente.get();
        return new ClienteModel();
    }

    public ClienteModel setCliente(ClienteModel cliente) {
        return clienteRespository.save(cliente);
    }

    public ClienteModel putCliente(Long id, ClienteModel cliente) {
        Optional<ClienteModel> optionalCliente = clienteRespository.findById(id);
        if(optionalCliente.isPresent()) {
            cliente.setId(id);
            return clienteRespository.save(cliente);
        }
        return new ClienteModel();
    }
}
