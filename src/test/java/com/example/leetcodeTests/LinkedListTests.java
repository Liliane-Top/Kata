package com.example.leetcodeTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.leetcode.LinkedList;
import com.example.leetcode.LinkedList.Node;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
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

  @Test
  void call_partitionList() {
//    LinkedList myList = new LinkedList(3);
//    myList.append(8);
//    myList.append(5);
//    myList.append(10);
//    myList.append(2);
//    myList.append(1);
//
//    myList.partitionList(5);
//
//    Assertions.assertEquals(3, myList.getHead().value);
//    Assertions.assertEquals(2, myList.getHead().next.value);
//    Assertions.assertEquals(1, myList.getHead().next.next.value);
//    Assertions.assertEquals(8, myList.getHead().next.next.next.value);
//    Assertions.assertEquals(5, myList.getHead().next.next.next.next.value);
//    Assertions.assertEquals(10, myList.getHead().next.next.next.next.next.value);
//
//    LinkedList list = new LinkedList(1);
//    list.append(4);
//    list.append(3);
//    list.append(2);
//    list.append(5);
//    list.append(2);
//
//    list.partitionList(3);
//    Assertions.assertEquals(1, list.getHead().value);
//    Assertions.assertEquals(2, list.getHead().next.value);
//    Assertions.assertEquals(2, list.getHead().next.next.value);
//    Assertions.assertEquals(4, list.getHead().next.next.next.value);
//    Assertions.assertEquals(3, list.getHead().next.next.next.next.value);
//    Assertions.assertEquals(5, list.getHead().next.next.next.next.next.value);


    LinkedList list2 = new LinkedList(1);
    list2.append(4);
    list2.append(3);
    list2.append(2);
    list2.append(5);
    list2.append(2);
    list2.partitionList(6);

    Assertions.assertEquals(1, list2.getHead().value);
    Assertions.assertEquals(4, list2.getHead().next.value);
    Assertions.assertEquals(3, list2.getHead().next.next.value);
    Assertions.assertEquals(2, list2.getHead().next.next.next.value);
    Assertions.assertEquals(5, list2.getHead().next.next.next.next.value);
    Assertions.assertEquals(2, list2.getHead().next.next.next.next.next.value);

    LinkedList list3 = new LinkedList(8);
    list3.append(4);
    list3.append(9);
    list3.append(3);
    list3.append(10);
    list3.append(2);
    list3.partitionList(5);



    Assertions.assertEquals(4, list3.getHead().value);
    Assertions.assertEquals(3, list3.getHead().next.value);
    Assertions.assertEquals(2, list3.getHead().next.next.value);
    Assertions.assertEquals(8, list3.getHead().next.next.next.value);
    Assertions.assertEquals(9, list3.getHead().next.next.next.next.value);
    Assertions.assertEquals(10, list3.getHead().next.next.next.next.next.value);

  }



}