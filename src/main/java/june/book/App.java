package june.book;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import com.google.gson.Gson;
import june.book.domain.BookBasket;
import june.book.domain.BookBoard;
import june.book.domain.Member;
import june.book.domain.TranscriptionBoard;
import june.book.handler.BookBasketAddCommand;
import june.book.handler.BookBasketDeleteCommand;
import june.book.handler.BookBasketDetailCommand;
import june.book.handler.BookBasketListCommand;
import june.book.handler.BookBasketUpdateCommand;
import june.book.handler.BookBoardAddCommand;
import june.book.handler.BookBoardDeleteCommand;
import june.book.handler.BookBoardDetailCommand;
import june.book.handler.BookBoardListCommand;
import june.book.handler.BookBoardUpdateCommand;
import june.book.handler.Command;
import june.book.handler.MemberAddCommand;
import june.book.handler.MemberDeleteCommand;
import june.book.handler.MemberDetailCommand;
import june.book.handler.MemberListCommand;
import june.book.handler.MemberUpdateCommand;
import june.book.handler.TranscriptionBoardAddCommand;
import june.book.handler.TranscriptionBoardDeleteCommand;
import june.book.handler.TranscriptionBoardDetailCommand;
import june.book.handler.TranscriptionBoardListCommand;
import june.book.handler.TranscriptionBoardUpdateCommand;
import june.util.Prompt;

public class App {

  static Scanner keyboard = new Scanner(System.in);

  static Deque<String> commandStack = new ArrayDeque<>();
  static Queue<String> commandQueue = new LinkedList<>();

  static ArrayList<BookBoard> bookBoardList = new ArrayList<>();
  static ArrayList<TranscriptionBoard> transcriptionBoardList = new ArrayList<>();
  static ArrayList<BookBasket> bookBasketList = new ArrayList<>();
  static LinkedList<Member> memberList = new LinkedList<>();

  public static void main(String[] args) {

    loadMemberData();
    loadBookBoardData();
    loadBookBasketData();
    loadTranscriptionData();

    Prompt prompt = new Prompt(keyboard);
    HashMap<String, Command> commandMap = new HashMap<>();

    commandMap.put("/basket/add", new BookBasketAddCommand(prompt, bookBasketList));
    commandMap.put("/basket/list", new BookBasketListCommand(bookBasketList));
    commandMap.put("/basket/detail", new BookBasketDetailCommand(prompt, bookBasketList));
    commandMap.put("/basket/update", new BookBasketUpdateCommand(prompt, bookBasketList));
    commandMap.put("/basket/delete", new BookBasketDeleteCommand(prompt, bookBasketList));

    commandMap.put("/transcription/add",
        new TranscriptionBoardAddCommand(prompt, transcriptionBoardList));
    commandMap.put("/transcription/list",
        new TranscriptionBoardListCommand(transcriptionBoardList));
    commandMap.put("/transcription/detail",
        new TranscriptionBoardDetailCommand(prompt, transcriptionBoardList));
    commandMap.put("/transcription/update",
        new TranscriptionBoardUpdateCommand(prompt, transcriptionBoardList));
    commandMap.put("/transcription/delete",
        new TranscriptionBoardDeleteCommand(prompt, transcriptionBoardList));

    commandMap.put("/book/add", new BookBoardAddCommand(prompt, bookBoardList));
    commandMap.put("/book/list", new BookBoardListCommand(bookBoardList));
    commandMap.put("/book/detail", new BookBoardDetailCommand(prompt, bookBoardList));
    commandMap.put("/book/update", new BookBoardUpdateCommand(prompt, bookBoardList));
    commandMap.put("/book/delete", new BookBoardDeleteCommand(prompt, bookBoardList));

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

    saveMemberData();
    saveBookBoardData();
    saveBookBasketData();
    saveTranscriptionData();


  } // main()


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


  private static void loadMemberData() {
    File file = new File("./member.json");


    try (BufferedReader in = new BufferedReader(new FileReader(file))) {
      memberList.addAll(Arrays.asList(new Gson().fromJson(in, Member[].class)));
      System.out.printf("총 %d 개의 회원 데이터를 로딩했습니다.\n", memberList.size());

    } catch (IOException e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }
  }

  private static void saveMemberData() {
    File file = new File("./member.json");

    try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
      out.write(new Gson().toJson(memberList));
      System.out.printf("총 %d 개의 회원 데이터를 저장했습니다.\n", memberList.size());

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! -" + e.getMessage());
    }
  }

  private static void loadBookBoardData() {
    File file = new File("./bookBoard.json");

    try (BufferedReader in = new BufferedReader(new FileReader(file))) {
      bookBoardList.addAll(Arrays.asList(new Gson().fromJson(in, BookBoard[].class)));
      System.out.printf("총 %d 개의 도서 데이터를 로딩했습니다.\n", bookBoardList.size());

    } catch (IOException e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }
  }

  private static void saveBookBoardData() {
    File file = new File("./bookBoard.json");

    try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
      out.write(new Gson().toJson(bookBoardList));
      System.out.printf("총 %d 개의 도서 데이터를 저장했습니다.\n", bookBoardList.size());

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    }
  }

  private static void loadBookBasketData() {
    File file = new File("./bookBasket.json");

    try (BufferedReader in = new BufferedReader(new FileReader(file))) {
      bookBasketList.addAll(Arrays.asList(new Gson().fromJson(in, BookBasket[].class)));
      System.out.printf("총 %d 개의 즐겨찾는 도서의 데이터를 로딩했습니다.\n", bookBasketList.size());
    } catch (IOException e) {
      System.out.printf("파일 읽기 중 오류 발생! -" + e.getMessage());
    }
  }

  private static void saveBookBasketData() {
    File file = new File("./bookBasket.json");

    try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
      out.write(new Gson().toJson(bookBoardList));
      System.out.printf("총 %d 개의 즐겨찾는 도서의 데이터를 저장했습니다.\n", bookBoardList.size());

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    }
  }

  private static void loadTranscriptionData() {
    File file = new File("./transcription.json");

    try (BufferedReader in = new BufferedReader(new FileReader(file))) {
      transcriptionBoardList
          .addAll(Arrays.asList(new Gson().fromJson(in, TranscriptionBoard[].class)));
      System.out.printf("총 %d 개의 필사게시판의 데이터를 로딩했습니다.\n", transcriptionBoardList.size());
    } catch (IOException e) {
      System.out.printf("파일 읽기 중 오류 발생! -" + e.getMessage());
    }
  }

  private static void saveTranscriptionData() {
    File file = new File("./transcription.json");
    try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
      out.write(new Gson().toJson(transcriptionBoardList));
      System.out.printf("총 %d 개의 필사게시판의 데이터를 저장했습니다.\n", transcriptionBoardList.size());
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! -" + e.getMessage());
    }
  }
}
