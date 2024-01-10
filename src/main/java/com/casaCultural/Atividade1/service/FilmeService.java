package com.casaCultural.Atividade1.service;

import com.casaCultural.Atividade1.data.FilmeEntity;
import com.casaCultural.Atividade1.data.FilmeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmeService {
    @Autowired
    FilmeRepository filmeRepository;

    public FilmeEntity criarFilme(FilmeEntity filme) {
        filme.setId(null);
        filmeRepository.save(filme);
        return filme;
    }
    
    public FilmeEntity atualizarFilme(Integer filmeID, FilmeEntity filmeRequest) {
        FilmeEntity filme = getFilmeById(filmeID);
        
        filme.setSinopse(filmeRequest.getSinopse());
        filme.setAnoLancamento(filmeRequest.getAnoLancamento());
        filme.setTitulo(filmeRequest.getTitulo());
        filme.setGenero(filmeRequest.getGenero());
        
        filmeRepository.save(filme);
        return filme;
    }
    

    public FilmeEntity getFilmeById(Integer id) {
        return filmeRepository.findById(id).orElse(null);
    }

    public void deleteById(Integer id) {
        filmeRepository.deleteById(id);
    }

    public List<FilmeEntity> listarTodosFilmes() {
        return filmeRepository.findAll();
    }   
    
}   
