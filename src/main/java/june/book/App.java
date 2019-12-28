package june.book;

import java.util.Scanner;
import june.book.handler.ReadingHandler;
import june.book.handler.RecommendHandler;
import june.book.handler.ReviewHandler;

public class App {

  static Scanner keyboard = new Scanner(System.in);

  public static void main(String[] args) {

    ReviewHandler.keyboard = keyboard;
    RecommendHandler.keyboard = keyboard;
    ReadingHandler.keyboard = keyboard;

    ReviewHandler reviewHandler1 = new ReviewHandler();
    ReviewHandler reviewHandler2 = new ReviewHandler();
    ReviewHandler reviewHandler3 = new ReviewHandler();
    
    RecommendHandler recommendHandler1 = new RecommendHandler();
    
    ReadingHandler readingHandler1 = new ReadingHandler();
    
    
    
    String command;

    do{
      System.out.print("\n명령> ");
      command = keyboard.nextLine();

      switch (command) {
        case "/reading/add" :
          readingHandler1.addReading();
          break;

        case "/reading/list":
          readingHandler1.listReading();
          break;

        case "/recommend/add":
          recommendHandler1.addRecommend();
          break;

        case "/recommend/list":
          recommendHandler1.listRecommend();
          break;

        case "/review/add":
          reviewHandler1.addReview();
          break;

        case "/review/list":
          reviewHandler1.listReview();
          break;
          
        case "/review/detail":
          reviewHandler1.detailReview();
          break;
          
        case "/review2/add":
          reviewHandler2.addReview();
          break;

        case "/review2/list":
          reviewHandler2.listReview();
          break;
          
        case "/review2/detail":
          reviewHandler2.detailReview();
          break;
          
        case "/review3/add":
          reviewHandler3.addReview();
          break;

        case "/review3/list":
          reviewHandler3.listReview();
          break;
          
        case "/review3/detail":
          reviewHandler3.detailReview();
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
