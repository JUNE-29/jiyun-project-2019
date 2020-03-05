package june.book.handler;

import june.book.domain.Reading;
import june.util.AbstractList;
import june.util.Prompt;

public class ReadingHandler {

  AbstractList<Reading> readingList;

  public Prompt prompt;

  public ReadingHandler(Prompt prompt, AbstractList<Reading> list) {
    this.prompt = prompt;
    this.readingList = list;
  }

  public void listReading() {

    Reading[] arr = this.readingList.toArray(new Reading[this.readingList.size()]);
    for (Reading r : arr) {
      System.out.printf("%d, %s, %s, %s, %s, %s\n", r.getNo(), r.getTitle(), r.getAuthor(),
          r.getPublisher(), r.getCategories(), r.getPublishedDate());
    }
  }

  public void addReading() {
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

  public void detailReading() {

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

  public void updateReading() {

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

  public void deleteReading() {

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
