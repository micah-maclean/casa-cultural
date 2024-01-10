package com.casaCultural.Atividade1.controller;

import com.casaCultural.Atividade1.data.AnaliseEntity;
import com.casaCultural.Atividade1.service.AnaliseService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/analise") 
public class AnaliseController {
   @Autowired 
   AnaliseService analiseService; 
   
   @GetMapping("/listar") 
   public ResponseEntity<List> getAllAnalises() {
       List<AnaliseEntity> analises = analiseService.listarTodasAnalises();
       return new ResponseEntity<>(analises, HttpStatus.OK);
   } 
   
   @GetMapping("/listar/{filmeId}") 
   public ResponseEntity<List> getAnalisesByFilmeId(@PathVariable Integer filmeId) {
       List<AnaliseEntity> analises = analiseService.getAnaliseByFilmeId(filmeId);
       return new ResponseEntity<>(analises, HttpStatus.OK);
   } 
   
   @GetMapping("/pesquisar/{id}")
   public ResponseEntity<AnaliseEntity> getFilmeById(@PathVariable Integer id) {
       AnaliseEntity analise = analiseService.getAnaliseById(id);
       return new ResponseEntity<>(analise, HttpStatus.OK);
   }
   
   @PostMapping("/adicionar")
   public ResponseEntity<AnaliseEntity> addFilme(@Valid @RequestBody AnaliseEntity analise) {
       AnaliseEntity novoFilme = analiseService.criarAnalise(analise);
       return new ResponseEntity<>(novoFilme, HttpStatus.CREATED);
   }
   
   @PutMapping("/atualizar/{id}")
   public ResponseEntity<AnaliseEntity> atualizarFilme(@PathVariable Integer id, @Valid @RequestBody AnaliseEntity analise){
       AnaliseEntity analiseAtualizado = analiseService.atualizarAnalise(id, analise);
       return new ResponseEntity<>(analiseAtualizado, HttpStatus.OK);
   }
   
   @DeleteMapping("/deletar/{id}")
   public ResponseEntity deletarFilme(@PathVariable Integer id) {
       analiseService.deleteById(id);
       return new ResponseEntity<>(HttpStatus.OK);
   }
}
