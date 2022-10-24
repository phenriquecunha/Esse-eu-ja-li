package learning.eejl.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tb_books_read")
@Data
public class BookRead {
  @Id
  @GeneratedValue
  Integer id;

  @Column(nullable = false)
  String bookId;

  @ManyToOne
  @JoinColumn(nullable = false)
  User user;
}
