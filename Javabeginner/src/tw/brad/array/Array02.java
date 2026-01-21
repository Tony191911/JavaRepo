package tw.brad.array;

public class Array02 {

	public static void main(String[] args) {
//		double temp = Math.random() * 6 + 1;
//		int score = (int)temp;
//		System.out.println(score);
		
		int p0, p1, p2, p3, p4, p5, p6;
		p0 = p1 = p2 = p3 = p4 = p5 = p6 = 0;
		
		for (int i=0; i<100 ; i++) {
			int point = (int)(Math.random()* 6) + 1;  // 1 ~ 6
			switch (point) {
			case 1 : p1++; break;
			case 2 : p2++; break;
			case 3 : p3++; break;
			case 4 : p4++; break;
			case 5 : p5++; break;
			case 6 : p6++; break;
			default: p0++;
			}
		}
		if (p0 > 0) {
			System.out.println("ERROR:" + p0);
		}else {
			System.out.printf("%d點出現%d\n", 1, p1);
			System.out.printf("%d點出現%d\n", 2, p2);
			System.out.printf("%d點出現%d\n", 3, p3);
			System.out.printf("%d點出現%d\n", 4, p4);
			System.out.printf("%d點出現%d\n", 5, p5);
			System.out.printf("%d點出現%d\n", 6, p6);
		}
	}

}
