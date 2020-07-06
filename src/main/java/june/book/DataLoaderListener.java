package june.book;

import june.book.context.ApplicationContextListener;

public class DataLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitialized() {
    System.out.println("데이터를 로딩합니다.");
  }

  @Override
  public void contextDestroyed() {
    System.out.println("데이터를 저장합니다.");
  }

}
