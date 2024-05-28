package com.granjadepatos.service;

import com.granjadepatos.enums.PatoStatus;
import com.granjadepatos.model.ClienteModel;
import com.granjadepatos.model.PatoModel;
import com.granjadepatos.model.VendaModel;
import com.granjadepatos.repository.ClienteRepository;
import com.granjadepatos.repository.PatoRepository;
import com.granjadepatos.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PatoRepository patoRepository;

    public List<VendaModel> getVendas() {
        return vendaRepository.findAll();
    }

    public VendaModel getVenda(Long id) {
        Optional<VendaModel> optionalCompra = vendaRepository.findById(id);
        return optionalCompra.orElse(new VendaModel());
    }

    public VendaModel setVenda(Long clienteId, Long patoId) {
        System.out.println("Cliente ID: " + clienteId);
        System.out.println("Pato ID: " + patoId);

        Optional<ClienteModel> optionalCliente = clienteRepository.findById(clienteId);
        Optional<PatoModel> optionalPato = patoRepository.findById(patoId);

        if (optionalCliente.isPresent() && optionalPato.isPresent()) {
            PatoModel pato = optionalPato.get();
            if (pato.getStatus() == PatoStatus.VENDIDO) {
                System.out.println("Pato já vendido");
                return new VendaModel();
            }

            VendaModel venda = new VendaModel();
            venda.setCliente(optionalCliente.get());
            venda.setPato(pato);

            pato.setStatus(PatoStatus.VENDIDO);
            patoRepository.save(pato);

            return vendaRepository.save(venda);
        }

        System.out.println("Cliente ou Pato não encontrado");
        return new VendaModel();
    }
}
