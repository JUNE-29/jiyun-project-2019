package june.book;

import java.sql.Date;
import java.util.Scanner;

public class App {

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
    
    final int REVIEW_SIZE = 100;
    Review[] reviews = new Review [REVIEW_SIZE];
    int reviewCount = 0;
    
    
    class Recommend{
      int no;
      String categories;
      String language;
      String age;
      String character;
      String keyword;
      Date date;
    }
    
    final int RECOMMEND_SIZE = 100;
    Recommend[] recommends = new Recommend[RECOMMEND_SIZE];
    int recommendCount = 0;

    
    class Reading {
      int no;
      String title;
      String author;
      String publisher;
      String categories;
      String publishedDate;
    }
    
    final int READING_SIZE = 100;
    Reading[] reading = new Reading[READING_SIZE];
    int readingCount = 0;
    
    String command;
 
      do{
        System.out.print("\n명령> ");
        command = keyboard.nextLine();
        
        switch (command) {
          case "/reading/add" :
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

            reading[readingCount++] = read;
            System.out.println("저장하였습니다.");
            break;
            
          case "/reading/list":
            for(int i = 0; i < readingCount; i++) {
              Reading r = reading[i];
              System.out.printf("%d, %s, %s, %s, %s, %s\n",
                  r.no, r.title, r.author, r.publisher, r.categories, r.publishedDate);
            }
            break;
            
          case "/recommend/add":
            Recommend recommend = new Recommend();
            
            System.out.print("번호? ");
            recommend.no = keyboard.nextInt();

            keyboard.nextLine();

            System.out.print("카테고리? ");
            recommend.categories = keyboard.nextLine();

            System.out.print("언어? ");
            recommend.language = keyboard.nextLine();

            System.out.print("나이? ");
            recommend.age = keyboard.nextLine();

            System.out.print("MBTI(성격)? ");
            recommend.character = keyboard.nextLine();

            System.out.print("키워드(해시태그)? ");
            recommend.keyword = keyboard.nextLine();

            recommend.date = new Date(System.currentTimeMillis());

            recommends[recommendCount++] = recommend;
            System.out.println("저장하였습니다.");
            break;
            
          case "/recommend/list":
            for(int i = 0; i < recommendCount; i++) {
              Recommend rec = recommends[i];
              System.out.printf("%d, %s세, %s, %s, #%s, %s\n",
                  rec.no, rec.age, rec.categories, rec.character, rec.keyword, rec.date);
            }
            break;
            
          case "/review/add":
            Review review = new Review();
            
            System.out.print("번호? ");
            review.no = keyboard.nextInt();
            keyboard.nextLine();
            
            System.out.print("도서명? ");
            review.bookTitle = keyboard.nextLine();
            
            System.out.print("게시판 제목? ");
            review.title = keyboard.nextLine();
            
            System.out.print("게시판 내용? ");
            review.contents = keyboard.nextLine();
            
            System.out.print("이미지? ");
            review.photo = keyboard.nextLine();
            
            System.out.print("책에 대한 점수(5.0점만점)? ");
            review.score =keyboard.nextFloat();
            keyboard.nextLine();
            
            review.date = new Date(System.currentTimeMillis());
            review.viewCount = 0;
            
            reviews[reviewCount++] = review;
            System.out.println("저장하였습니다.");
            break;
            
          case "/review/list":
            for(int i = 0; i < reviewCount; i++) {
              Review rev = reviews[i];
            System.out.printf("%d, %s, 제목: %s, %1.1f점, %s, %d\n",
                rev.no, rev.bookTitle, rev.title, rev.score, 
                rev.date, rev.viewCount);
            }
            break;
            default:
              if(!command.equalsIgnoreCase("quit")) {
                System.out.println("실행할 수 없는 명령입니다.");
              }
        }
      } while(!command.equalsIgnoreCase("quit"));
   
       System.out.println("종료됩니다. 안녕히가십시오.");
    
    
   





    keyboard.close();
  }
}
