package june.util;

import java.util.Arrays;

public class ArrayList<E> extends AbstractList<E> {

  final static int DEAULT_CAPACITY = 2;

  Object[] elementData;

  public ArrayList() {
    this.elementData = new Object[DEAULT_CAPACITY];
  }

  public ArrayList(int initialCapacity) {
    if (initialCapacity < DEAULT_CAPACITY) {
      this.elementData = new Object[DEAULT_CAPACITY];
    } else {
      this.elementData = new Object[initialCapacity];
    }
  }

  @Override
  public void add(E e) {
    if (this.size == this.elementData.length) {
      grow();
    }
    this.elementData[this.size++] = e;
  }

  @Override
  @SuppressWarnings("unchecked")
  public E get(int index) {
    if (index < 0 || index >= this.size) {
      return null;
    }
    return (E) this.elementData[index];
  }

  @Override
  @SuppressWarnings("unchecked")
  public E set(int index, E e) {
    if (index < 0 || index >= this.size) {
      return null;
    }
    E oldValue = (E) this.elementData[index];
    this.elementData[index] = e;
    return oldValue;
  }

  @Override
  @SuppressWarnings("unchecked")
  public E remove(int index) {
    if (index < 0 || index >= this.size) {
      return null;
    }

    E oldValue = (E) this.elementData[index];

    System.arraycopy(this.elementData, index + 1, this.elementData, index, this.size - (index + 1));

    this.size--;
    return oldValue;
  }


  @Override
  public Object[] toArray() {
    return Arrays.copyOf(this.elementData, this.size);
  }

  @Override
  @SuppressWarnings("unchecked")
  public E[] toArray(E[] arr) {
    if (arr.length < this.size) {
      return (E[]) Arrays.copyOf(this.elementData, this.size, arr.getClass());
    }
    System.arraycopy(this.elementData, 0, arr, 0, this.size);
    return arr;
  }

  @Override
  public void add(int index, E value) {
    if (index < 0 || index >= this.size) {
      return;
    }

    if (this.size == this.elementData.length) {
      grow();
    }

    for (int i = size - 1; i >= index; i--)
      this.elementData[i + 1] = this.elementData[i];

    this.elementData[index] = value;
    this.size++;
  }

  private Object[] grow() {
    return this.elementData = Arrays.copyOf(this.elementData, newCapacity());
  }

  private int newCapacity() {
    int oldSize = this.elementData.length;
    return oldSize + (oldSize >> 1);
  }
}
