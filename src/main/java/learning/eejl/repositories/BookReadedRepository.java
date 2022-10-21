package learning.eejl.repositories;

import learning.eejl.models.BookReaded;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookReadedRepository extends JpaRepository<BookReaded, Integer> {

  List<BookReaded> findByUser_id(int id);
}
