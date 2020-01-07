package june.book.handler;

import java.util.Arrays;
import june.book.domain.Review;

public class ReviewList {

  public static final int REVIEW_SIZE = 100;
  
  Review[] reviews; 
  int reviewCount = 0;
  
  
  ReviewList(){
    this.reviews = new Review[REVIEW_SIZE];
  }
  
  ReviewList(int capacity) {
    if(capacity < REVIEW_SIZE || capacity > 10000)
      this.reviews = new Review[REVIEW_SIZE];
    else
      this.reviews = new Review[capacity];
  }

  public Review[] toArray() {
    return Arrays.copyOf(this.reviews, this.reviewCount);
    
  }

  public void add(Review review) {
    if(this.reviewCount == this.reviews.length) {
      int oldCapacity = this.reviews.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      this.reviews = Arrays.copyOf(this.reviews, newCapacity);
    }
    this.reviews[this.reviewCount++] = review;
  }

  public Review get(int no) {
    for(int i = 0; i < this.reviewCount; i++) {
      if(this.reviews[i].getNo() == no) {
        return this.reviews[i];
      }
    }
    return null;
  }
}
