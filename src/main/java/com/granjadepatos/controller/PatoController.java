package com.granjadepatos.controller;

import com.granjadepatos.DTO.PatoDTO;
import com.granjadepatos.model.PatoModel;
import com.granjadepatos.service.PatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patos")
public class PatoController {

    @Autowired
    private PatoService patoService;

    @GetMapping
    ResponseEntity<List<PatoModel>> getPatos() {
        List<PatoModel> patos = patoService.getPatos();
        if (patos.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(patos);
        return ResponseEntity.ok(patos);
    }

    @GetMapping("/{id}")
    ResponseEntity<PatoModel> getPato(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(patoService.getPato(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new PatoModel());
        }
    }

    @PostMapping
    public ResponseEntity<PatoModel> setPato(@RequestBody PatoDTO patoDTO) {
        PatoModel pato = new PatoModel();
        pato.setNome(patoDTO.getNome());
        pato.setValor(patoDTO.getValor());
        pato.setStatus(patoDTO.getStatus());
        PatoModel savedPato = patoService.setPato(pato, patoDTO.getMaeId());
        return ResponseEntity.created(null).body(savedPato);
    }

    @PutMapping("/{id}")
    ResponseEntity<PatoModel> putPato(@RequestBody PatoModel pato, @PathVariable Long id){
        try {
            return ResponseEntity.accepted().body(patoService.putPato(id, pato));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new PatoModel());
        }
    }
}
