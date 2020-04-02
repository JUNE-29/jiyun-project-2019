package june.book.handler;

import java.util.List;
import june.book.domain.Recommendation;
import june.util.Prompt;

public class RecommendationAddCommand implements Command {
  List<Recommendation> recommendationList;

  Prompt prompt;

  public RecommendationAddCommand(Prompt prompt, List<Recommendation> list) {
    this.prompt = prompt;
    this.recommendationList = list;
  }

  @Override
  public void execute() {
    Recommendation recommendation = new Recommendation();

    recommendation.setNo(prompt.inputInt("번호? "));

    recommendation.setCategories(prompt.inputString("카테고리? "));

    recommendation.setAge(prompt.inputString("나이? "));

    recommendation.setCharacter(prompt.inputString("MBTI(성격)? "));

    recommendation.setKeyword(prompt.inputString("키워드(해시태그)? "));

    this.recommendationList.add(recommendation);
    System.out.println("저장하였습니다.");
  }
}
