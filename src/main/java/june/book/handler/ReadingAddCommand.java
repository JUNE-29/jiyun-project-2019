package june.book.handler;

import java.util.List;
import june.book.domain.Reading;
import june.util.Prompt;

public class ReadingAddCommand implements Command {

  List<Reading> readingList;

  public Prompt prompt;

  public ReadingAddCommand(Prompt prompt, List<Reading> list) {
    this.prompt = prompt;
    this.readingList = list;
  }

  @Override
  public void execute() {
    Reading read = new Reading();

    read.setNo(prompt.inputInt("번호? "));

    read.setTitle(prompt.inputString("도서명? "));

    read.setAuthor(prompt.inputString("지은이? "));

    read.setPublisher(prompt.inputString("출판사? "));

    read.setCategories(prompt.inputString("카테고리? "));

    read.setPublishedDate(prompt.inputString("출판 연도? "));

    this.readingList.add(read);

    System.out.println("저장하였습니다.");
  }
}
