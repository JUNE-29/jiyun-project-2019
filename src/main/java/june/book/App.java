package june.book;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
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
    // 데이터가 보관된 파일의 정보를 준비한다.
    File file = new File("./member.csv");

    FileReader in = null;
    Scanner dataScan = null;

    try {
      // 파일을 읽을 때 사용할 도구를 준비한다.
      in = new FileReader(file);

      // .csv 파일에서 한 줄 단위로 문자열을 읽는 도구가 필요한데
      // FileReader에는 그런기능이 없다.
      // 그래서 FileReader를 그대로 사용할 수 없고
      // 이 객체에 다른 도구를 연결하여 사용할 것이다.

      dataScan = new Scanner(in);
      int count = 0;

      while (true) {
        try {
          // 파일에서 한 줄을 읽는다.
          String line = dataScan.nextLine();

          // 한 줄을 콤마(,)로 나눈다.
          String[] data = line.split(",");

          // 한 줄에 들어 있는 데이터를 추출하여 Member 객체에 담는다.
          // => 데이터 순서는 다음과 같다.
          // 번호, 이름, 이메일, 비밀번호, 사진, 가입날짜
          Member member = new Member();
          member.setNo(Integer.parseInt(data[0]));
          member.setName(data[1]);
          member.setEmail(data[2]);
          member.setPassword(data[3]);
          member.setPhoto(data[4]);
          member.setRegisteredDate(Date.valueOf(data[5]));

          // member 객체를 Command가 사용하는 목록에 저장한다.
          memberList.add(member);
          count++;

        } catch (Exception e) {
          break;
        }
      }
      System.out.printf("총 %d 개의 회원 데이터를 로딩했습니다.\n", count);

    } catch (FileNotFoundException e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    } finally {
      try {
        dataScan.close();
      } catch (Exception e) {
      }
      try {
        in.close();
      } catch (Exception e) {
      }
    }
  }

  private static void saveMemberData() {
    File file = new File("./member.csv");

    FileWriter out = null;

    try {
      out = new FileWriter(file);
      int count = 0;

      for (Member member : memberList) {
        String line = String.format("%d,%s,%s,%s,%s,%s\n", //
            member.getNo(), member.getName(), member.getEmail(), member.getPassword(),
            member.getPhoto(), member.getRegisteredDate());

        out.write(line);
        count++;
      }
      System.out.printf("총 %d 개의 회원 데이터를 저장했습니다.\n", count);
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! -" + e.getMessage());
    } finally {
      try {
        out.close();
      } catch (IOException e) {

      }
    }
  }

  private static void loadBookBoardData() {
    File file = new File("./bookBoard.csv");

    FileReader in = null;
    Scanner dataScan = null;

    try {
      in = new FileReader(file);
      dataScan = new Scanner(in);
      int count = 0;

      while (true) {
        try {
          String line = dataScan.nextLine();
          String[] data = line.split(",");

          BookBoard bookBoard = new BookBoard();
          bookBoard.setNo(Integer.parseInt(data[0]));
          bookBoard.setBookTitle(data[1]);
          bookBoard.setAuthor(data[2]);
          bookBoard.setPublisher(data[3]);
          bookBoard.setCategories(data[4]);
          bookBoard.setPublishedDate(data[5]);
          bookBoard.setContent(data[6]);
          bookBoard.setPhoto(data[7]);
          bookBoard.setBookStatus(data[8]);
          bookBoard.setScore(Float.parseFloat(data[9]));
          bookBoard.setDate(Date.valueOf(data[10]));
          bookBoard.setViewCount(Integer.parseInt(data[11]));

          bookBoardList.add(bookBoard);
          count++;

        } catch (Exception e) {
          break;
        }
      }
      System.out.printf("총 %d 개의 게시물 데이터를 로딩했습니다.\n", count);

    } catch (FileNotFoundException e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    } finally {
      try {
        dataScan.close();
      } catch (Exception e) {

      }
      try {
        in.close();
      } catch (Exception e) {
      }
    }
  }

  private static void saveBookBoardData() {
    File file = new File("./bookBoard.csv");
    FileWriter out = null;

    try {
      out = new FileWriter(file);
      int count = 0;

      for (BookBoard bookBoard : bookBoardList) {
        String line = String.format("%d,%s,%s,%s,%s,%s,%s,%s,%s,%1.1f,%s,%d", //
            bookBoard.getNo(), bookBoard.getBookTitle(), bookBoard.getAuthor(),
            bookBoard.getPublisher(), bookBoard.getCategories(), bookBoard.getPublishedDate(),
            bookBoard.getContent(), bookBoard.getPhoto(), bookBoard.getBookStatus(),
            bookBoard.getScore(), bookBoard.getDate(), bookBoard.getViewCount());

        out.write(line);
        count++;
      }
      System.out.printf("총 %d 개의 게시물 데이터를 저장했습니다.\n", count);

    } catch (Exception e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    } finally {
      try {
        out.close();
      } catch (IOException e) {

      }
    }
  }

  private static void loadBookBasketData() {
    File file = new File("./bookBasket.csv");

    FileReader in = null;
    Scanner dataScan = null;

    try {

      in = new FileReader(file);
      dataScan = new Scanner(in);
      int count = 0;

      while (true) {
        try {
          String line = dataScan.nextLine();
          String[] data = line.split(",");

          BookBasket bookBasket = new BookBasket();
          bookBasket.setNo(Integer.parseInt(data[0]));
          bookBasket.setBookTitle(data[1]);
          bookBasket.setAuthor(data[2]);
          bookBasket.setPublisher(data[3]);
          bookBasket.setCategories(data[4]);
          bookBasket.setPublishedDate(data[5]);
          bookBasket.setMemo(data[6]);

          bookBasketList.add(bookBasket);
          count++;

        } catch (Exception e) {
          break;
        }
      }
      System.out.printf("총 %d 개의 즐겨찾는 도서의 데이터를 로딩했습니다.\n", count);
    } catch (FileNotFoundException e) {
      System.out.printf("파일 읽기 중 오류 발생! -" + e.getMessage());
    } finally {
      try {
        dataScan.close();
      } catch (Exception e) {

      }
      try {
        in.close();
      } catch (Exception e) {

      }
    }
  }

  private static void saveBookBasketData() {
    File file = new File("./bookBasket.csv");
    FileWriter out = null;

    try {
      out = new FileWriter(file);
      int count = 0;

      for (BookBasket bookBasket : bookBasketList) {
        String line = String.format("%d,%s,%s,%s,%s,%s,%s", //
            bookBasket.getNo(), bookBasket.getBookTitle(), bookBasket.getAuthor(),
            bookBasket.getPublisher(), bookBasket.getCategories(), bookBasket.getPublishedDate(),
            bookBasket.getMemo());

        out.write(line);
        count++;
      }
      System.out.printf("총 %d 개의 즐겨찾는 도서의 데이터를 저장했습니다.\n", count);

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    } finally {
      try {
        out.close();
      } catch (IOException e) {

      }
    }
  }

  private static void loadTranscriptionData() {
    File file = new File("./transcription.csv");

    FileReader in = null;
    Scanner dataScan = null;

    try {
      in = new FileReader(file);
      dataScan = new Scanner(in);
      int count = 0;

      while (true) {
        try {
          String line = dataScan.nextLine();
          String[] data = line.split(",");

          TranscriptionBoard transcription = new TranscriptionBoard();
          transcription.setNo(Integer.parseInt(data[0]));
          transcription.setTitle(data[1]);
          transcription.setBookTitle(data[2]);
          transcription.setAuthor(data[3]);
          transcription.setPublisher(data[4]);
          transcription.setContent(data[5]);
          transcription.setPhoto(data[6]);
          transcription.setDate(Date.valueOf(data[7]));

          transcriptionBoardList.add(transcription);
          count++;

        } catch (Exception e) {
          break;
        }
      }
      System.out.printf("총 %d 개의 필사게시판의 데이터를 로딩했습니다.\n", count);
    } catch (FileNotFoundException e) {

    }
  }

  private static void saveTranscriptionData() {
    File file = new File("./transcription.csv");
    FileWriter out = null;

    try {
      out = new FileWriter(file);
      int count = 0;

      for (TranscriptionBoard transcription : transcriptionBoardList) {
        String line = String.format("%d,%s,%s,%s,%s,%s,%s,%s", //
            transcription.getNo(), transcription.getTitle(), transcription.getBookTitle(),
            transcription.getAuthor(), transcription.getPublisher(), transcription.getContent(),
            transcription.getPhoto(), transcription.getDate());
        out.write(line);
        count++;
      }
      System.out.printf("총 %d 개의 필사게시판의 데이터를 저장했습니다.\n", count);
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! -" + e.getMessage());
    } finally {
      try {
        out.close();
      } catch (Exception e) {
      }
    }
  }

}
