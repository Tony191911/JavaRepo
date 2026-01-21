package tw.brad.tutor;

import tw.brad.object.Scooter;

public class Brad12 {

	public static void main(String[] args) {
		// 初始化Scooter()，new 建構一個物件
		Scooter s1 = new Scooter();
		System.out.println(s1.getColor());
		System.out.println("-----");
		s1.upSpeed();s1.upSpeed();s1.upSpeed();
		s1.upSpeed();s1.downSpeed();
		System.out.println(s1.getSpeed());
		
		s1.setGear(2);
		s1.upSpeed();s1.upSpeed();
		System.out.println(s1.getSpeed());
		
		Scooter s2 = new Scooter("red");
		System.out.println(s2.getColor());
	}

}
