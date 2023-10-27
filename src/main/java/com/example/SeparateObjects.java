package com.example;

public class SeparateObjects {

	public static void main(String[] args) {

		Object myObject = new Object();

		//local variables are never shared each thread has its own local variable or its own references
		//but the two different runnables reference the same myObject on the heap
		Runnable runnable1 = new MyRunnable(myObject);
		Runnable runnable2 = new MyRunnable(myObject);

		Thread thread1 = new Thread(runnable1, "Thread1");
		Thread thread2 = new Thread(runnable2, "Thread2");

		thread1.start();
		thread2.start();



	}
}
