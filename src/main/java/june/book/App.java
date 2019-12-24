package june.book;

import java.sql.Date;
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

    String command;

    do{
      System.out.print("\n명령> ");
      command = keyboard.nextLine();

      switch (command) {
        case "/reading/add" :
          ReadingHandler.addReading();
          break;

        case "/reading/list":
          ReadingHandler.listReading();
          break;

        case "/recommend/add":
          RecommendHandler.addRecommend();
          break;

        case "/recommend/list":
          RecommendHandler.listRecommend();
          break;

        case "/review/add":
          ReviewHandler.addReview();
          break;

        case "/review/list":
          ReviewHandler.listReview();
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
