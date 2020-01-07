package june.book.handler;

import java.util.Arrays;
import june.book.domain.Reading;

public class ReadingList {
  
  static final int READING_SIZE = 100;
  
  Reading[] reading;
  int readingCount = 0;
  
  public ReadingList() {
    this.reading = new Reading[READING_SIZE];
  }
  
  public ReadingList(int capacity) {
    if(capacity < READING_SIZE || capacity > 10000)
      this.reading = new Reading[READING_SIZE];
    else
      this.reading = new Reading[capacity];
  }

  public Reading[] toArray() {
    return Arrays.copyOf(this.reading, this.readingCount);
  }
  
  public void add(Reading reading) {
    if(this.readingCount == this.reading.length) {
      int oldCapacity = this.reading.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      this.reading = Arrays.copyOf(this.reading, newCapacity);
    }
    this.reading[this.readingCount++] = reading;
  }
  
}
