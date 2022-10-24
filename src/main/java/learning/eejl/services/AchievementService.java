package learning.eejl.services;

import learning.eejl.models.Achievement;
import learning.eejl.models.BookRead;
import learning.eejl.models.User;
import learning.eejl.repositories.AchievementRepository;
import learning.eejl.repositories.BookReadRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Data
public class AchievementService {
  @Autowired
  AchievementRepository achievementRepository;

  @Autowired
  BookReadRepository bookReadRepository;

  List<String> getAchievements(User user){
    var achievements = achievementRepository.findByUser(user);
    return achievements.stream().map(Achievement::getName).toList();
  }
  void check(User user){
    var books = bookReadRepository.findByUser(user);
    var idList = books.stream().map(BookRead::getBookId);
  }
}
