package june.book.handler;

import java.util.List;
import june.book.domain.BookBasket;
import june.util.Prompt;

public class BookBasketAddCommand implements Command {
  List<BookBasket> bookBasketList;

  Prompt prompt;

  public BookBasketAddCommand(Prompt prompt, List<BookBasket> list) {
    this.prompt = prompt;
    this.bookBasketList = list;
  }

  @Override
  public void execute() {
    BookBasket bookBasket = new BookBasket();

    bookBasket.setNo(prompt.inputInt("번호? "));

    bookBasket.setBookTitle(prompt.inputString("도서명? "));

    bookBasket.setAuthor(prompt.inputString("지은이? "));

    bookBasket.setPublisher(prompt.inputString("출판사? "));

    bookBasket.setCategories(prompt.inputString("카테고리? "));

    bookBasket.setPublishedDate(prompt.inputString("출판 연도? "));

    bookBasket.setMemo(prompt.inputString("즐겨찾기 한 이유? "));

    this.bookBasketList.add(bookBasket);
    System.out.println("저장하였습니다.");
  }
}
