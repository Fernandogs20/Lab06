package com.example.lab_06.repository;

import com.example.lab_06.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

    // Puedes agregar métodos personalizados si lo necesitas, por ejemplo:
    // List<BlogPost> findByAutor(String autor);
    // List<BlogPost> findByCategoria(String categoria);
}