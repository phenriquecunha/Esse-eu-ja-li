package learning.eejl.controllers;

import learning.eejl.dtos.FieldFilter;
import learning.eejl.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {
  @Autowired
  BookService bookService;

  @GetMapping
  ResponseEntity<Object> initialList(){
    return ResponseEntity.ok().body(bookService.searchBooks("code", FieldFilter.LIST));
  }

  @GetMapping("/s/{terms}")
  ResponseEntity<Object> searchBooks(@PathVariable String terms){
    return ResponseEntity.ok().body(bookService.searchBooks(terms, FieldFilter.LIST));
  }

  @GetMapping("/id/{id}")
  ResponseEntity<Object> getBook(@PathVariable String id){
    return ResponseEntity.ok().body(bookService.findBookById(id,FieldFilter.UNIQUE));
  }
}
