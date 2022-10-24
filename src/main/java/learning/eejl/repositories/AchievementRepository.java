package learning.eejl.repositories;

import learning.eejl.models.Achievement;
import learning.eejl.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AchievementRepository extends JpaRepository<Achievement, Integer> {
  List<Achievement> findByUser(User user);
}
