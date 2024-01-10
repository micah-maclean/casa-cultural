package com.casaCultural.Atividade1.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table; 
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name="filmes")
public class FilmeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Integer id;
    
    @NotBlank(message="Título obrigatório")
    private String titulo;
    
    @NotBlank(message="Sinopse obrigatório")
    private String sinopse;
    
    @NotBlank(message="Genêro obrigatório")
    private String genero;
    
    @NotNull(message="Ano de lançamento obrigatório")
    private Integer anoLancamento;    
}
