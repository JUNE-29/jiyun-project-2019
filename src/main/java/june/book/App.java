package june.book;

import java.util.Scanner;
import june.book.domain.Reading;
import june.book.domain.Recommendation;
import june.book.domain.Review;
import june.book.handler.ReadingHandler;
import june.book.handler.RecommendationHandler;
import june.book.handler.ReviewHandler;
import june.util.ArrayList;
import june.util.LinkedList;
import june.util.Prompt;
import june.util.Queue;
import june.util.Stack;

public class App {

  static Scanner keyboard = new Scanner(System.in);

  static Stack<String> commandStack = new Stack<>();
  static Queue<String> commandQueue = new Queue<>();

  public static void main(String[] args) {

    Prompt prompt = new Prompt(keyboard);

    LinkedList<Reading> readingList = new LinkedList<>();
    ReadingHandler readingHandler = new ReadingHandler(prompt, readingList);

    ArrayList<Recommendation> recommendationList = new ArrayList<>();
    RecommendationHandler recommendHandler = new RecommendationHandler(prompt, recommendationList);

    LinkedList<Review> reviewList = new LinkedList<>();
    ReviewHandler reviewHandler = new ReviewHandler(prompt, reviewList);


    String command;

    do {
      System.out.print("\n명령> ");
      command = keyboard.nextLine();

      if (command.length() == 0)
        continue;

      commandStack.push(command);
      commandQueue.offer(command);

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

        case "history":
          printCommandHistory();
          break;

        case "history2":
          printCommandHistory2();
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

  private static void printCommandHistory2() {
    Queue<String> historyQueue = commandQueue.clone();
    int count = 0;

    while (historyQueue.size() > 0) {
      System.out.println(historyQueue.poll());
      count++;

      if ((count % 5) == 0) {
        System.out.print(":");
        String str = keyboard.nextLine();
        if (str.equalsIgnoreCase("q")) {
          break;
        }
      }
    }
  }


  private static void printCommandHistory() {
    Stack<String> historyStack = commandStack.clone();
    int count = 0;
    while (!historyStack.empty()) {
      System.out.println(historyStack.pop());
      count++;

      if ((count % 5) == 0) {
        System.out.print(":");
        String str = keyboard.nextLine();
        if (str.equalsIgnoreCase("q")) {
          break;
        }
      }
    }
  }

}
