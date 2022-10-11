package learning.eejl.repositories;

import learning.eejl.models.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementRepository extends JpaRepository<Achievement, Integer> {
}
