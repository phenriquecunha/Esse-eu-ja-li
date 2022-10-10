package learning.eejl.services;

import jdk.jshell.EvalException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "googleBooks",url = "https://www.googleapis.com/books/v1/volumes")
public interface BookService {

  @GetMapping("/")
  Object searchBooks(@RequestParam(value = "q") String searchTerm,
                     @RequestParam(value = "fields") String fields);

  @GetMapping("/{id}")
  Object findBookById(@PathVariable String id,
                      @RequestParam(value = "fields") String fields);

}
