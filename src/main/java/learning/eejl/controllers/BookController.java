package learning.eejl.controllers;

import learning.eejl.dtos.FieldFilter;
import learning.eejl.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class BookController {
  @Autowired
  BookService bookService;

  //test Feign
  @GetMapping
  Object callFeign(){
    return bookService.searchBooks("code", FieldFilter.LIST);
  }
}
