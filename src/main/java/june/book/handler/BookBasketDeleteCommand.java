package june.book.handler;

import java.util.List;
import june.book.domain.BookBasket;
import june.util.Prompt;

public class BookBasketDeleteCommand implements Command {
  List<BookBasket> bookBasketList;

  Prompt prompt;

  public BookBasketDeleteCommand(Prompt prompt, List<BookBasket> list) {
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
    this.bookBasketList.remove(index);
    System.out.println("즐겨찾는 도서를 삭제했습니다.");
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
