package june.book.handler;

import java.util.List;
import june.book.domain.Review;
import june.util.Prompt;

public class ReviewDeleteCommand implements Command {

  List<Review> reviewList;

  Prompt prompt;

  public ReviewDeleteCommand(Prompt prompt, List<Review> list) {
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
