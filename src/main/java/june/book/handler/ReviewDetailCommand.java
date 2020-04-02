package june.book.handler;

import java.util.List;
import june.book.domain.Review;
import june.util.Prompt;

public class ReviewDetailCommand implements Command {

  List<Review> reviewList;

  Prompt prompt;

  public ReviewDetailCommand(Prompt prompt, List<Review> list) {
    this.prompt = prompt;
    this.reviewList = list;
  }

  @Override
  public void execute() {
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

  private int indexOfRecommendation(int no) {
    for (int i = 0; i < this.reviewList.size(); i++) {
      if (this.reviewList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}
