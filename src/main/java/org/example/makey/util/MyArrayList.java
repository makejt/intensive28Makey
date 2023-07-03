package org.example.makey.util;

import java.util.*;

public class MyArrayList<T> implements MyList<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] array;

    public MyArrayList() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public MyArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity couldn't be negative!!!");
        }
        array = new Object[capacity];
        size = 0;
    }

    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return (T) array[0];
    }

    @SuppressWarnings("unchecked")
    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return (T) array[size - 1];
    }
    public int capacity() {
        return array.length;
    }
    private void checkSize() {
        if (size == array.length) {
            Object[] array1 = new Object[(array.length * 2)];
            System.arraycopy(array, 0, array1, 0, array.length);
            array = array1;
        }
    }
    public void add(T element) {
        checkSize();
        array[size] = element;
        size++;
    }
    public void add(int index, T element) {
        checkSize();
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Index is out of list!");
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }
    public void addAll(Collection<? extends T> collection) {
        if ((array.length - size - collection.size() <= 0)) {
            Object[] array1 = new Object[(array.length + collection.size())];
            System.arraycopy(array, 0, array1, 0, array.length);
            array = array1;
        }
        Object[] a = collection.toArray();
        System.arraycopy(a, 0, array, size, a.length);
        size += a.length;
    }
    @SuppressWarnings("unchecked")
    public void clear() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Index is out of list!");
        }
        return (T) array[index];
    }

    public void set(int index, T element) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Index is out of list!");
        }
        array[index] = element;
    }

    public T remove(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Index is out of list!");
        }
        T element = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;

        return element;
    }

    @SuppressWarnings("unchecked")
    public boolean remove(T o) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(o)) { // i = 1
                System.arraycopy(array, i + 1, array, i, size - i - 1);
                array[--size] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    @SuppressWarnings("unchecked")
    public void sort(Comparator<? super T> c, int low, int high) {
        if (size == 0)
            return;
        if (low >= high)
            return;

        int middle = low + (high - low) / 2;
        T border = (T) array[middle];

        int i = low;
        int j = high;

        while (i <= j) {
            while (c.compare((T) array[i], border) < 0) i++;
            while (c.compare((T) array[j], border) > 0) j--;

            if (i <= j) {
                T temp = (T) array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
            if (low < j) sort(c, low, j);
            if (i < high) sort(c, i, high);
        }
    }
}