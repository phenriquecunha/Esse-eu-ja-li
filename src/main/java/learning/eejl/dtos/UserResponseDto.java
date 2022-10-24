package learning.eejl.dtos;

import learning.eejl.models.BookRead;
import learning.eejl.models.User;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDto {

  User user;
  List<String> booksRead;
  List<String> achievements;

}
