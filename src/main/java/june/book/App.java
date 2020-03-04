package june.book;

import java.util.Scanner;
import june.book.handler.ReadingHandler;
import june.book.handler.RecommendationHandler;
import june.book.handler.ReviewHandler;
import june.util.Prompt;

public class App {

  static Scanner keyboard = new Scanner(System.in);

  public static void main(String[] args) {

    Prompt prompt = new Prompt(keyboard);

    ReadingHandler readingHandler = new ReadingHandler(prompt);
    RecommendationHandler recommendHandler = new RecommendationHandler(prompt);
    ReviewHandler reviewHandler = new ReviewHandler(prompt);


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

        case "/reading/update":
          readingHandler.updateReading();
          break;

        case "/reading/delete":
          readingHandler.deleteReading();
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

        case "/recommend/update":
          recommendHandler.updateRecommendation();
          break;

        case "/recommend/delete":
          recommendHandler.deleteRecommendation();
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

        case "/review/update":
          reviewHandler.updateReview();
          break;

        case "/review/delete":
          reviewHandler.deleteReview();
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
