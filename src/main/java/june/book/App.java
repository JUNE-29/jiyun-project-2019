package june.book;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
    File file = new File("./member.data");

    try (DataInputStream in =
        new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      int size = in.readInt();
      for (int i = 0; i < size; i++) {
        Member member = new Member();
        member.setNo(in.readInt());
        member.setName(in.readUTF());
        member.setEmail(in.readUTF());
        member.setPassword(in.readUTF());
        member.setPhoto(in.readUTF());
        member.setRegisteredDate(Date.valueOf(in.readUTF()));
        memberList.add(member);
      }
      System.out.printf("총 %d 개의 회원 데이터를 로딩했습니다.\n", memberList.size());

    } catch (IOException e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }
  }

  private static void saveMemberData() {
    File file = new File("./member.data");

    try (DataOutputStream out =
        new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeInt(memberList.size());
      for (Member member : memberList) {
        out.writeInt(member.getNo());
        out.writeUTF(member.getName());
        out.writeUTF(member.getEmail());
        out.writeUTF(member.getPassword());
        out.writeUTF(member.getPhoto());
        out.writeUTF(member.getRegisteredDate().toString());
      }
      System.out.printf("총 %d 개의 회원 데이터를 저장했습니다.\n", memberList.size());

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! -" + e.getMessage());
    }
  }

  private static void loadBookBoardData() {
    File file = new File("./bookBoard.data");

    try (DataInputStream in =
        new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      int size = in.readInt();
      for (int i = 0; i < size; i++) {
        BookBoard bookBoard = new BookBoard();
        bookBoard.setNo(in.readInt());
        bookBoard.setBookTitle(in.readUTF());
        bookBoard.setAuthor(in.readUTF());
        bookBoard.setPublisher(in.readUTF());
        bookBoard.setCategories(in.readUTF());
        bookBoard.setPublishedDate(in.readUTF());
        bookBoard.setContent(in.readUTF());
        bookBoard.setPhoto(in.readUTF());
        bookBoard.setBookStatus(in.readUTF());
        bookBoard.setScore(in.readFloat());
        bookBoard.setDate(Date.valueOf(in.readUTF()));
        bookBoard.setViewCount(in.readInt());
        bookBoardList.add(bookBoard);
      }

      System.out.printf("총 %d 개의 도서 데이터를 로딩했습니다.\n", bookBoardList.size());

    } catch (IOException e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }
  }

  private static void saveBookBoardData() {
    File file = new File("./bookBoard.data");

    try (DataOutputStream out =
        new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeInt(bookBoardList.size());
      for (BookBoard bookBoard : bookBoardList) {
        out.writeInt(bookBoard.getNo());
        out.writeUTF(bookBoard.getBookTitle());
        out.writeUTF(bookBoard.getAuthor());
        out.writeUTF(bookBoard.getPublisher());
        out.writeUTF(bookBoard.getCategories());
        out.writeUTF(bookBoard.getPublishedDate());
        out.writeUTF(bookBoard.getContent());
        out.writeUTF(bookBoard.getPhoto());
        out.writeUTF(bookBoard.getBookStatus());
        out.writeFloat(bookBoard.getScore());
        out.writeUTF(bookBoard.getDate().toString());
        out.writeInt(bookBoard.getViewCount());
      }
      System.out.printf("총 %d 개의 도서 데이터를 저장했습니다.\n", bookBoardList.size());
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    }
  }

  private static void loadBookBasketData() {
    File file = new File("./bookBasket.data");

    try (DataInputStream in =
        new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {

      int size = in.readInt();
      for (int i = 0; i < size; i++) {
        BookBasket bookBasket = new BookBasket();
        bookBasket.setNo(in.readInt());
        bookBasket.setBookTitle(in.readUTF());
        bookBasket.setAuthor(in.readUTF());
        bookBasket.setPublisher(in.readUTF());
        bookBasket.setCategories(in.readUTF());
        bookBasket.setPublishedDate(in.readUTF());
        bookBasket.setMemo(in.readUTF());
        bookBasketList.add(bookBasket);
      }

      System.out.printf("총 %d 개의 즐겨찾는 도서의 데이터를 로딩했습니다.\n", bookBasketList.size());
    } catch (IOException e) {
      System.out.println("파일 읽기 중 오류 발생! -" + e.getMessage());
    }
  }

  private static void saveBookBasketData() {
    File file = new File("./bookBasket.data");

    try (DataOutputStream out =
        new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeInt(bookBasketList.size());
      for (BookBasket bookBasket : bookBasketList) {
        out.writeInt(bookBasket.getNo());
        out.writeUTF(bookBasket.getBookTitle());
        out.writeUTF(bookBasket.getAuthor());
        out.writeUTF(bookBasket.getPublisher());
        out.writeUTF(bookBasket.getCategories());
        out.writeUTF(bookBasket.getPublishedDate());
        out.writeUTF(bookBasket.getMemo());
      }
      System.out.printf("총 %d 개의 즐겨찾는 도서의 데이터를 저장했습니다.\n", bookBasketList.size());
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    }
  }

  private static void loadTranscriptionData() {
    File file = new File("./transcription.data");

    try (DataInputStream in =
        new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      int size = in.readInt();
      for (int i = 0; i < size; i++) {
        TranscriptionBoard transcription = new TranscriptionBoard();
        transcription.setNo(in.readInt());
        transcription.setTitle(in.readUTF());
        transcription.setBookTitle(in.readUTF());
        transcription.setAuthor(in.readUTF());
        transcription.setPublisher(in.readUTF());
        transcription.setContent(in.readUTF());
        transcription.setPhoto(in.readUTF());
        transcription.setDate(Date.valueOf(in.readUTF()));
        transcriptionBoardList.add(transcription);
      }
      System.out.printf("총 %d 개의 필사게시판의 데이터를 로딩했습니다.\n", transcriptionBoardList.size());
    } catch (IOException e) {
      System.out.println("파일 읽기 중 오류 발생! -" + e.getMessage());
    }
  }

  private static void saveTranscriptionData() {
    File file = new File("./transcription.data");
    try (DataOutputStream out =
        new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeInt(transcriptionBoardList.size());
      for (TranscriptionBoard transcription : transcriptionBoardList) {
        out.writeInt(transcription.getNo());
        out.writeUTF(transcription.getTitle());
        out.writeUTF(transcription.getBookTitle());
        out.writeUTF(transcription.getAuthor());
        out.writeUTF(transcription.getPublisher());
        out.writeUTF(transcription.getContent());
        out.writeUTF(transcription.getPhoto());
        out.writeUTF(transcription.getDate().toString());
      }
      System.out.printf("총 %d 개의 필사게시판의 데이터를 저장했습니다.\n", transcriptionBoardList.size());
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! -" + e.getMessage());
    }
  }
}
