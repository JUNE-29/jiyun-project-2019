package june.util;

public interface List<E> {

  void add(E e);

  void add(int index, E value);

  E get(int index);

  E set(int index, E e);

  E remove(int index);

  Object[] toArray();

  E[] toArray(E[] arr);

  int size();

  Iterator<E> iterator();

}
