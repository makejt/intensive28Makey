package org.example.makey.util;

import java.util.Collection;
import java.util.Comparator;

public interface MyList<T> {

    int size();
    boolean isEmpty();
    T getFirst();
    T getLast();
    int capacity();
    void add(T element);
    void add(int index, T element);
    void addAll(Collection<? extends T> collection);
    T get(int index);
    void set(int index, T element);
    T remove(int index);
    boolean remove(T o);
    void sort(Comparator<? super T> c, int low, int high);
}