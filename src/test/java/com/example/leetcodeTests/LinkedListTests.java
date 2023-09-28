package com.example.leetcodeTests;

import com.example.leetcode.LinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LinkedListTests {

  @Test
  void call_hasLoop() {
    LinkedList myList = new LinkedList(1);
    myList.append(2);
    myList.append(3);
    myList.append(4);
    myList.append(5);

    myList.getTail().next = myList.getHead();

    Assertions.assertEquals(true, myList.hasLoop());

  }

  @Test
  void call_hasLoop2() {
    LinkedList myList = new LinkedList(1);
    myList.append(2);

    myList.getTail().next = myList.getHead().next;

    Assertions.assertEquals(true, myList.hasLoop());

    myList.getTail().value = myList.getHead().value;
    Assertions.assertEquals(true, myList.hasLoop());

    myList.getTail().next = myList.getHead();
    Assertions.assertEquals(true, myList.hasLoop());

  }

  @Test
  void call_hasLoop3() {
    LinkedList myList = new LinkedList(1);

    myList.getTail().next = myList.getHead().next;

    System.out.print("Has Loop:");
    System.out.println( myList.hasLoop());

    Assertions.assertEquals(false, myList.hasLoop());
  }

}
