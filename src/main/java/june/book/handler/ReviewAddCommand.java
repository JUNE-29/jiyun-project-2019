package june.book.handler;

import java.sql.Date;
import java.util.List;
import june.book.domain.Review;
import june.util.Prompt;

public class ReviewAddCommand implements Command {

  List<Review> reviewList;

  Prompt prompt;

  public ReviewAddCommand(Prompt prompt, List<Review> list) {
    this.prompt = prompt;
    this.reviewList = list;
  }

  @Override
  public void execute() {
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
}
