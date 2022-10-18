package learning.eejl.models;

import javax.persistence.*;

@Entity
@Table(name = "tb_books_readed")
public class BookReaded {
  @Id
  @GeneratedValue
  Integer id;

  @Column(nullable = false)
  String book_id;

  @ManyToOne
  @JoinColumn(nullable = false)
  User user_id;
}
