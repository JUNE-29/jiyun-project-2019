package june.book.handler;

import java.util.List;
import june.book.domain.Recommendation;
import june.util.Prompt;

public class RecommendationDeleteCommand implements Command {
  List<Recommendation> recommendationList;

  Prompt prompt;

  public RecommendationDeleteCommand(Prompt prompt, List<Recommendation> list) {
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
    this.recommendationList.remove(index);
    System.out.println("추천도서 정보를 삭제했습니다.");
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
