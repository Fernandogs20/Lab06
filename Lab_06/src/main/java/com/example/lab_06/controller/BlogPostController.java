package com.example.lab_06.controller;

import com.example.lab_06.entity.BlogPost;
import com.example.lab_06.repository.BlogPostRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class BlogPostController {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @GetMapping
    public String listarPosts(Model model) {
        List<BlogPost> posts = blogPostRepository.findAll();
        model.addAttribute("posts", posts);
        return "lista"; // Cambiado de "posts/lista" a "lista"
    }

    @GetMapping("/nuevo")
    public String nuevoPost(Model model) {
        model.addAttribute("post", new BlogPost());
        return "formulario";
    }

    @PostMapping("/guardar")
    public String guardarPost(@ModelAttribute("post") @Valid BlogPost post, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "formulario";
        }
        blogPostRepository.save(post);
        attr.addFlashAttribute("msgExito", "Entrada guardada con éxito");
        return "redirect:/posts";
    }

    @GetMapping("/{id}")
    public String verPost(@PathVariable Long id, Model model) {
        BlogPost post = blogPostRepository.findById(id).orElseThrow();
        model.addAttribute("post", post);
        return "ver";
    }

    @GetMapping("/editar/{id}")
    public String editarPost(@PathVariable Long id, Model model) {
        BlogPost post = blogPostRepository.findById(id).orElseThrow();
        model.addAttribute("post", post);
        return "formulario";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarPost(@PathVariable Long id, RedirectAttributes attr) {
        blogPostRepository.deleteById(id);
        attr.addFlashAttribute("msgError", "Entrada eliminada");
        return "redirect:/posts";
    }
}

