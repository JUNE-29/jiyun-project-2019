package june.book.handler;

import java.util.List;
import june.book.domain.Recommendation;
import june.util.Prompt;

public class RecommendationUpdateCommand implements Command {
  List<Recommendation> recommendationList;

  Prompt prompt;

  public RecommendationUpdateCommand(Prompt prompt, List<Recommendation> list) {
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

    Recommendation oldRecommendation = this.recommendationList.get(index);
    Recommendation newRecommendation = new Recommendation();

    newRecommendation.setNo(oldRecommendation.getNo());

    newRecommendation.setCategories( //
        prompt.inputString(String.format("카테고리(%s)? ", oldRecommendation.getCategories()),
            oldRecommendation.getCategories()));

    newRecommendation.setAge( //
        prompt.inputString(String.format("나이(%s)? ", oldRecommendation.getAge()),
            oldRecommendation.getAge()));

    newRecommendation.setCharacter( //
        prompt.inputString(String.format("MBTI(성격)(%s)? ", oldRecommendation.getCharacter()),
            oldRecommendation.getCharacter()));

    newRecommendation.setKeyword( //
        prompt.inputString(String.format("키워드(해시태그)(%s)? ", oldRecommendation.getKeyword()),
            oldRecommendation.getKeyword()));

    if (oldRecommendation.equals(newRecommendation)) {
      System.out.println("추천 도서 정보의 변경을 취소했습니다.");
      return;
    }

    this.recommendationList.set(index, newRecommendation);
    System.out.println("추천 도서 정보를 변경했습니다.");
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
