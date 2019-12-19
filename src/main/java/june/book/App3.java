package june.book;

import java.sql.Date;
import java.util.Scanner;

public class App3 {

    public static void main(String[] args) {
      
      Scanner keyboard = new Scanner(System.in);
      
      class Review {
        int no;
        String bookTitle;
        String title;
        String contents;
        String photo ;
        Date date ;
        float score ;
        int viewCount ;
      }
      
      final int SIZE = 100;
      
      Review[] books = new Review [SIZE];
      
      int count = 0;
      
      for (int i = 0; i < SIZE; i++) {
        count++;
        
        Review book = new Review();
        
        System.out.print("번호? ");
        book.no = keyboard.nextInt();
        keyboard.nextLine();
        
        System.out.print("도서명? ");
        book.bookTitle = keyboard.nextLine();
        
        System.out.print("게시판 제목? ");
        book.title = keyboard.nextLine();
        
        System.out.print("게시판 내용? ");
        book.contents = keyboard.nextLine();
        
        System.out.print("이미지? ");
        book.photo = keyboard.nextLine();
        
        System.out.print("책에 대한 점수(5.0점만점)? ");
        book.score =keyboard.nextFloat();
        keyboard.nextLine();
        
        book.date = new Date(System.currentTimeMillis());
        book.viewCount = 0;
        
        books[i] = book;
        
        System.out.println();
        
        System.out.print("계속 입력하시겠습니까?(Y/n) ");
        String response = keyboard.nextLine();
        if(!response.equalsIgnoreCase("y")) {
          break;
        }
      }
      keyboard.close();
      
      System.out.println();
      
      for(int i = 0; i < count; i++) {
          Review book = books[i];
        System.out.printf("%d, %s, 제목: %s, %1.1f점, %s, %d\n",
            book.no, book.bookTitle, book.title, book.score, 
            book.date, book.viewCount);
      }
      
     }
   }
