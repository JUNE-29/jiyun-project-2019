package june.book.domain;

import java.sql.Date;

public class Review {

  private int no;
  private String bookTitle;
  private String title;
  private String contents;
  private String photo;
  private Date date;
  private float score;
  private int viewCount;


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((bookTitle == null) ? 0 : bookTitle.hashCode());
    result = prime * result + ((contents == null) ? 0 : contents.hashCode());
    result = prime * result + no;
    result = prime * result + ((photo == null) ? 0 : photo.hashCode());
    result = prime * result + Float.floatToIntBits(score);
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    result = prime * result + viewCount;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Review other = (Review) obj;
    if (bookTitle == null) {
      if (other.bookTitle != null)
        return false;
    } else if (!bookTitle.equals(other.bookTitle))
      return false;
    if (contents == null) {
      if (other.contents != null)
        return false;
    } else if (!contents.equals(other.contents))
      return false;
    if (no != other.no)
      return false;
    if (photo == null) {
      if (other.photo != null)
        return false;
    } else if (!photo.equals(other.photo))
      return false;
    if (Float.floatToIntBits(score) != Float.floatToIntBits(other.score))
      return false;
    if (title == null) {
      if (other.title != null)
        return false;
    } else if (!title.equals(other.title))
      return false;
    if (viewCount != other.viewCount)
      return false;
    return true;
  }

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
