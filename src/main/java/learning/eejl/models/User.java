package learning.eejl.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tb_user")
@Data
public class User{
  @Id
  @GeneratedValue
  Integer id;

  @Column(nullable = false)
  String name;

  @Column(nullable = false, unique = true)
  String email;

  @Column(nullable = false)
  String pass;

  @Column(nullable = false)
  int points=0;
}
