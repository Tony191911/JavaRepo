package tw.brad.datastructure;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Set03 {

	public static void main(String[] args) {
		Set<Integer> lottery = new TreeSet<>();
		while (lottery.size() < 6) {
			lottery.add((int)(Math.random() * 49 + 1));
		}
		for (Integer i : lottery) {
			System.out.println(i);
		}

	}

}
