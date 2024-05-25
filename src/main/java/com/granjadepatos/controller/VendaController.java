package com.granjadepatos.controller;

import com.granjadepatos.model.VendaModel;
import com.granjadepatos.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @GetMapping
    ResponseEntity<List<VendaModel>> getCompras() {
        List<VendaModel> vendas = vendaService.getVendas();
        if (vendas.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(vendas);
        return ResponseEntity.ok(vendas);
    }

    @GetMapping("/{id}")
    ResponseEntity<VendaModel> getVenda(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(vendaService.getVenda(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new VendaModel());
        }
    }

    @PostMapping
    ResponseEntity<VendaModel> setVenda(@RequestParam Long clienteId, @RequestParam Long patoId) {
        VendaModel venda = vendaService.setVenda(clienteId, patoId);
        if (venda.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(venda);
        }
        return ResponseEntity.created(null).body(venda);
    }
}
