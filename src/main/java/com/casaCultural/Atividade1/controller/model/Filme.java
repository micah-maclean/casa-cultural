package com.casaCultural.Atividade1.controller.model;

public class Filme {
    int id;
    String titulo;
    String sinopse;
    String genero;
    int anoLancamento;
    
    public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }
    
    public String getTitulo() { return this.titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    
    public String getSinopse(){ return this.sinopse; }
    public void setSinopse(String sinopse) { this.sinopse = sinopse; }
    
    public String getGenero() { return this.genero; }
    public void setGenero(String genero) { this.genero = genero; }
    
    public int getAnoLancamento() { return this.anoLancamento; }
    public void setAnoLancamento(int anoLancamento) { this.anoLancamento = anoLancamento; }

    @Override
    public String toString() {
        return "Filme{" + "id=" + id + ", titulo=" + titulo + ", sinopse=" + sinopse + ", genero=" + genero + ", anoLancamento=" + anoLancamento + '}';
    }
}
