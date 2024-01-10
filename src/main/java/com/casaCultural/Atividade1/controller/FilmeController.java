package com.casaCultural.Atividade1.controller;

import com.casaCultural.Atividade1.data.FilmeEntity;
import com.casaCultural.Atividade1.service.FilmeService;
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
@RequestMapping("/filme")  
public class FilmeController { 
   @Autowired 
   FilmeService filmeService; 
   
   @GetMapping("/listar") 
   public ResponseEntity<List> getAllFilmes() {
       List<FilmeEntity> filmes = filmeService.listarTodosFilmes();
       return new ResponseEntity<>(filmes, HttpStatus.OK);
   } 
   
   @GetMapping("/pesquisar/{id}")
   public ResponseEntity<FilmeEntity> getFilmeById(@PathVariable Integer id) {
       FilmeEntity filme = filmeService.getFilmeById(id);
       return new ResponseEntity<>(filme, HttpStatus.OK);
   }
   
   @PostMapping("/adicionar")
   public ResponseEntity<FilmeEntity> addFilme(@Valid @RequestBody FilmeEntity filme) {
       FilmeEntity novoFilme = filmeService.criarFilme(filme);
       return new ResponseEntity<>(novoFilme, HttpStatus.CREATED);
   }
   
   @PutMapping("/atualizar/{id}")
   public ResponseEntity<FilmeEntity> atualizarFilme(@PathVariable Integer id, @Valid @RequestBody FilmeEntity filme){
       FilmeEntity filmeAtualizado = filmeService.atualizarFilme(id, filme);
       return new ResponseEntity<>(filmeAtualizado, HttpStatus.OK);
   }
   
   @DeleteMapping("/deletar/{id}")
   public ResponseEntity deletarFilme(@PathVariable Integer id) {
       filmeService.deleteById(id);
       return new ResponseEntity<>(HttpStatus.OK);
   }
} 