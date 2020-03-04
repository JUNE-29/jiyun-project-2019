package june.book.handler;

import june.book.domain.Recommendation;
import june.util.ArrayList;
import june.util.Prompt;

public class RecommendationHandler {

  ArrayList<Recommendation> recommendationList;

  Prompt prompt;

  public RecommendationHandler(Prompt prompt) {
    this.prompt = prompt;
    this.recommendationList = new ArrayList<>();
  }

  public RecommendationHandler(Prompt prompt, int capacity) {
    this.prompt = prompt;
    recommendationList = new ArrayList<>(capacity);
  }

  public void listRecommendation() {
    Recommendation[] arr =
        this.recommendationList.toArray(new Recommendation[this.recommendationList.size()]);

    for (Recommendation rec : arr) {
      System.out.printf("%d, %s세, %s, %s, #%s, %s\n", rec.getNo(), rec.getAge(),
          rec.getCategories(), rec.getCharacter(), rec.getKeyword());
    }
  }


  public void addRecommendation() {
    Recommendation recommendation = new Recommendation();

    recommendation.setNo(prompt.inputInt("번호? "));

    recommendation.setCategories(prompt.inputString("카테고리? "));

    recommendation.setAge(prompt.inputString("나이? "));

    recommendation.setCharacter(prompt.inputString("MBTI(성격)? "));

    recommendation.setKeyword(prompt.inputString("키워드(해시태그)? "));

    this.recommendationList.add(recommendation);
    System.out.println("저장하였습니다.");
  }

  public void detailRecommendation() {

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

  public void updateRecommendation() {

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

  public void deleteRecommendation() {

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
