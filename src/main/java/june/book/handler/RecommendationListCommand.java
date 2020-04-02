package june.book.handler;

import java.util.Iterator;
import java.util.List;
import june.book.domain.Recommendation;

public class RecommendationListCommand implements Command {
  List<Recommendation> recommendationList;


  public RecommendationListCommand(List<Recommendation> list) {
    this.recommendationList = list;
  }

  @Override
  public void execute() {
    Iterator<Recommendation> iterator = recommendationList.iterator();
    while (iterator.hasNext()) {
      Recommendation rec = iterator.next();
      System.out.printf("%d, %s세, %s, %s, #%s\n", rec.getNo(), rec.getAge(), rec.getCategories(),
          rec.getCharacter(), rec.getKeyword());
    }
  }
}
