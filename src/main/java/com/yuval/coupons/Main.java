package com.yuval.coupons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);

		/*
		 * // Creating a task
		 *  TimerTask timerTask = new CouponsTimerTask();
		 * 
		 * // Creating a timer 
		 * Timer timer = new Timer(); 
		 * Calendar beginning = Calendar.getInstance();
		 *  beginning.set(2020, 11, 24, 0, 0, 0); //
		 *   Tell the timer to run the task every 10 seconds, starting of now
		 * 
		 * timer.schedule(timerTask, beginning.getTimeInMillis(), (1000 * 60 * 60 *24));
		 * 
		 * System.out.println("TimerTask started");
		 * 
		 * try { // 10 seconds delay before canceling the task
		 *  Thread.sleep(10000); }
		 * catch (InterruptedException e) {
		 *  e.printStackTrace(); 
		 *  }
		 * 
		 * // Removing the task 
		 * timer.cancel();
		 * System.out.println("TimerTask cancelled"); }
		 */

	}
}
