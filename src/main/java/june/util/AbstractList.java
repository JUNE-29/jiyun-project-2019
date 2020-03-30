package june.util;

public abstract class AbstractList<E> implements List<E> {

  protected int size;

  @Override
  public int size() {
    return this.size;
  }

  @Override
  public Iterator<E> iterator() {

    class ListIterator<T> implements Iterator<T> {
      List<T> list;
      int cursor;

      @SuppressWarnings("unchecked")
      public ListIterator() {
        this.list = (List<T>) AbstractList.this;
      }

      @Override
      public boolean hasNext() {
        return cursor < this.list.size();
      }

      @Override
      public T next() {
        return list.get(cursor++);
      }
    }

    return new ListIterator<E>();
  }
}
