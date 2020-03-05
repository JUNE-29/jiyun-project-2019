package june.util;

public class ListIterator<E> implements Iterator<E> {

  List<E> list;
  int cursor;

  public ListIterator(List<E> list) {
    this.list = list;
  }

  @Override
  public boolean hasNext() {
    return cursor < this.list.size();
  }

  @Override
  public E next() {
    return list.get(cursor++);
  }



}
