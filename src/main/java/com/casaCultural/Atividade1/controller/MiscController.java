package com.casaCultural.Atividade1.controller;
import com.casaCultural.Atividade1.controller.model.Analise;
import com.casaCultural.Atividade1.controller.model.Filme;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller 
public class MiscController { 
    private final List<Filme> filmes = new ArrayList(); 
    private final List<Analise> analises = new ArrayList(); 

    @GetMapping("/")
    public String mostrarHome(Model model) {
        return "redirect:/lista-filme"; 
    }
    @GetMapping("/lista-filme")
    public String mostrarListaFilmes(Model model) {
        model.addAttribute("filmes", filmes); 
        return "lista-filme";
    }
    @GetMapping("/detalhes-filme")
    public String mostrarListaAnalise(Model model, @RequestParam(defaultValue="0", required=true)int id) {
        Filme filme = filmes.get(id);
        model.addAttribute("filme", filme); 
        model.addAttribute("analise", new Analise());
        List<Analise> analiseComMesmoFilmeId = analises.stream().filter(a -> a.getFilmeId() == id ).collect(Collectors.toList());
        model.addAttribute("analises", analiseComMesmoFilmeId);
        return "detalhes-filme";
    }
    @GetMapping("/cadastro-filme")
    public String mostrarCadastroFilme(Model model) {
        model.addAttribute("filme", new Filme()); 
        return "cadastro-filme";
    }
    @PostMapping("/cadastro-filme") 
    public String processarFormularioFilme(@ModelAttribute Filme filme, Model model) { 
        filme.setId(filmes.size());
        filmes.add(filme);
        return "redirect:/lista-filme"; 
    }
    @PostMapping("/cadastrar-analise") 
    public String processarFormularioAnalise(@ModelAttribute Analise analise, Model model) { 
        analise.setId(analises.size());
        analises.add(analise);
        return "redirect:/detalhes-filme?id=" + analise.getFilmeId(); 
    }
} 