package june.book.domain;

import java.sql.Date;

public class Review {

  private int no;
  private String bookTitle;
  private String title;
  private String contents;
  private String photo ;
  private Date date ;
  private float score ;
  private int viewCount ;

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }
  
  public String getBookTitle() {
    return bookTitle;
  }
  
  public void setBookTitle(String bookTitle) {
    this.bookTitle = bookTitle;
  }
  
  public String getTitle() {
    return title;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }
  
  public String getContents() {
    return contents;
  }
  
  public void setContents(String contents) {
    this.contents = contents;
  }
  
  public String getPhoto() {
    return photo;
  }
  
  public void setPhoto(String photo) {
    this.photo = photo;
  }
  
  public Date getDate() {
    return date;
  }
  
  public void setDate(Date date) {
    this.date = date;
  }
  
  public float getScore() {
    return score;
  }
  
  public void setScore(float score) {
    this.score = score;
  }
  
  public int getViewCount() {
    return viewCount;
  }
  
  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }
}
