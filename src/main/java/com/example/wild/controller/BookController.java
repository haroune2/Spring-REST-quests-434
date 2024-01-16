package com.example.wild.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.wild.entities.Book;
import com.example.wild.repositories.BookRepository;

@RestController

public class BookController {
    @Autowired
    BookRepository bookRepository;

    /**
     * CRUD
     * 
     * CREATE
     * READ
     * UPDATE
     * DELETE
     * 
     */

    // CREATE
    @PostMapping("/blogs")
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    // READ
    @GetMapping("/blogs")
    public List<Book> index() {
        return bookRepository.findAll();
    }

    @GetMapping("/blogs/{id}")
    public Book show(@PathVariable Long id) {
        return bookRepository.findById(id).get();
    }

    // UPDATE
    @PutMapping("/blogs/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book) {
        // getting blog
        Optional<Book> blogToUpdateOptional = bookRepository.findById(id);
        if (blogToUpdateOptional.isPresent()) {
            Book blogToUpdate = blogToUpdateOptional.get();
            blogToUpdate.setTitle(book.getTitle());
            blogToUpdate.setDescription(book.getDescription());
            return bookRepository.save(blogToUpdate);
        } else
            return null;

    }

    // DELETE
    @DeleteMapping("blogs/{id}")
    public void delete(@RequestParam Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            bookRepository.delete(bookOptional.get());
        }
    }

    //SEARCH
    @PostMapping("/blogs/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return bookRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }


}
