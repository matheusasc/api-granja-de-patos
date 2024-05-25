package com.granjadepatos.repository;

import com.granjadepatos.model.VendaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<VendaModel, Long> {
}
