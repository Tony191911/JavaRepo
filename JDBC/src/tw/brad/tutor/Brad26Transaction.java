package tw.brad.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import tw.brad.transaction.PurchaseTask;
import tw.brad.transaction.RestockTask;
import tw.brad.transaction.StoreService;
import tw.brad.transaction.Task;

public class Brad26Transaction {
	private static final String URL = "jdbc:mysql://localhost:3306/iii";
	private static final String USER = "root";
	private static final String PASSWD = "root";
	private static final BlockingQueue<Task> QUEUE = new ArrayBlockingQueue<>(200);
	private static final AtomicBoolean RUNNING = new AtomicBoolean(true);
	
	public static void main(String[] args) throws Exception{
		try(Connection conn = DriverManager.getConnection(URL, USER, PASSWD);) 
		{
			System.out.println("Connection OK");
		}
		// RUNNING == true
		// Producer 一直進貨/買貨 -> QUEUE
		// logger 一直紀錄 -> QUEUE
		// Main Thread -> 喊停 / 跑迴圈
		
		ExecutorService producer = Executors.newFixedThreadPool(2);
		ExecutorService writer = Executors.newFixedThreadPool(4);
		
		producer.submit(new Producer());
		producer.submit(new Producer());
		
		writer.submit(new Writer());
		writer.submit(new Writer());
		writer.submit(new Writer());
		writer.submit(new Writer());
		
		Thread.sleep(3*1000);
		RUNNING.set(false);
		producer.shutdown();
		System.out.println("Game Over");
	}
	static class Producer implements Runnable {
		private final Random r = new Random();
		@Override
		public void run() {
			try {
				while (RUNNING.get()) {
					Task t;
					if (r.nextInt(100) < 70) {
						t = new PurchaseTask(1 + r.nextInt(4));
					}else {
						t = new RestockTask(1 + r.nextInt(6));
					}
					QUEUE.put(t);
					Thread.sleep(30);
				}
			} catch (Exception e) {
			}
		}
	}
	static class Writer implements Runnable {
		@Override
		public void run() {
			StoreService service = new StoreService(URL, USER, PASSWD);
			try {
				while (RUNNING.get() || !QUEUE.isEmpty()) {
					Task t = QUEUE.poll(200, TimeUnit.MILLISECONDS);
					if (t == null) continue;
					try {
						t.execute(service);
						System.out.println(t.label());
						
					} catch (Exception e) {
						System.out.println(e);
					}
				}
			} catch (Exception e) {
				
			}
			
		}
	}
}
