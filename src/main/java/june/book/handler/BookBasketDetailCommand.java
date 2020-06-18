package june.book.handler;

import java.util.List;
import june.book.domain.BookBasket;
import june.util.Prompt;

public class BookBasketDetailCommand implements Command {
  List<BookBasket> bookBasketList;

  Prompt prompt;

  public BookBasketDetailCommand(Prompt prompt, List<BookBasket> list) {
    this.prompt = prompt;
    this.bookBasketList = list;
  }

  @Override
  public void execute() {
    int index = indexOfRecommendation(prompt.inputInt("번호? "));

    if (index == -1) {
      System.out.println("즐겨찾는 도서의 번호가 유효하지 않습니다.");
      return;
    }

    BookBasket bookBasket = this.bookBasketList.get(index);

    System.out.printf("번호: %d\n", bookBasket.getNo());
    System.out.printf("도서명: %s\n", bookBasket.getBookTitle());
    System.out.printf("지은이: %s\n", bookBasket.getAuthor());
    System.out.printf("출판사: %s\n", bookBasket.getPublisher());
    System.out.printf("출판 연도: %s\n", bookBasket.getPublishedDate());
    System.out.printf("카테고리: %s\n", bookBasket.getCategories());
    System.out.printf("즐겨찾기 한 이유: %s\n", bookBasket.getMemo());
  }

  private int indexOfRecommendation(int no) {
    for (int i = 0; i < this.bookBasketList.size(); i++) {
      if (this.bookBasketList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}
