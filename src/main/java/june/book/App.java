package june.book;

import java.util.Scanner;
import june.book.handler.ReadingHandler;
import june.book.handler.RecommendationHandler;
import june.book.handler.ReviewHandler;

public class App {

  static Scanner keyboard = new Scanner(System.in);

  public static void main(String[] args) {


    ReviewHandler reviewHandler1 = new ReviewHandler(keyboard);
    ReviewHandler reviewHandler2 = new ReviewHandler(keyboard, 1000);
    ReviewHandler reviewHandler3 = new ReviewHandler(keyboard);

    RecommendationHandler recommendHandler1 = new RecommendationHandler(keyboard);

    ReadingHandler readingHandler1 = new ReadingHandler(keyboard);


    String command;

    do {
      System.out.print("\n명령> ");
      command = keyboard.nextLine();

      switch (command) {
        case "/reading/add":
          readingHandler1.addReading();
          break;

        case "/reading/list":
          readingHandler1.listReading();
          break;

        case "/reading/detail":
          readingHandler1.detailReading();
          break;

        case "/recommend/add":
          recommendHandler1.addRecommendation();
          break;

        case "/recommend/list":
          recommendHandler1.listRecommendation();
          break;

        case "/recommend/detail":
          recommendHandler1.detailRecommendation();
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
          if (!command.equalsIgnoreCase("quit")) {
            System.out.println("실행할 수 없는 명령입니다.");
          }
      }
    } while (!command.equalsIgnoreCase("quit"));

    System.out.println("종료됩니다. 안녕히가십시오.");

    keyboard.close();
  }
}
