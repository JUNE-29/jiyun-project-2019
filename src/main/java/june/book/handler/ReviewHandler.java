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
    review.setNo(input.nextInt());
    input.nextLine();

    System.out.print("도서명? ");
    review.setBookTitle(input.nextLine());

    System.out.print("제목? ");
    review.setTitle(input.nextLine());

    System.out.print("내용? ");
    review.setContents(input.nextLine());

    System.out.print("이미지? ");
    review.setPhoto(input.nextLine());

    System.out.print("책에 대한 점수(5.0점만점)? ");
    review.setScore(input.nextFloat());
    input.nextLine();

    review.setDate(new Date(System.currentTimeMillis()));
    review.setViewCount(0);

    this.reviews[this.reviewCount++] = review;
    System.out.println("저장하였습니다.");
  }
  public void listReview() {
    for(int i = 0; i < this.reviewCount; i++) {
      Review rev = this.reviews[i];
      System.out.printf("%d, %s, 제목: %s, %1.1f점, %s, %d\n",
          rev.getNo(), rev.getBookTitle(), rev.getTitle(), rev.getScore(), 
          rev.getDate(), rev.getViewCount());
    }
  }

  public void detailReview() {
    System.out.print("게시물 번호? ");
    int no = input.nextInt();
    input.nextLine();

    Review review =  null;
    for(int i = 0; i < this.reviewCount; i++) {
      if(this.reviews[i].getNo() == no) {
        review = this.reviews[i];
        break;
      }
    }
    if (review == null) {
      System.out.println("게시물 번호가 유효하지 않습니다.");
      return;
    }
    System.out.printf("번호: %d\n", review.getNo());
    System.out.printf("도서명: %s\n", review.getBookTitle());
    System.out.printf("제목: %s\n", review.getTitle());
    System.out.printf("평가: %1.1f점\n", review.getScore());
    System.out.printf("등록일: %s\n", review.getDate());
    System.out.printf("조회수: %s\n", review.getViewCount());
  }
}
