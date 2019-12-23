package june.book;

import java.sql.Date;
import java.util.Scanner;

public class ReviewHandler {
  static class Review {
    int no;
    String bookTitle;
    String title;
    String contents;
    String photo ;
    Date date ;
    float score ;
    int viewCount ;
  }
  static final int REVIEW_SIZE = 100;
  static Review[] reviews = new Review [REVIEW_SIZE];
  static int reviewCount = 0;
  static Scanner keyboard;

  static void addReview() {
    Review review = new Review();

    System.out.print("번호? ");
    review.no = keyboard.nextInt();
    keyboard.nextLine();

    System.out.print("도서명? ");
    review.bookTitle = keyboard.nextLine();

    System.out.print("게시판 제목? ");
    review.title = keyboard.nextLine();

    System.out.print("게시판 내용? ");
    review.contents = keyboard.nextLine();

    System.out.print("이미지? ");
    review.photo = keyboard.nextLine();

    System.out.print("책에 대한 점수(5.0점만점)? ");
    review.score =keyboard.nextFloat();
    keyboard.nextLine();

    review.date = new Date(System.currentTimeMillis());
    review.viewCount = 0;

    reviews[reviewCount++] = review;
    System.out.println("저장하였습니다.");
  }
  static void listReview() {
    for(int i = 0; i < reviewCount; i++) {
      Review rev = reviews[i];
      System.out.printf("%d, %s, 제목: %s, %1.1f점, %s, %d\n",
          rev.no, rev.bookTitle, rev.title, rev.score, 
          rev.date, rev.viewCount);
    }
  }
}
