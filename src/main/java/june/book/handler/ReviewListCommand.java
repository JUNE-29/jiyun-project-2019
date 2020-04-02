package june.book.handler;

import java.util.Iterator;
import java.util.List;
import june.book.domain.Review;

public class ReviewListCommand implements Command {

  List<Review> reviewList;

  public ReviewListCommand(List<Review> list) {
    this.reviewList = list;
  }

  @Override
  public void execute() {
    Iterator<Review> iterator = reviewList.iterator();
    while (iterator.hasNext()) {
      Review rev = iterator.next();
      System.out.printf("%d, %s, 제목: %s, %1.1f점, %s, %d\n", rev.getNo(), rev.getBookTitle(),
          rev.getTitle(), rev.getScore(), rev.getDate(), rev.getViewCount());
    }
  }
}
