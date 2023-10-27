package com.example;

public class MyRunnable implements Runnable{
	private int count = 0;
	Object myObject = null;

	public MyRunnable(){

	}

	public MyRunnable(Object myObject){
		this.myObject = myObject;
	}

	@Override
	public void run() {

//		System.out.println(myObject);

		for (int i = 0; i < 1_000_000; i++) {
			synchronized (this) {
				this.count++;

			}
		}
		System.out.println(
				Thread.currentThread().getName() + " : " + this.count);
	}
}
