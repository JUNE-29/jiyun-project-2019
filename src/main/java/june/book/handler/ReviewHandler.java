package june.book.handler;

import java.sql.Date;
import june.book.domain.Review;
import june.util.AbstractList;
import june.util.Prompt;

public class ReviewHandler {

  AbstractList<Review> reviewList;

  Prompt prompt;

  public ReviewHandler(Prompt prompt, AbstractList<Review> list) {
    this.prompt = prompt;
    this.reviewList = list;
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

    review.setNo(prompt.inputInt("번호? "));

    review.setBookTitle(prompt.inputString("도서명? "));

    review.setTitle(prompt.inputString("제목? "));

    review.setContents(prompt.inputString("내용? "));

    review.setPhoto(prompt.inputString("이미지? "));

    review.setScore(prompt.inputFloat("책에 대한 점수(5.0점만점)? "));

    review.setDate(new Date(System.currentTimeMillis()));
    review.setViewCount(0);

    reviewList.add(review);

    System.out.println("저장하였습니다.");
  }

  public void detailReview() {

    int index = indexOfRecommendation(prompt.inputInt("번호? "));

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

    int index = indexOfRecommendation(prompt.inputInt("번호? "));

    if (index == -1) {
      System.out.println("읽은 도서 정보의 번호가 유효하지 않습니다.");
      return;
    }

    Review oldReview = this.reviewList.get(index);
    Review newReview = new Review();

    newReview.setNo(oldReview.getNo());

    newReview.setBookTitle(//
        prompt.inputString(String.format("도서명(%s)? ", oldReview.getBookTitle()),
            oldReview.getBookTitle()));

    newReview.setTitle(//
        prompt.inputString(String.format("제목(%s)? ", oldReview.getTitle()), oldReview.getTitle()));

    newReview.setContents(//
        prompt.inputString(String.format("내용(%s)? ", oldReview.getContents()),
            oldReview.getContents()));

    newReview.setPhoto(//
        prompt.inputString(String.format("이미지(%s)? ", oldReview.getPhoto()), oldReview.getPhoto()));

    newReview.setScore(//
        prompt.inputFloat(String.format("평가(%1.1f점)? ", oldReview.getScore()),
            oldReview.getScore()));

    newReview.setDate(new Date(System.currentTimeMillis()));
    newReview.setViewCount(oldReview.getViewCount());

    if (oldReview.equals(newReview)) {
      System.out.println("읽은 도서 정보의 변경을 취소했습니다.");
      return;
    }

    this.reviewList.set(index, newReview);
    System.out.println("읽은 도서 정보를 변경했습니다.");

  }

  public void deleteReview() {
    int index = indexOfRecommendation(prompt.inputInt("번호? "));

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
