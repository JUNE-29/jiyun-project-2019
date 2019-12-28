package june.book.handler;

import java.sql.Date;
import java.util.Scanner;
import june.book.domain.Recommend;

public class RecommendHandler {

  Recommend[] recommends = new Recommend[RECOMMEND_SIZE];
  int recommendCount = 0;
  
  public static Scanner keyboard;
  public static final int RECOMMEND_SIZE = 100;

  public void addRecommend() {
    Recommend recommend = new Recommend();

    System.out.print("번호? ");
    recommend.no = keyboard.nextInt();

    keyboard.nextLine();

    System.out.print("카테고리? ");
    recommend.categories = keyboard.nextLine();

    System.out.print("언어? ");
    recommend.language = keyboard.nextLine();

    System.out.print("나이? ");
    recommend.age = keyboard.nextLine();

    System.out.print("MBTI(성격)? ");
    recommend.character = keyboard.nextLine();

    System.out.print("키워드(해시태그)? ");
    recommend.keyword = keyboard.nextLine();

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
