package june.book.handler;

import java.util.List;
import june.book.domain.Recommendation;
import june.util.Prompt;

public class RecommendationDetailCommand implements Command {
  List<Recommendation> recommendationList;

  Prompt prompt;

  public RecommendationDetailCommand(Prompt prompt, List<Recommendation> list) {
    this.prompt = prompt;
    this.recommendationList = list;
  }

  @Override
  public void execute() {
    int index = indexOfRecommendation(prompt.inputInt("번호? "));

    if (index == -1) {
      System.out.println("추천 도서 정보의 번호가 유효하지 않습니다.");
      return;
    }

    Recommendation recommendation = this.recommendationList.get(index);
    System.out.printf("번호: %d\n", recommendation.getNo());
    System.out.printf("카테고리: %s\n", recommendation.getCategories());
    System.out.printf("나이: %s\n", recommendation.getAge());
    System.out.printf("MBTI(성격): %s\n", recommendation.getCharacter());
    System.out.printf("키워드(해시태그): %s\n", recommendation.getKeyword());
  }

  private int indexOfRecommendation(int no) {
    for (int i = 0; i < this.recommendationList.size(); i++) {
      if (this.recommendationList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}
