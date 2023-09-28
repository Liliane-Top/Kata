package com.example.leetcodeTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.leetcode.LinkedList;
import com.example.leetcode.LinkedList.Node;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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

  @ParameterizedTest
  @MethodSource("happyFlow")
  void call_findKthFromEnd(int k, Integer output) {
    LinkedList myList = new LinkedList(1);
    myList.append(2);
    myList.append(3);
    myList.append(4);
    myList.append(5);

    assertEquals(output, myList.findKthFromEnd(k).value);

  }

  static Stream<Arguments> happyFlow() {
    return Stream.of(
        Arguments.of(2, 4),
        Arguments.of(5, 1),
        Arguments.of(1, 5)
    );

  }

  @ParameterizedTest
  @MethodSource("unhappyFlow")
  void call_findKthFromEnd_invalid(int k, Node output) {
    LinkedList myList = new LinkedList(1);
    myList.append(2);
    myList.append(3);
    myList.append(4);
    myList.append(5);

    assertEquals(output, myList.findKthFromEnd(k));
    LinkedList myList2 = new LinkedList(1);
    myList2.removeFirst();
    assertEquals(output, myList.findKthFromEnd(k));


  }

  static Stream<Arguments> unhappyFlow() {
    return Stream.of(
        Arguments.of(6, null),
        Arguments.of(0, null)
    );

  }

}
