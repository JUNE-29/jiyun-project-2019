package june.book.handler;

import java.sql.Date;
import java.util.List;
import june.book.domain.Review;
import june.util.Prompt;

public class ReviewUpdateCommand implements Command {

  List<Review> reviewList;

  Prompt prompt;

  public ReviewUpdateCommand(Prompt prompt, List<Review> list) {
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

  private int indexOfRecommendation(int no) {
    for (int i = 0; i < this.reviewList.size(); i++) {
      if (this.reviewList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}
