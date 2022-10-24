package learning.eejl.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tb_achievement")
@Data
public class Achievement {
  @Id
  @GeneratedValue
  Integer id;

  @Column(nullable = false)
  String name;

  @ManyToOne
  @JoinColumn(nullable = false)
  User user;
}
