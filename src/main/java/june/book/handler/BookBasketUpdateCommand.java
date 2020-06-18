package june.book.handler;

import java.util.List;
import june.book.domain.BookBasket;
import june.util.Prompt;

public class BookBasketUpdateCommand implements Command {
  List<BookBasket> bookBasketList;

  Prompt prompt;

  public BookBasketUpdateCommand(Prompt prompt, List<BookBasket> list) {
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

    BookBasket oldBasket = this.bookBasketList.get(index);
    BookBasket newBasket = new BookBasket();

    newBasket.setNo(oldBasket.getNo());

    newBasket.setBookTitle(prompt.inputString( //
        String.format("도서명(%s)?", oldBasket.getBookTitle()), oldBasket.getBookTitle()));

    newBasket.setAuthor(prompt.inputString( //
        String.format("지은이(%s)? ", oldBasket.getAuthor()), oldBasket.getAuthor()));

    newBasket.setPublisher(prompt.inputString( //
        String.format("출판사(%s)? ", oldBasket.getPublisher()), oldBasket.getPublisher()));

    newBasket.setPublishedDate( //
        prompt.inputString(String.format("출판 연도(%s)? ", oldBasket.getPublishedDate()),
            oldBasket.getPublishedDate()));

    newBasket.setCategories( //
        prompt.inputString(String.format("카테고리(%s)? ", oldBasket.getCategories()),
            oldBasket.getCategories()));

    newBasket.setMemo( //
        prompt.inputString(String.format("즐겨 찾기 한 이유(%s)? ", oldBasket.getMemo()),
            oldBasket.getMemo()));

    if (oldBasket.equals(newBasket)) {
      System.out.println("즐겨찾는 도서의 변경을 취소했습니다.");
      return;
    }

    this.bookBasketList.set(index, newBasket);
    System.out.println("즐겨찾는 도서를 변경했습니다.");
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
