package june.book;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import june.book.domain.Member;
import june.book.domain.Reading;
import june.book.domain.Recommendation;
import june.book.domain.Review;
import june.book.handler.Command;
import june.book.handler.MemberAddCommand;
import june.book.handler.MemberDeleteCommand;
import june.book.handler.MemberDetailCommand;
import june.book.handler.MemberListCommand;
import june.book.handler.MemberUpdateCommand;
import june.book.handler.ReadingAddCommand;
import june.book.handler.ReadingDeleteCommand;
import june.book.handler.ReadingDetailCommand;
import june.book.handler.ReadingListCommand;
import june.book.handler.ReadingUpdateCommand;
import june.book.handler.RecommendationAddCommand;
import june.book.handler.RecommendationDeleteCommand;
import june.book.handler.RecommendationDetailCommand;
import june.book.handler.RecommendationListCommand;
import june.book.handler.RecommendationUpdateCommand;
import june.book.handler.ReviewAddCommand;
import june.book.handler.ReviewDeleteCommand;
import june.book.handler.ReviewDetailCommand;
import june.book.handler.ReviewListCommand;
import june.book.handler.ReviewUpdateCommand;
import june.util.Prompt;

public class App {

  static Scanner keyboard = new Scanner(System.in);

  static Deque<String> commandStack = new ArrayDeque<>();
  static Queue<String> commandQueue = new LinkedList<>();

  public static void main(String[] args) {

    Prompt prompt = new Prompt(keyboard);
    HashMap<String, Command> commandMap = new HashMap<>();

    LinkedList<Reading> readingList = new LinkedList<>();
    commandMap.put("/reading/add", new ReadingAddCommand(prompt, readingList));
    commandMap.put("/reading/list", new ReadingListCommand(readingList));
    commandMap.put("/reading/detail", new ReadingDetailCommand(prompt, readingList));
    commandMap.put("/reading/update", new ReadingUpdateCommand(prompt, readingList));
    commandMap.put("/reading/delete", new ReadingDeleteCommand(prompt, readingList));

    ArrayList<Recommendation> recommendationList = new ArrayList<>();
    commandMap.put("/recommend/add", new RecommendationAddCommand(prompt, recommendationList));
    commandMap.put("/recommend/list", new RecommendationListCommand(recommendationList));
    commandMap.put("/recommend/detail",
        new RecommendationDetailCommand(prompt, recommendationList));
    commandMap.put("/recommend/update",
        new RecommendationUpdateCommand(prompt, recommendationList));
    commandMap.put("/recommend/delete",
        new RecommendationDeleteCommand(prompt, recommendationList));

    LinkedList<Review> reviewList = new LinkedList<>();
    commandMap.put("/review/add", new ReviewAddCommand(prompt, reviewList));
    commandMap.put("/review/list", new ReviewListCommand(reviewList));
    commandMap.put("/review/detail", new ReviewDetailCommand(prompt, reviewList));
    commandMap.put("/review/update", new ReviewUpdateCommand(prompt, reviewList));
    commandMap.put("/review/delete", new ReviewDeleteCommand(prompt, reviewList));

    LinkedList<Member> memberList = new LinkedList<>();
    commandMap.put("/member/add", new MemberAddCommand(prompt, memberList));
    commandMap.put("/member/list", new MemberListCommand(memberList));
    commandMap.put("/member/detail", new MemberDetailCommand(prompt, memberList));
    commandMap.put("/member/update", new MemberUpdateCommand(prompt, memberList));
    commandMap.put("/member/delete", new MemberDeleteCommand(prompt, memberList));

    String command;

    while (true) {
      System.out.print("\n명령> ");
      command = keyboard.nextLine();

      if (command.length() == 0)
        continue;

      if (command.equals("quit")) {
        System.out.println("종료됩니다.");
        break;

      } else if (command.equals("history")) {
        printCommandHistory(commandStack.iterator());
        continue;

      } else if (command.equals("history2")) {
        printCommandHistory(commandQueue.iterator());
        continue;
      }

      commandStack.push(command);
      commandQueue.offer(command);

      Command commandHandler = commandMap.get(command);

      if (commandHandler != null) {
        try {
          commandHandler.execute();
        } catch (Exception e) {
          System.out.printf("명령어 실행중 오류 발생: %s \n", e.getMessage());
        }
      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }
    }

    keyboard.close();
  }


  private static void printCommandHistory(Iterator<String> iterator) {
    int count = 0;
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
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
