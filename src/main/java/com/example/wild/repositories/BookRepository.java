package com.example.wild.repositories;

import java.util.List;

import com.example.wild.entities.Book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository  extends JpaRepository<Book,Long>{
    //Créer un BookRepository permettant de réaliser les 4 opérations CRUD + une opération de recherche par mot-clé 
    //contenu dans le titre ou la description
    List<Book> findByTitleContainingOrDescriptionContaining(String searchTerm, String searchTerm1);
    
}
