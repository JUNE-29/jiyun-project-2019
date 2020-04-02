package june.book.handler;

import java.util.List;
import june.book.domain.Reading;
import june.util.Prompt;

public class ReadingDeleteCommand implements Command {

  List<Reading> readingList;

  public Prompt prompt;

  public ReadingDeleteCommand(Prompt prompt, List<Reading> list) {
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

    this.readingList.remove(index);
    System.out.println("읽을 도서 정보를 삭제했습니다.");
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
