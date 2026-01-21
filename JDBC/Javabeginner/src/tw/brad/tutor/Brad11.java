package tw.brad.tutor;

import tw.brad.object.Bike;

public class Brad11 {

	public static void main(String[] args) {
		Bike bike1 = new Bike();
		Bike bike2 = new Bike();
		System.out.println(bike1);
		System.out.println(bike2);
		System.out.println(bike1.getSpeed());
		System.out.println(bike2.getSpeed());
		
		bike1.upSpeed();bike1.upSpeed();bike1.upSpeed();bike1.upSpeed();
		System.out.println(bike1.getSpeed());
		
		for (int i = 0; i<10 ; i++) {
			bike2.upSpeed();
		}
		System.out.println(bike2.getSpeed());

	}

}
