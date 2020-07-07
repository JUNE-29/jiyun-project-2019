package june.book;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import june.book.context.ApplicationContextListener;
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

  Scanner keyboard = new Scanner(System.in);

  Deque<String> commandStack = new ArrayDeque<>();
  Queue<String> commandQueue = new LinkedList<>();

  List<BookBoard> bookBoardList = new ArrayList<>();
  List<TranscriptionBoard> transcriptionBoardList = new ArrayList<>();
  List<BookBasket> bookBasketList = new ArrayList<>();
  List<Member> memberList = new LinkedList<>();

  // 옵저버 목록을 관리할 객체 준비
  Set<ApplicationContextListener> listeners = new HashSet<>();

  // 옵저버를 등록하는 메서드이다.
  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  // 옵저버를 제거하는 메서드이다.
  public void removeApplicationContextListener(ApplicationContextListener listener) {
    listeners.remove(listener);
  }

  // 애플리케이션이 시작되면, 등록된 리스너에게 알린다.
  private void notifyApplicationInitialized() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextInitialized();
    }
  }

  // 애플리케이션이 종료되면, 등록된 리스너에게 알린다.
  private void notifyApplicationDestroyed() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextDestroyed();
    }
  }

  public void service() {

    notifyApplicationInitialized();

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

    notifyApplicationDestroyed();

  } // service()


  private void printCommandHistory(Iterator<String> iterator) {
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


  @SuppressWarnings("unchecked")
  private void loadMemberData() {
    File file = new File("./member.ser2");

    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      memberList = (List<Member>) in.readObject();
      System.out.printf("총 %d 개의 회원 데이터를 로딩했습니다.\n", memberList.size());

    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }
  }

  private void saveMemberData() {
    File file = new File("./member.ser2");

    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeObject(memberList);
      System.out.printf("총 %d 개의 회원 데이터를 저장했습니다.\n", memberList.size());

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! -" + e.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  private void loadBookBoardData() {
    File file = new File("./bookBoard.ser2");

    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      bookBoardList = (List<BookBoard>) in.readObject();

      System.out.printf("총 %d 개의 도서 데이터를 로딩했습니다.\n", bookBoardList.size());

    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }
  }

  private void saveBookBoardData() {
    File file = new File("./bookBoard.ser2");

    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeObject(bookBoardList);

      System.out.printf("총 %d 개의 도서 데이터를 저장했습니다.\n", bookBoardList.size());
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  private void loadBookBasketData() {
    File file = new File("./bookBasket.ser2");

    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      bookBasketList = (List<BookBasket>) in.readObject();

      System.out.printf("총 %d 개의 즐겨찾는 도서의 데이터를 로딩했습니다.\n", bookBasketList.size());
    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! -" + e.getMessage());
    }
  }

  private void saveBookBasketData() {
    File file = new File("./bookBasket.ser2");

    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeObject(bookBasketList);
      System.out.printf("총 %d 개의 즐겨찾는 도서의 데이터를 저장했습니다.\n", bookBasketList.size());
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  private void loadTranscriptionData() {
    File file = new File("./transcription.ser2");

    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      transcriptionBoardList = (List<TranscriptionBoard>) in.readObject();

      System.out.printf("총 %d 개의 필사게시판의 데이터를 로딩했습니다.\n", transcriptionBoardList.size());
    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! -" + e.getMessage());
    }
  }

  private void saveTranscriptionData() {
    File file = new File("./transcription.ser2");
    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeObject(transcriptionBoardList);

      System.out.printf("총 %d 개의 필사게시판의 데이터를 저장했습니다.\n", transcriptionBoardList.size());
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! -" + e.getMessage());
    }
  }

  public static void main(String[] args) {
    App app = new App();

    app.addApplicationContextListener(new DataLoaderListener());

    app.service();
  }
}
