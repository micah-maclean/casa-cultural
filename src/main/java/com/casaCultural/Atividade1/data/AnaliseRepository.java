package com.casaCultural.Atividade1.data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnaliseRepository extends JpaRepository<AnaliseEntity, Integer> {

    public List<AnaliseEntity> findByFilmeId(Integer filmeId);
    
}
