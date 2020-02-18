package june.book.handler;

import java.sql.Date;
import java.util.Scanner;
import june.book.domain.Review;
import june.util.ArrayList;

public class ReviewHandler {

  ArrayList<Review> reviewList;

  Scanner input;

  public ReviewHandler(Scanner input) {
    this.input = input;
    this.reviewList = new ArrayList<>();
  }

  public ReviewHandler(Scanner input, int capacity) {
    this.input = input;
    this.reviewList = new ArrayList<>(capacity);
  }

  public void listReview() {
    Review[] arr = this.reviewList.toArray(new Review[reviewList.size()]);

    for (Review rev : arr) {
      System.out.printf("%d, %s, 제목: %s, %1.1f점, %s, %d\n", rev.getNo(), rev.getBookTitle(),
          rev.getTitle(), rev.getScore(), rev.getDate(), rev.getViewCount());
    }
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

    reviewList.add(review);
    System.out.println("저장하였습니다.");
  }

  public void detailReview() {
    System.out.print("게시물 인덱스? ");
    int index = input.nextInt();
    input.nextLine();

    Review review = this.reviewList.get(index);

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
