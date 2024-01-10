package com.casaCultural.Atividade1.controller;
import com.casaCultural.Atividade1.data.AnaliseEntity;
import com.casaCultural.Atividade1.data.FilmeEntity;
import com.casaCultural.Atividade1.service.AnaliseService;
import com.casaCultural.Atividade1.service.FilmeService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
@Controller 
public class MiscController { 
    @Autowired 
    FilmeService filmeService;
    @Autowired 
    AnaliseService analiseService;

    @GetMapping("/")
    public String mostrarHome(Model model) {
        return "redirect:/lista-filme"; 
    }
    @GetMapping("/lista-filme")
    public String mostrarListaFilmes(Model model, @CookieValue(name="lightTheme", defaultValue="true")Boolean lightTheme) {
        model.addAttribute("filmes",  filmeService.listarTodosFilmes()); 
        model.addAttribute("lightTheme", lightTheme);
        return "lista-filme";
    }
    @GetMapping("/detalhes-filme")
    public String mostrarListaAnalise(Model model, @RequestParam(defaultValue="0", required=true)int id, @CookieValue(name="lightTheme", defaultValue="true")Boolean lightTheme) {
        FilmeEntity filme = filmeService.getFilmeById(id);
        model.addAttribute("filme", filme); 
        model.addAttribute("analise", new AnaliseEntity());
        model.addAttribute("atualizar", false);
        model.addAttribute("lightTheme", lightTheme);
        
        List<AnaliseEntity> analiseComMesmoFilmeId = analiseService.getAnaliseByFilmeId(id);
        model.addAttribute("analises", analiseComMesmoFilmeId);
        
        return "detalhes-filme";
    }
    @GetMapping("/cadastro-filme")
    public String mostrarCadastroFilme(Model model, @CookieValue(name="lightTheme", defaultValue="true")Boolean lightTheme) {
        FilmeEntity filme = new FilmeEntity();
        model.addAttribute("filme", filme);
        model.addAttribute("atualizar", false);
        model.addAttribute("lightTheme", lightTheme);
        
        return "cadastro-filme";
    }
    @PostMapping("/cadastro-filme") 
    public String processarFormularioFilme(@Valid @ModelAttribute("filme") FilmeEntity filme, BindingResult result) { 
         if (result.hasErrors()) {
            return "cadastro-filme";
         }

        filmeService.criarFilme(filme);
        return "redirect:/lista-filme"; 
    }
    
    @PostMapping("/cadastrar-analise") 
    public String processarFormularioAnalise(@Valid @ModelAttribute AnaliseEntity analise, BindingResult result) { 
        if (result.hasErrors()) {
           return "detalhes-filme";
        }
        analiseService.criarAnalise(analise);
        return "redirect:/detalhes-filme?id=" + analise.getFilmeId(); 
    }
    
    @GetMapping("/deletarAnalise/{filmeId}/{id}") 
    public String deletaAnalise(@PathVariable(value = "filmeId") Integer filmeId, @PathVariable(value = "id")Integer id) {
        analiseService.deleteById(id);
        return "redirect:/detalhes-filme?id=" + filmeId; 
    }
    
    @GetMapping("/deletarFilme/{id}") 
    public String deletaFilme(@PathVariable(value = "id")Integer id) { 
        filmeService.deleteById(id);
        return "redirect:/lista-filme";  
    }
    
    @GetMapping("/atualizarAnaliseForm/{filmeId}/{id}") 
    public String atualizarAnaliseForm(Model model, @PathVariable(value = "filmeId") Integer filmeId, @PathVariable(value = "id")Integer id, @CookieValue(name="lightTheme", defaultValue="true")Boolean lightTheme) {       
        AnaliseEntity analise = analiseService.getAnaliseById(id);
        
        model.addAttribute("analise", analise);
        model.addAttribute("lightTheme", lightTheme);
        
        return "/atualizar-analise"; 
    }
    
    @PostMapping("/atualizar-analise") 
    public String atualizarAnalise(@Valid @ModelAttribute("analise") AnaliseEntity analise, BindingResult result) { 
         if (result.hasErrors()) {
            return "cadastro-filme";
         }

        analiseService.atualizarAnalise(analise.getId(), analise);
        return "redirect:/detalhes-filme?id=" + analise.getFilmeId();
    }
    
    
    @GetMapping("/atualizarFilmeForm/{id}") 
    public String atualizarFilmeForm(Model model, @PathVariable(value = "id")Integer id, @CookieValue(name="lightTheme", defaultValue="true")Boolean lightTheme) { 
        FilmeEntity filme = filmeService.getFilmeById(id);
        
        model.addAttribute("filme", filme);
        model.addAttribute("atualizar", true);
        model.addAttribute("lightTheme", lightTheme);
        
        return "cadastro-filme";  
    }
    
    @PostMapping("/atualizar-filme") 
    public String atualizarFilme(@Valid @ModelAttribute("filme") FilmeEntity filme, BindingResult result) { 
         if (result.hasErrors()) {
            return "cadastro-filme";
         }

        filmeService.atualizarFilme(filme.getId(), filme);
        return "redirect:/lista-filme"; 
    }
    
    @GetMapping("/setTheme")
    public String atualizarTema(Model model, HttpServletResponse response, @CookieValue(name="lightTheme", defaultValue="true")Boolean lightTheme, @RequestHeader("Referer") String referer) {
        Cookie cookieTheme = new Cookie("lightTheme", lightTheme ? "false" : "true"); 
        response.addCookie(cookieTheme);
        
        return "redirect:/" + referer.replace("http://localhost:8080/", "");  
    }
} 