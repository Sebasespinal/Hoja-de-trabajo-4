package Postfixx;

public class DoublyLinkedList<T> {
    private Node<T> tail;
    private int size;

    private static class Node<T> {
        private T data;
        private Node<T> prev;
        public Node(T data) {
            this.data = data;
            this.prev = null;
        }
    }

    public DoublyLinkedList() {
        tail = null;
        size = 0;
    }

    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            tail = newNode;
        } else {
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public T remove() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        T removedData = tail.data;
        if (size == 1) {
            tail = null;
        } else {
            tail = tail.prev;
        }
        size--;
        return removedData;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return tail.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
