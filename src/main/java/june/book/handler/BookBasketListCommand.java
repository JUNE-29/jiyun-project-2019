package june.book.handler;

import java.util.Iterator;
import java.util.List;
import june.book.domain.BookBasket;

public class BookBasketListCommand implements Command {
  List<BookBasket> bookBasketList;


  public BookBasketListCommand(List<BookBasket> list) {
    this.bookBasketList = list;
  }

  @Override
  public void execute() {
    Iterator<BookBasket> iterator = bookBasketList.iterator();
    while (iterator.hasNext()) {
      BookBasket basket = iterator.next();
      System.out.printf("%d, %s, %s\n", //
          basket.getNo(), basket.getBookTitle(), basket.getCategories());
    }
  }
}
