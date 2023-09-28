package com.example.leetcodeTests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.leetcode.LinkedList;
import org.junit.jupiter.api.Test;

class LinkedListTests {

  @Test
  void call_hasLoop() {
    LinkedList myList = new LinkedList(1);
    myList.append(2);
    myList.append(3);
    myList.append(4);
    myList.append(5);

    myList.getTail().next = myList.getHead();

    assertTrue(myList.hasLoop());

  }

  @Test
  void call_hasLoop2() {
    LinkedList myList = new LinkedList(1);
    myList.append(2);

    myList.getTail().next = myList.getHead().next;

    assertTrue(myList.hasLoop());

    myList.getTail().value = myList.getHead().value;
    assertTrue(myList.hasLoop());

    myList.getTail().next = myList.getHead();
    assertTrue(myList.hasLoop());

  }

  @Test
  void call_hasLoop3() {
    LinkedList myList = new LinkedList(1);
    myList.getTail().next = myList.getHead().next;

    assertFalse(myList.hasLoop());
  }
}
