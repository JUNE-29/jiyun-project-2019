package june.book.handler;

import java.util.List;
import june.book.domain.Reading;
import june.util.Prompt;

public class ReadingDetailCommand implements Command {

  List<Reading> readingList;

  public Prompt prompt;

  public ReadingDetailCommand(Prompt prompt, List<Reading> list) {
    this.prompt = prompt;
    this.readingList = list;
  }

  @Override
  public void execute() {
    int index = indexOfReading(prompt.inputInt("번호? "));

    if (index == -1) {
      System.out.println("읽을 도서 정보의 번호가 유효하지 않습니다.");
      return;
    }

    Reading reading = this.readingList.get(index);
    System.out.printf("번호: %d\n", reading.getNo());
    System.out.printf("도서명: %s\n", reading.getTitle());
    System.out.printf("지은이: %s\n", reading.getAuthor());
    System.out.printf("출판사: %s\n", reading.getPublisher());
    System.out.printf("카테고리: %s\n", reading.getCategories());
    System.out.printf("출판 연도:%s\n", reading.getPublishedDate());
  }

  private int indexOfReading(int no) {
    for (int i = 0; i < this.readingList.size(); i++) {
      if (this.readingList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}
