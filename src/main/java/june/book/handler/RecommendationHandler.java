package june.book.handler;

import java.sql.Date;
import java.util.Scanner;
import june.book.domain.Recommendation;

public class RecommendationHandler {

  ArrayList recommendationList;

  Scanner input;

  public RecommendationHandler(Scanner input) {
    this.input = input;
    this.recommendationList = new ArrayList();
  }

  public RecommendationHandler(Scanner input, int capacity) {
    this.input = input;
    recommendationList = new ArrayList(capacity);
  }

  public void listRecommendation() {
    Object[] arr = recommendationList.toArray();
    for (Object obj : arr) {
      Recommendation rec = (Recommendation) obj;
      System.out.printf("%d, %s세, %s, %s, #%s, %s\n", rec.getNo(), rec.getAge(),
          rec.getCategories(), rec.getCharacter(), rec.getKeyword(), rec.getDate());
    }
  }


  public void addRecommendation() {
    Recommendation recommend = new Recommendation();

    System.out.print("번호? ");
    recommend.setNo(input.nextInt());

    input.nextLine();

    System.out.print("카테고리? ");
    recommend.setCategories(input.nextLine());

    System.out.print("나이? ");
    recommend.setAge(input.nextLine());

    System.out.print("MBTI(성격)? ");
    recommend.setCharacter(input.nextLine());

    System.out.print("키워드(해시태그)? ");
    recommend.setKeyword(input.nextLine());

    recommend.setDate(new Date(System.currentTimeMillis()));

    this.recommendationList.add(recommend);
    System.out.println("저장하였습니다.");
  }

  public void detailRecommendation() {
    System.out.print("게시물 인덱스? ");
    int index = input.nextInt();
    input.nextLine();

    Recommendation recommend = (Recommendation) this.recommendationList.get(index);

    if (recommend == null) {
      System.out.println("게시물 인덱스가 유효하지 않습니다.");
      return;
    }

    System.out.printf("번호: %d\n", recommend.getNo());
    System.out.printf("카테고리: %s\n", recommend.getCategories());
    System.out.printf("나이: %s\n", recommend.getAge());
    System.out.printf("MBTI(성격): %s\n", recommend.getCharacter());
    System.out.printf("키워드(해시태그):%s\n", recommend.getKeyword());

  }

}
