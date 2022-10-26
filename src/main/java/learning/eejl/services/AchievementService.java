package learning.eejl.services;

import learning.eejl.dtos.FieldFilter;
import learning.eejl.models.Achievement;
import learning.eejl.models.BookRead;
import learning.eejl.models.User;
import learning.eejl.repositories.AchievementRepository;
import learning.eejl.repositories.BookReadRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class AchievementService {
  @Autowired
  AchievementRepository achievementRepository;

  @Autowired
  BookReadRepository bookReadRepository;

  @Autowired
  BookService bookService;

  public List<String> getAchievements(User user){
    var achievements = achievementRepository.findByUser(user);
    return achievements.stream().map(Achievement::getName).toList();
  }
  public Object check(User user){
    var books = bookReadRepository.findByUser(user).stream().map(BookRead::getBookId).toList();
    List<String> categories = new ArrayList<>();
    books.forEach(id -> {
      var res = bookService.getCategories(id, FieldFilter.CATEGORIES);
      if(!res.isEmpty()){
        res.get("volumeInfo").get("categories")
            .forEach(p -> {
              categories.addAll(Arrays.stream(p.split(" / ")).toList());
            });
      }
    });
    //TODO save achievement if repeat category 5x
    return categories;
  }
}
