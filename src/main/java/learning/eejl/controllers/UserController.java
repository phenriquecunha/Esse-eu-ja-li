package learning.eejl.controllers;

import learning.eejl.dtos.LoginDto;
import learning.eejl.dtos.UserDto;
import learning.eejl.dtos.UserLoginDto;
import learning.eejl.models.User;
import learning.eejl.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  UserRepository userRepository;

  @PostMapping("/register")
  ResponseEntity<Object> createUser(@RequestBody UserDto userDto){
    var userExists = userRepository.findByEmail(userDto.getEmail());
    if(userExists.isPresent()){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already registered");
    }
    var user = new User();
    BeanUtils.copyProperties(userDto,user);
    return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
  }

  @PostMapping("/login")
  ResponseEntity<Object> loginUser(@RequestBody UserLoginDto userDto){
    var userExists = userRepository.findByEmail(userDto.getEmail());
    if(userExists.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
    var logReturn = new LoginDto();
    logReturn.setId(userExists.get().getId());
    if(userDto.getPass().equals(userExists.get().getPass())){
      logReturn.setAuth(true);
      return ResponseEntity.ok().body(logReturn);
    }
    logReturn.setAuth(false);
    return ResponseEntity.ok().body(logReturn);
  }
}
