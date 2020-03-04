package june.book.handler;

import java.util.Scanner;
import june.book.domain.Recommendation;
import june.util.ArrayList;

public class RecommendationHandler {

  ArrayList<Recommendation> recommendationList;

  Scanner input;

  public RecommendationHandler(Scanner input) {
    this.input = input;
    this.recommendationList = new ArrayList<>();
  }

  public RecommendationHandler(Scanner input, int capacity) {
    this.input = input;
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

    System.out.print("번호? ");
    recommendation.setNo(input.nextInt());

    input.nextLine();

    System.out.print("카테고리? ");
    recommendation.setCategories(input.nextLine());

    System.out.print("나이? ");
    recommendation.setAge(input.nextLine());

    System.out.print("MBTI(성격)? ");
    recommendation.setCharacter(input.nextLine());

    System.out.print("키워드(해시태그)? ");
    recommendation.setKeyword(input.nextLine());

    this.recommendationList.add(recommendation);
    System.out.println("저장하였습니다.");
  }

  public void detailRecommendation() {
    System.out.print("번호? ");
    int no = input.nextInt();
    input.nextLine();

    int index = indexOfRecommendation(no);

    if (index == -1) {
      System.out.println("추천 도서 정보의 번호가 유효하지 않습니다.");
      return;
    }

    Recommendation recommendation = this.recommendationList.get(index);
    System.out.printf("번호: %d\n", recommendation.getNo());
    System.out.printf("카테고리: %s\n", recommendation.getCategories());
    System.out.printf("나이: %s\n", recommendation.getAge());
    System.out.printf("MBTI(성격): %s\n", recommendation.getCharacter());
    System.out.printf("키워드(해시태그):%s\n", recommendation.getKeyword());
  }

  public void updateRecommendation() {
    System.out.print("번호? ");
    int no = input.nextInt();
    input.nextLine();

    int index = indexOfRecommendation(no);

    if (index == -1) {
      System.out.println("추천 도서 정보의 번호가 유효하지 않습니다.");
      return;
    }


    boolean changed = false;

    String inputStr = null;

    Recommendation oldRecommendation = this.recommendationList.get(index);

    Recommendation newRecommendation = new Recommendation();

    newRecommendation.setNo(oldRecommendation.getNo());

    System.out.printf("카테고리(%s)? ", oldRecommendation.getCategories());
    inputStr = input.nextLine();
    if (inputStr.length() == 0) {
      newRecommendation.setCategories(oldRecommendation.getCategories());
    } else {
      newRecommendation.setCategories(inputStr);
      changed = true;
    }

    System.out.printf("나이(%s)? ", oldRecommendation.getAge());
    inputStr = input.nextLine();
    if (inputStr.length() == 0) {
      newRecommendation.setAge(oldRecommendation.getAge());
    } else {
      newRecommendation.setAge(inputStr);
      changed = true;
    }

    System.out.printf("MBTI(성격)(%s)? ", oldRecommendation.getCharacter());
    inputStr = input.nextLine();
    if (inputStr.length() == 0) {
      newRecommendation.setCharacter(oldRecommendation.getCharacter());
    } else {
      newRecommendation.setCharacter(inputStr);
      changed = true;
    }

    System.out.printf("키워드(해시태그)(%s)? ", oldRecommendation.getKeyword());
    inputStr = input.nextLine();
    if (inputStr.length() == 0) {
      newRecommendation.setKeyword(oldRecommendation.getKeyword());
    } else {
      newRecommendation.setKeyword(inputStr);
      changed = true;
    }

    if (changed) {
      this.recommendationList.set(index, newRecommendation);
      System.out.println("추천 도서 정보를 변경했습니다.");
    } else {
      System.out.println("추천 도서 정보의 변경을 취소했습니다.");
    }
  }

  public void deleteRecommendation() {
    System.out.print("번호? ");
    int no = input.nextInt();
    input.nextLine();

    int index = indexOfRecommendation(no);

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
