package june.util;

public class Queue<E> extends LinkedList<E> implements Cloneable {

  public void offer(E value) {
    this.add(value);
  }

  public E poll() {
    return this.remove(0);
  }

  @Override
  public Queue<E> clone() {
    Queue<E> temp = new Queue<E>();

    for (int i = 0; i < this.size(); i++) {
      temp.offer(this.get(i));
    }
    return temp;
  }

  @Override
  public Iterator<E> iterator() {
    return new QueueIterator<>(this);
  }

  static public class QueueIterator<E> implements Iterator<E> {

    Queue<E> queue;

    public QueueIterator(Queue<E> queue) {
      this.queue = queue.clone();
    }

    @Override
    public boolean hasNext() {
      return queue.size() > 0;
    }

    @Override
    public E next() {
      return queue.poll();
    }

  }


}
