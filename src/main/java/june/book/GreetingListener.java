package june.book;

import java.util.Map;
import june.book.context.ApplicationContextListener;

public class GreetingListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("[도서 관리 시스템]에 오신걸 환영합니다!");

  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    System.out.println("안녕히가십시오!");
  }

}
