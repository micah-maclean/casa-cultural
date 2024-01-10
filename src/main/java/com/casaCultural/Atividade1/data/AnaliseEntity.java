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
@Table(name="analises")
public class AnaliseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Integer id;
    
    @NotNull(message="FilmeID obrigatório")
    private Integer filmeId;
    
    @NotBlank(message="Descrição obrigatória")
    private String descricao;
    
    @NotNull(message="Nota obrigatória")
    private Integer nota;
    
}
