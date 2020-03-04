package june.book.domain;

public class Recommendation {

  private int no;
  private String categories;
  private String age;
  private String character;
  private String keyword;



  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((age == null) ? 0 : age.hashCode());
    result = prime * result + ((categories == null) ? 0 : categories.hashCode());
    result = prime * result + ((character == null) ? 0 : character.hashCode());
    result = prime * result + ((keyword == null) ? 0 : keyword.hashCode());
    result = prime * result + no;
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
    Recommendation other = (Recommendation) obj;
    if (age == null) {
      if (other.age != null)
        return false;
    } else if (!age.equals(other.age))
      return false;
    if (categories == null) {
      if (other.categories != null)
        return false;
    } else if (!categories.equals(other.categories))
      return false;
    if (character == null) {
      if (other.character != null)
        return false;
    } else if (!character.equals(other.character))
      return false;
    if (keyword == null) {
      if (other.keyword != null)
        return false;
    } else if (!keyword.equals(other.keyword))
      return false;
    if (no != other.no)
      return false;
    return true;
  }


  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getCategories() {
    return categories;
  }

  public void setCategories(String categories) {
    this.categories = categories;
  }


  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getCharacter() {
    return character;
  }

  public void setCharacter(String character) {
    this.character = character;
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

}
