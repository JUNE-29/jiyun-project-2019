package june.book.handler;

import java.util.Scanner;
import june.book.domain.Reading;

public class ReadingHandler {

  Reading[] reading;
  int readingCount = 0;

  Scanner input;
  
  public static final int READING_SIZE = 100;

  public ReadingHandler(Scanner input) {
    this.input = input;
    this.reading = new Reading[READING_SIZE];
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

    this.reading[this.readingCount++] = read;
    System.out.println("저장하였습니다.");
  }

  public void listReading() {
    for(int i = 0; i < this.readingCount; i++) {
      Reading r = this.reading[i];
      System.out.printf("%d, %s, %s, %s, %s, %s\n",
          r.getNo(), r.getTitle(), r.getAuthor(),
          r.getPublisher(), r.getCategories(), r.getPublishedDate());
    }
  }
}
