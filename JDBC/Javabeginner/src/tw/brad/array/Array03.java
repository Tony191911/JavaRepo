package tw.brad.array;

public class Array03 {

	public static void main(String[] args) {
//		double temp = Math.random() * 6 + 1;
//		int score = (int)temp;
//		System.out.println(score);

		int[] p = new int[7];

		for (int i = 0; i < 1000000; i++) {
			int point = (int) (Math.random() * 9) + 1;    // 1 ~ 9
			if (point >= 1 && point <= 9) {
				p[point>=7?point-3:point]++;
			} else {
				p[0]++;
			}
		}
		if (p[0] > 0) {
			System.out.println("ERROR:" + p[0]);
		} else {
			for (int i = 1; i < p.length; i++) {
				System.out.printf("%d點出現%d\n", i, p[i]);
			}

		}
	}

}
