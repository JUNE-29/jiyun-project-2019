package june.book.handler;

import java.util.Scanner;
import june.book.domain.Reading;

public class ReadingHandler {

  ArrayList readingList;

  public Scanner input;

  public ReadingHandler(Scanner input) {
    this.input = input;
    readingList = new ArrayList();
  }

  public ReadingHandler(Scanner input, int capacity) {
    this.input = input;
    readingList = new ArrayList(capacity);
  }

  public void listReading() {
    Object[] arr = readingList.toArray();
    for (Object obj : arr) {
      Reading r = (Reading) obj;
      System.out.printf("%d, %s, %s, %s, %s, %s\n", r.getNo(), r.getTitle(), r.getAuthor(),
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

    System.out.print("출판 연도? ");
    read.setPublishedDate(input.nextLine());

    this.readingList.add(read);

    System.out.println("저장하였습니다.");
  }

  public void detailReading() {
    System.out.print("게시물 인덱스? ");
    int index = input.nextInt();
    input.nextLine();

    Reading reading = (Reading) this.readingList.get(index);

    if (reading == null) {
      System.out.println("게시물 인덱스가 유효하지 않습니다.");
      return;
    }

    System.out.printf("번호: %d\n", reading.getNo());
    System.out.printf("도서명: %s\n", reading.getTitle());
    System.out.printf("지은이: %s\n", reading.getAuthor());
    System.out.printf("출판사: %s\n", reading.getPublisher());
    System.out.printf("카테고리: %s\n", reading.getCategories());
    System.out.printf("출판 연도:%s\n", reading.getPublishedDate());

  }

}
