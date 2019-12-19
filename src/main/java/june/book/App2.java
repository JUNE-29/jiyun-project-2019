package june.book;

import java.sql.Date;
import java.util.Scanner;

public class App2 {

  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);

    class Recommend{
      int no;
      String categories;
      String language;
      String age;
      String character;
      String keyword;
      Date date;
    }
    final int SIZE = 100;

    Recommend[] books = new Recommend[SIZE];

    int count = 0;

    for (int i = 0; i < SIZE; i++) {
      count++;
      
      Recommend book = new Recommend();
      
      System.out.print("번호? ");
      book.no = keyboard.nextInt();

      keyboard.nextLine();

      System.out.print("카테고리? ");
      book.categories = keyboard.nextLine();

      System.out.print("언어? ");
      book.language = keyboard.nextLine();

      System.out.print("나이? ");
      book.age = keyboard.nextLine();

      System.out.print("MBTI(성격)? ");
      book.character = keyboard.nextLine();

      System.out.print("키워드(해시태그)? ");
      book.keyword = keyboard.nextLine();

      book.date = new Date(System.currentTimeMillis());

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
      Recommend book = books[i];
      System.out.printf("%d, %s세, %s, %s, #%s, %s\n",
          book.no, book.age, book.categories,  book.character,
          book.keyword, book.date);
    }
    keyboard.close();
  }
}
