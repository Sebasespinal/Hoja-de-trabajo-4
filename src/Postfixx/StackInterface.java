package Postfixx;

import java.util.EmptyStackException;

public interface StackInterface<T> {
    void push(T element);
    T pop() throws EmptyStackException;
    T peek() throws EmptyStackException;
    boolean isEmpty();
    int size();
}
