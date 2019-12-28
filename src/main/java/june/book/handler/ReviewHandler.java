package june.book.handler;

import java.sql.Date;
import java.util.Scanner;
import june.book.domain.Review;

public class ReviewHandler {

  Review[] reviews; 
  int reviewCount = 0;

  public Scanner input;
  
  public static final int REVIEW_SIZE = 100;
  
  public ReviewHandler(Scanner input) {
    this.input = input;
    this.reviews = new Review [REVIEW_SIZE];
  }

  public void addReview() {
    Review review = new Review();

    System.out.print("번호? ");
    review.no = input.nextInt();
    input.nextLine();

    System.out.print("도서명? ");
    review.bookTitle = input.nextLine();

    System.out.print("제목? ");
    review.title = input.nextLine();

    System.out.print("내용? ");
    review.contents = input.nextLine();

    System.out.print("이미지? ");
    review.photo = input.nextLine();

    System.out.print("책에 대한 점수(5.0점만점)? ");
    review.score =input.nextFloat();
    input.nextLine();

    review.date = new Date(System.currentTimeMillis());
    review.viewCount = 0;

    this.reviews[this.reviewCount++] = review;
    System.out.println("저장하였습니다.");
  }
  public void listReview() {
    for(int i = 0; i < this.reviewCount; i++) {
      Review rev = this.reviews[i];
      System.out.printf("%d, %s, 제목: %s, %1.1f점, %s, %d\n",
          rev.no, rev.bookTitle, rev.title, rev.score, 
          rev.date, rev.viewCount);
    }
  }

  public void detailReview() {
    System.out.print("게시물 번호? ");
    int no = input.nextInt();
    input.nextLine();

    Review review =  null;
    for(int i = 0; i < this.reviewCount; i++) {
      if(this.reviews[i].no == no) {
        review = this.reviews[i];
        break;
      }
    }
    if (review == null) {
      System.out.println("게시물 번호가 유효하지 않습니다.");
      return;
    }
    System.out.printf("번호: %d\n", review.no);
    System.out.printf("도서명: %s\n", review.bookTitle);
    System.out.printf("제목: %s\n", review.title);
    System.out.printf("평가: %1.1f점\n", review.score);
    System.out.printf("등록일: %s\n", review.date);
    System.out.printf("조회수: %s\n", review.viewCount);
  }
}
