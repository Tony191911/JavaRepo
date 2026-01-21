package tw.brad.streaming;

public class Thread01 {

	public static void main(String[] args) {
		MyThread mt1 = new MyThread("A");
		MyThread mt2 = new MyThread("B");
		MyRunable mr1 = new MyRunable("C");
		Thread t1 = new Thread(mr1);
		
//		mt1.setDaemon(true);
//		mt2.setDaemon(false);
//		t1.setDaemon(true);
		
		mt1.start();
		mt2.start();
//		mt1.start();
		t1.start();
		System.out.println("Main Finish");
	}
}

class MyThread extends Thread {
	private String name;
	MyThread(String name){this.name = name;}
	@Override
	public void run() {
		for (int i=0; i<20 ; i++) {
			System.out.println(name + ":" + i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
			}
		}
	}
}


class MyRunable implements Runnable {
	private String name;
	MyRunable(String name){this.name = name;}
	@Override
	public void run() {
		for (int i=0; i<10 ; i++) {
			System.out.println(name + ":" + i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
			}
		}
	}
}