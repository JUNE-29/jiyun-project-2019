package june.book;

import java.sql.Date;
import java.util.Scanner;

public class RecommendHandler {
  static class Recommend{
    int no;
    String categories;
    String language;
    String age;
    String character;
    String keyword;
    Date date;
  }

  static final int RECOMMEND_SIZE = 100;
  static Recommend[] recommends = new Recommend[RECOMMEND_SIZE];
  static int recommendCount = 0;
  static Scanner keyboard;

  static void addRecommend() {
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

    recommends[recommendCount++] = recommend;
    System.out.println("저장하였습니다.");
  }

  static void listRecommend() {
    for(int i = 0; i < recommendCount; i++) {
      Recommend rec = recommends[i];
      System.out.printf("%d, %s세, %s, %s, #%s, %s\n",
          rec.no, rec.age, rec.categories, rec.character, rec.keyword, rec.date);
    }
  }
}
