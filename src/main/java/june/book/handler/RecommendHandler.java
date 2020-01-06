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
    recommend.setNo(input.nextInt());

    input.nextLine();

    System.out.print("카테고리? ");
    recommend.setCategories(input.nextLine());

    System.out.print("언어? ");
    recommend.setLanguage(input.nextLine());

    System.out.print("나이? ");
    recommend.setAge(input.nextLine());

    System.out.print("MBTI(성격)? ");
    recommend.setCharacter(input.nextLine());

    System.out.print("키워드(해시태그)? ");
    recommend.setKeyword(input.nextLine());

    recommend.setDate(new Date(System.currentTimeMillis()));

    this.recommends[this.recommendCount++] = recommend;
    System.out.println("저장하였습니다.");
  }

  public void listRecommend() {
    for(int i = 0; i < this.recommendCount; i++) {
      Recommend rec = this.recommends[i];
      System.out.printf("%d, %s세, %s, %s, #%s, %s\n",
          rec.getNo(), rec.getAge(), rec.getCategories(),
          rec.getCharacter(), rec.getKeyword(), rec.getDate());
    }
  }
}
