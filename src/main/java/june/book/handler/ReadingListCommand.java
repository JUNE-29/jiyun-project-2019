package june.book.handler;

import java.util.Iterator;
import java.util.List;
import june.book.domain.Reading;

public class ReadingListCommand implements Command {

  List<Reading> readingList;


  public ReadingListCommand(List<Reading> list) {
    this.readingList = list;
  }

  @Override
  public void execute() {
    Iterator<Reading> iterator = readingList.iterator();
    while (iterator.hasNext()) {
      Reading r = iterator.next();
      System.out.printf("%d, %s, %s, %s, %s, %s\n", r.getNo(), r.getTitle(), r.getAuthor(),
          r.getPublisher(), r.getCategories(), r.getPublishedDate());
    }
  }
}
