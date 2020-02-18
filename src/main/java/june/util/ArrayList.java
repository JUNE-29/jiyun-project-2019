package june.util;

import java.util.Arrays;

public class ArrayList<E> {

  static final int DEFAULT_CAPACITY = 3;

  Object[] list;
  int size = 0;

  public ArrayList() {
    this.list = new Object[DEFAULT_CAPACITY];
  }

  public ArrayList(int capacity) {
    if (capacity < DEFAULT_CAPACITY || capacity > 10000)
      this.list = new Object[DEFAULT_CAPACITY];
    else
      this.list = new Object[capacity];
  }

  @SuppressWarnings("unchecked")
  public E[] toArray(E[] arr) {

    if (arr.length < this.size) {
      return (E[]) Arrays.copyOf(this.list, this.size, arr.getClass());
    }
    System.arraycopy(this.list, 0, arr, 0, this.size);

    return arr;
  }

  public void add(Object obj) {
    if (this.size == this.list.length) {
      int oldCapacity = this.list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      this.list = Arrays.copyOf(this.list, newCapacity);
    }
    this.list[this.size++] = obj;
  }


  @SuppressWarnings("unchecked")
  public E get(int idx) {
    if (idx >= 0 && idx < this.size) {
      return (E) this.list[idx];
    } else {
      return null;
    }
  }

  public int size() {
    return this.size;
  }

}
