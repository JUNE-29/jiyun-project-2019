package june.book.handler;

import java.util.Arrays;
import june.book.domain.Recommend;

public class RecommendList {

  static final int RECOMMEND_SIZE = 100;

  Recommend[] recommends;
  int recommendCount = 0;

  RecommendList(){
    this.recommends = new Recommend[RECOMMEND_SIZE];
  }

  RecommendList(int capacity) {
    if(capacity < RECOMMEND_SIZE || capacity > 10000)
      this.recommends = new Recommend[RECOMMEND_SIZE];
    else
      this.recommends = new Recommend[capacity];
  }

  public Recommend[] toArray() {
    return Arrays.copyOf(this.recommends, this.recommendCount);
  }

  public void add(Recommend recommend) {
    if(this.recommendCount == this.recommends.length) {
      int oldCapacity = this.recommends.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      this.recommends = Arrays.copyOf(this.recommends, newCapacity);
    }
    this.recommends[this.recommendCount++] = recommend;
  }


}
