package com.example;

public class SharedObjects {

	public static void main(String[] args) {
//		Object myObject = new Object();
//		Runnable runnable = new MyRunnable(myObject);
		MyRunnable runnable = new MyRunnable();
		//each thread will create its own object even though they both have the same runnable.
		Thread thread1 = new Thread(runnable, "Thread1");
		Thread thread2 = new Thread(runnable, "Thread2");

		thread1.start();
		thread2.start();
	}

}
