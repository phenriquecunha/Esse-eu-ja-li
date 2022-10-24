package learning.eejl.controllers;

import learning.eejl.dtos.*;
import learning.eejl.models.BookRead;
import learning.eejl.models.User;
import learning.eejl.repositories.BookReadRepository;
import learning.eejl.repositories.UserRepository;
import learning.eejl.services.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  UserRepository userRepository;

  @Autowired
  BookReadRepository bookReadRepository;

  @Autowired
  BookService bookService;

  @Autowired
  PasswordEncoder encoder;

  @PostMapping("/register")
  ResponseEntity<Object> createUser(@RequestBody UserDto userDto){
    var user = userRepository.findByEmail(userDto.getEmail());
    if(user.isPresent()){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already registered");
    }
    var newUser = new User();
    BeanUtils.copyProperties(userDto,newUser);
    newUser.setPass(encoder.encode(newUser.getPass()));
    return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(newUser));
  }

  @PostMapping("/login")
  ResponseEntity<Object> loginUser(@RequestBody UserLoginDto userDto){
    var user = userRepository.findByEmail(userDto.getEmail());
    if(user.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
    var logReturn = new LoginDto();
    logReturn.setId(user.get().getId());
    if(encoder.matches(userDto.getPass(),user.get().getPass())){
      logReturn.setAuth(true);
      return ResponseEntity.ok().body(logReturn);
    }
    logReturn.setAuth(false);
    return ResponseEntity.ok().body(logReturn);
  }

  @GetMapping("/all")
  ResponseEntity<Object> getUsers(){
    return ResponseEntity.ok().body(userRepository.findAll());
  }

  @GetMapping("/id/{id}")
  ResponseEntity<Object> getUserById(@PathVariable int id){
    var user = userRepository.findById(id);
    if(user.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User id not found");
    }
    var response = new UserResponseDto();
    response.setUser(user.get());
    var booksRead = bookReadRepository.findByUser(user.get());
    var booksReadIds = booksRead.stream().map(BookRead::getBookId).collect(Collectors.toList());
    response.setBooksRead(booksReadIds);
    return ResponseEntity.ok().body(response);
  }

  @GetMapping("/ranking")
  ResponseEntity<Object> getRanking(){
    var users = userRepository.findAllByOrderByPointsDesc();
    return ResponseEntity.ok().body(users);
  }

  @GetMapping("/read")
  ResponseEntity<Object> setBookRead(@RequestBody ReadDto body){
    var user = userRepository.findById(body.getUser_id());
    if(user.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
    }
    var pages = bookService.getPages(body.getBook_id(), FieldFilter.PAGES).get("volumeInfo").get("pageCount");
    var points = user.get().getPoints() + (int)Math.ceil((double)pages/100);
    user.get().setPoints(points);
    userRepository.save(user.get());
    var br = new BookRead();
    br.setBookId(body.getBook_id());
    br.setUser(user.get());
    return ResponseEntity.ok().body(bookReadRepository.save(br));
  }

//  @GetMapping("/test/{id}")
//  Object test(@PathVariable String id){
//    int pages = bookService.getPages(id, FieldFilter.PAGES)
//    return (int)Math.ceil((double)pages/100);
//  }
}
