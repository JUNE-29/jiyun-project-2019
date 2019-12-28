package june.book.handler;

import java.sql.Date;
import java.util.Scanner;
import june.book.domain.Recommend;

public class RecommendHandler {

  Recommend[] recommends;
  int recommendCount = 0;
  
  public Scanner input;
  
  public static final int RECOMMEND_SIZE = 100;
  
  public RecommendHandler(Scanner input) {
    this.input = input;
    this.recommends = new Recommend[RECOMMEND_SIZE];
  }

  public void addRecommend() {
    Recommend recommend = new Recommend();

    System.out.print("번호? ");
    recommend.no = input.nextInt();

    input.nextLine();

    System.out.print("카테고리? ");
    recommend.categories = input.nextLine();

    System.out.print("언어? ");
    recommend.language = input.nextLine();

    System.out.print("나이? ");
    recommend.age = input.nextLine();

    System.out.print("MBTI(성격)? ");
    recommend.character = input.nextLine();

    System.out.print("키워드(해시태그)? ");
    recommend.keyword = input.nextLine();

    recommend.date = new Date(System.currentTimeMillis());

    this.recommends[this.recommendCount++] = recommend;
    System.out.println("저장하였습니다.");
  }

  public void listRecommend() {
    for(int i = 0; i < this.recommendCount; i++) {
      Recommend rec = this.recommends[i];
      System.out.printf("%d, %s세, %s, %s, #%s, %s\n",
          rec.no, rec.age, rec.categories, rec.character, rec.keyword, rec.date);
    }
  }
}
