package com.casaCultural.Atividade1.service;

import com.casaCultural.Atividade1.data.AnaliseEntity;
import com.casaCultural.Atividade1.data.AnaliseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnaliseService {
    @Autowired
    AnaliseRepository analiseRepository;
    
    public AnaliseEntity criarAnalise(AnaliseEntity analise) {
        analise.setId(null);
        analiseRepository.save(analise);
        return analise;
    }
    
    public AnaliseEntity atualizarAnalise(Integer analiseID, AnaliseEntity analiseRequest) {
        AnaliseEntity analise = getAnaliseById(analiseID);
        
        analise.setDescricao(analiseRequest.getDescricao());
        analise.setNota(analiseRequest.getNota());
        analise.setFilmeId(analiseRequest.getFilmeId());
        
        analiseRepository.save(analise);
        
        return analise;
    }
    

    public AnaliseEntity getAnaliseById(Integer id) {
        return analiseRepository.findById(id).orElse(null);
    }
    
    public List<AnaliseEntity> getAnaliseByFilmeId(Integer filmeId) {
        return analiseRepository.findByFilmeId(filmeId);
    }

    public void deleteById(Integer id) {
        analiseRepository.deleteById(id);
    }

    public List<AnaliseEntity> listarTodasAnalises() {
        return analiseRepository.findAll();
    }   
}
