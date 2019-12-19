package june.book;

import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);

    class Reading{
      int no;
      String title;
      String author;
      String publisher;
      String categories;
      String publishedDate;
    }

    final int SIZE = 100;

    Reading[] books = new Reading[SIZE];

    int count = 0;

    for (int i = 0; i < SIZE; i++) {

      Reading book = new Reading(); 

      count++;

      System.out.print("번호? ");
      book.no = keyboard.nextInt();

      keyboard.nextLine();

      System.out.print("도서명? ");
      book.title = keyboard.nextLine();

      System.out.print("지은이? ");
      book.author = keyboard.nextLine();

      System.out.print("출판사? ");
      book.publisher = keyboard.nextLine();

      System.out.print("카테고리? ");
      book.categories = keyboard.nextLine();

      System.out.print("출판 년도? ");
      book.publishedDate = keyboard.nextLine();

      System.out.println();

      books[i] = book;

      System.out.print("계속 입력하시겠습니까?(Y/n) ");
      String response = keyboard.nextLine();
      if (!response.equalsIgnoreCase("y")) {
        break;
      } 
    }

    System.out.println();

    for(int i = 0; i < count; i++) {
      Reading book = books[i];
      System.out.printf("%d, %s, %s, %s, %s, %s\n",
          book.no, book.title, book.author, book.publisher, 
          book.categories, book.publishedDate);
    }
    keyboard.close();
  }
}
