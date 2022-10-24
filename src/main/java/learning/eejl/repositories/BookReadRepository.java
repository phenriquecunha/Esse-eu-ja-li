package learning.eejl.repositories;

import learning.eejl.models.BookRead;
import learning.eejl.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookReadRepository extends JpaRepository<BookRead, Integer> {

  List<BookRead> findByUser(User user);
}
