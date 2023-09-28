package com.example.leetcode;

public class LinkedList {

  private Node head;
  private Node tail;
  private int length;

  public class Node {

    public int value;
    public Node next;

    Node(int value) {
      this.value = value;
    }
  }

  public LinkedList(int value) {
    Node newNode = new Node(value);
    head = newNode;
    tail = newNode;
    length = 1;
  }

  public Node getHead() {
    return head;
  }

  public Node getTail() {
    return tail;
  }

  public int getLength() {
    return length;
  }

  public void printList() {
    Node temp = head;
    while (temp != null) {
      System.out.println(temp.value);
      temp = temp.next;
    }
  }

  public void printAll() {
    if (length == 0) {
      System.out.println("Head: null");
      System.out.println("Tail: null");
    } else {
      System.out.println("Head: " + head.value);
      System.out.println("Tail: " + tail.value);
    }
    System.out.println("Length:" + length);
    System.out.println("\nLinked List:");
    if (length == 0) {
      System.out.println("empty");
    } else {
      printList();
    }
  }

  public void makeEmpty() {
    head = null;
    tail = null;
    length = 0;
  }

  public void append(int value) {
    Node newNode = new Node(value);
    if (length == 0) {
      head = newNode;
    } else {
      tail.next = newNode;
    }
    tail = newNode;
    length++;
  }

  public Node removeFirst() {
    if (length == 0) return null;
    Node temp = head;
    head = head.next;
    temp.next = null;
    length--;
    if (length == 0) {
      tail = null;
    }
    return temp;
  }

  public Node findMiddleNode() {
    if (head == null) {
      return null;
    }
    Node slow = head;
    Node fast = head;
    while (fast.next != null) {
      fast = fast.next;
      slow = slow.next;
      if (fast.next != null) {
        fast = fast.next;
      }
    }
    return slow;
  }

  public boolean hasLoopMySolution() {

    Node iterate;
    iterate = head.next;

    while (iterate.next != null) {
      iterate = iterate.next;
      if (iterate == head.next) {
        return true;
      }
    }

    return false;

  }

  public boolean hasLoop() {

    Node slow = head;
    Node fast = head;

    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (fast == slow) {
        return true;
      }
    }
    return false;
  }

  public Node findKthFromEnd(int k){
    Node findLength = head;
    Node result = head;

    int length = 1;
    while(findLength != null && findLength.next != null){
      findLength = findLength.next;
      length++;
    }
    int steps = k - length;
    if(k > length) {
      return null;
    }

    while(steps != 0) {
      result = result.next;
      steps ++;
    }
    return result;
  }
}


