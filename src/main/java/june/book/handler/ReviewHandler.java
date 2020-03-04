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
    System.out.print("번호? ");
    int no = input.nextInt();
    input.nextLine();

    int index = indexOfRecommendation(no);
    if (index == -1) {
      System.out.println("읽은 도서 정보의 번호가 유효하지 않습니다.");
      return;
    }

    Review review = this.reviewList.get(index);
    System.out.printf("번호: %d\n", review.getNo());
    System.out.printf("도서명: %s\n", review.getBookTitle());
    System.out.printf("제목: %s\n", review.getTitle());
    System.out.printf("내용: %s\n", review.getContents());
    System.out.printf("이미지: %s\n", review.getPhoto());
    System.out.printf("평가: %1.1f점\n", review.getScore());
    System.out.printf("등록일: %s\n", review.getDate());
    System.out.printf("조회수: %s\n", review.getViewCount());
  }

  public void updateReview() {

    System.out.print("번호? ");
    int no = input.nextInt();
    input.nextLine();

    int index = indexOfRecommendation(no);
    if (index == -1) {
      System.out.println("읽은 도서 정보의 번호가 유효하지 않습니다.");
      return;
    }

    boolean changed = false;
    String inputStr = null;

    Review oldReview = this.reviewList.get(index);
    Review newReview = new Review();

    newReview.setNo(oldReview.getNo());

    System.out.printf("도서명(%s)? ", oldReview.getBookTitle());
    inputStr = input.nextLine();
    if (inputStr.length() == 0) {
      newReview.setBookTitle(oldReview.getBookTitle());
    } else {
      newReview.setBookTitle(inputStr);
      changed = true;
    }

    System.out.printf("제목(%s)? ", oldReview.getTitle());
    inputStr = input.nextLine();
    if (inputStr.length() == 0) {
      newReview.setTitle(oldReview.getTitle());
    } else {
      newReview.setTitle(inputStr);
      changed = true;
    }

    System.out.printf("내용(%s)? ", oldReview.getContents());
    inputStr = input.nextLine();
    if (inputStr.length() == 0) {
      newReview.setContents(oldReview.getContents());
    } else {
      newReview.setContents(inputStr);
      changed = true;
    }


    System.out.printf("내용(%s)? ", oldReview.getPhoto());
    inputStr = input.nextLine();
    if (inputStr.length() == 0) {
      newReview.setPhoto(oldReview.getPhoto());
    } else {
      newReview.setPhoto(inputStr);
      changed = true;
    }

    System.out.printf("평가(%1.1f점)? ", oldReview.getScore());
    inputStr = input.nextLine();
    if (inputStr.length() == 0) {
      newReview.setScore(oldReview.getScore());
    } else {
      newReview.setScore(Float.parseFloat(inputStr));
      changed = true;
    }

    newReview.setDate(new Date(System.currentTimeMillis()));
    newReview.setViewCount(oldReview.getViewCount());

    if (changed) {
      this.reviewList.set(index, newReview);
      System.out.println("읽은 도서 정보를 변경했습니다.");
    } else {
      System.out.println("읽은 도서 정보의 변경을 취소했습니다.");
    }
  }

  public void deleteReview() {
    System.out.print("번호? ");
    int no = input.nextInt();
    input.nextLine();

    int index = indexOfRecommendation(no);
    if (index == -1) {
      System.out.println("읽은 도서 정보의 번호가 유효하지 않습니다.");
      return;
    }

    this.reviewList.remove(index);
    System.out.println("읽은 도서 정보를 삭제했습니다.");
  }

  private int indexOfRecommendation(int no) {
    for (int i = 0; i < this.reviewList.size(); i++) {
      if (this.reviewList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }

}
