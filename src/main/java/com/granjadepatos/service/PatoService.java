package com.granjadepatos.service;

import com.granjadepatos.DTO.PatoDTO;
import com.granjadepatos.model.PatoModel;
import com.granjadepatos.repository.PatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatoService {

    List<PatoModel> patos = new ArrayList<>();

    @Autowired
    private PatoRepository patoRepository;

    @GetMapping
    public List<PatoModel> getPatos() {
        return patoRepository.findAll();
    }

    public PatoModel getPato(Long id) {
        Optional<PatoModel> optionalPato = patoRepository.findById(id);
        if(optionalPato.isPresent())
            return optionalPato.get();
        return new PatoModel();
    }

    public PatoModel setPato(PatoModel pato, Long maeId) {
        if (maeId != null) {
            PatoModel mae = patoRepository.findById(maeId).orElse(null);
            pato.setMae(mae);
        }
        return patoRepository.save(pato);
    }

    public PatoModel putPato(Long id, PatoModel pato) {
        Optional<PatoModel> optionalPato = patoRepository.findById(id);
        if(optionalPato.isPresent()) {
            pato.setId(id);
            return patoRepository.save(pato);
        }
        return new PatoModel();
    }

}
