package june.book.handler;

import java.util.Scanner;
import june.book.domain.Reading;

public class ReadingHandler {

  ReadingList readingList;
  
  public Scanner input;
  
  public ReadingHandler(Scanner input) {
    this.input = input;
    readingList = new ReadingList();
  }
  
  public ReadingHandler(Scanner input, int capacity) {
    this.input = input;
    readingList = new ReadingList(capacity);
  }

  public void listReading() {
    Reading[] reading = readingList.toArray();
    for(Reading r : reading) {
      System.out.printf("%d, %s, %s, %s, %s, %s\n",
          r.getNo(), r.getTitle(), r.getAuthor(),
          r.getPublisher(), r.getCategories(), r.getPublishedDate());
    }
  }

  public void addReading() {
    Reading read = new Reading(); 

    System.out.print("번호? ");
    read.setNo(input.nextInt());

    input.nextLine();

    System.out.print("도서명? ");
    read.setTitle(input.nextLine());

    System.out.print("지은이? ");
    read.setAuthor(input.nextLine());

    System.out.print("출판사? ");
    read.setPublisher(input.nextLine());

    System.out.print("카테고리? ");
    read.setCategories(input.nextLine());

    System.out.print("출판 년도? ");
    read.setPublishedDate(input.nextLine());

    readingList.add(read);
    System.out.println("저장하였습니다.");
  }

}
