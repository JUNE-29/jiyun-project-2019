package june.book.handler;

import java.util.Scanner;
import june.book.domain.Reading;

public class ReadingHandler {

  Reading[] reading = new Reading[READING_SIZE];
  int readingCount = 0;

  public static Scanner keyboard;
  public static final int READING_SIZE = 100;

  public void addReading() {
    Reading read = new Reading(); 

    System.out.print("번호? ");
    read.no = keyboard.nextInt();

    keyboard.nextLine();

    System.out.print("도서명? ");
    read.title = keyboard.nextLine();

    System.out.print("지은이? ");
    read.author = keyboard.nextLine();

    System.out.print("출판사? ");
    read.publisher = keyboard.nextLine();

    System.out.print("카테고리? ");
    read.categories = keyboard.nextLine();

    System.out.print("출판 년도? ");
    read.publishedDate = keyboard.nextLine();

    this.reading[this.readingCount++] = read;
    System.out.println("저장하였습니다.");
  }

  public void listReading() {
    for(int i = 0; i < this.readingCount; i++) {
      Reading r = this.reading[i];
      System.out.printf("%d, %s, %s, %s, %s, %s\n",
          r.no, r.title, r.author, r.publisher, r.categories, r.publishedDate);
    }
  }
}
