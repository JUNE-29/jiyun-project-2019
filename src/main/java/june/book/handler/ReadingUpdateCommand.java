package june.book.handler;

import java.util.List;
import june.book.domain.Reading;
import june.util.Prompt;

public class ReadingUpdateCommand implements Command {

  List<Reading> readingList;

  public Prompt prompt;

  public ReadingUpdateCommand(Prompt prompt, List<Reading> list) {
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


    Reading oldReading = this.readingList.get(index);
    Reading newReading = new Reading();

    newReading.setNo(oldReading.getNo());

    newReading.setTitle(prompt.inputString(String.format("도서명(%s)? ", oldReading.getTitle()),
        oldReading.getTitle()));

    newReading.setAuthor(prompt.inputString(String.format("지은이(%s)? ", oldReading.getAuthor()),
        oldReading.getAuthor()));

    newReading.setPublisher(prompt.inputString(
        String.format("출판사(%s)? ", oldReading.getPublisher()), oldReading.getPublisher()));

    newReading.setCategories(prompt.inputString(
        String.format("카테고리(%s)? ", oldReading.getCategories()), oldReading.getCategories()));

    newReading.setPublishedDate(
        prompt.inputString(String.format("출판 연도(%s)? ", oldReading.getPublishedDate()),
            oldReading.getPublishedDate()));

    if (oldReading.equals(newReading)) {
      System.out.println("읽을 도서 정보 변경을 취소했습니다.");
      return;
    }

    this.readingList.set(index, newReading);
    System.out.println("읽을 도서 정보 변경했습니다.");
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
