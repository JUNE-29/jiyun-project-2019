package june.book;

import java.util.Scanner;
import june.book.handler.ReadingHandler;
import june.book.handler.RecommendationHandler;
import june.book.handler.ReviewHandler;

public class App {

  static Scanner keyboard = new Scanner(System.in);

  public static void main(String[] args) {


    ReadingHandler readingHandler = new ReadingHandler(keyboard);
    RecommendationHandler recommendHandler = new RecommendationHandler(keyboard);
    ReviewHandler reviewHandler = new ReviewHandler(keyboard);


    String command;

    do {
      System.out.print("\n명령> ");
      command = keyboard.nextLine();

      switch (command) {
        case "/reading/add":
          readingHandler.addReading();
          break;

        case "/reading/list":
          readingHandler.listReading();
          break;

        case "/reading/detail":
          readingHandler.detailReading();
          break;

        case "/recommend/add":
          recommendHandler.addRecommendation();
          break;

        case "/recommend/list":
          recommendHandler.listRecommendation();
          break;

        case "/recommend/detail":
          recommendHandler.detailRecommendation();
          break;

        case "/review/add":
          reviewHandler.addReview();
          break;

        case "/review/list":
          reviewHandler.listReview();
          break;

        case "/review/detail":
          reviewHandler.detailReview();
          break;

        default:
          if (!command.equalsIgnoreCase("quit")) {
            System.out.println("실행할 수 없는 명령입니다.");
          }
      }
    } while (!command.equalsIgnoreCase("quit"));

    System.out.println("종료됩니다. 안녕히가십시오.");

    keyboard.close();
  }
}
