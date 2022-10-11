package learning.eejl.repositories;

import learning.eejl.models.BookReaded;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReadedRepository extends JpaRepository<BookReaded, Integer> {
}
