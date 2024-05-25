package com.granjadepatos.repository;

import com.granjadepatos.model.PatoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatoRepository extends JpaRepository<PatoModel, Long> {

}
