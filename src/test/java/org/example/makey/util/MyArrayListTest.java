package org.example.makey.util;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import org.apache.logging.log4j.Logger;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {

    private static final Logger logger = LogManager.getLogger();

    private static final MyArrayList<Integer> myList = new MyArrayList<>();
    @BeforeAll
    static void create() {
        myList.add(10);
        myList.add(50);
        myList.add(-44);
        myList.add(15);
        myList.add(51);
        myList.add(17);
        myList.add(14);
    }
    @Test
    void size() {
        assertEquals(7, myList.size());
    }
    @Test
    void capacity() {
        assertEquals(10, myList.capacity());
    }
    @Test
    void addOneElementIntheEndOfList() {
        myList.add(14);
        assertEquals(8, myList.size());
        assertEquals(10, myList.capacity());
        assertEquals(14, myList.getLast());
    }
    @Test
     void addOneElementByIndex() {
        myList.add(1, 100);
        assertEquals(8, myList.size());
        assertEquals(10, myList.capacity());
        assertEquals(100, myList.get(1));
    }
    @Test
    void addCollection() {
        Set<Integer> set = new HashSet<>();
        set.add(15);
        set.add(-10);
        set.add(4);
        set.add(-56);
        set.add(7);
        set.add(89);
        myList.addAll(set);
        assertEquals(13, myList.size());
        assertEquals(16, myList.capacity());
    }
    @Test
    void setByIndex() {
        assertEquals(50, myList.get(1));
        myList.set(1, 100);
        assertEquals(100, myList.get(1));
    }
    @Test
    void getFirstElement() {
        assertEquals(10, myList.getFirst());
    }
    @Test
    void getLastElement() {
        assertEquals(14, myList.getLast());
    }
    @Test
    void clear() {
        myList.clear();
        assertEquals(0, myList.size());
    }
    @Test
    void getFirstElementFromEmptyList() {
        myList.clear();
        Throwable exception = assertThrows(NoSuchElementException.class, myList::getFirst);
        assertEquals("List is empty", exception.getMessage());
    }
    @Test
    void getLastElementFromEmptyList() {
        myList.clear();
        Throwable exception = assertThrows(NoSuchElementException.class, myList::getLast);
        assertEquals("List is empty", exception.getMessage());
    }
    @Test
    void getByIndex() {
        assertEquals(17, myList.get(5));
    }
    @Test
    void removeByIndex() {
        assertEquals(10, myList.getFirst());
        myList.remove(0);
        assertEquals(50, myList.getFirst());
    }
    @Test
    void removeByOutOfIndex() {
       Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> myList.remove(20));
       assertEquals("Index is out of list!", exception.getMessage());
    }
    @Test
    void sort() {
        myList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        }, 0, 6);
        assertEquals(-44, myList.getFirst());
        assertEquals(10, myList.get(1));
        assertEquals(14, myList.get(2));
        assertEquals(15, myList.get(3));
        assertEquals(17, myList.get(4));
        assertEquals(50, myList.get(5));
        assertEquals(51, myList.getLast());
    }
}