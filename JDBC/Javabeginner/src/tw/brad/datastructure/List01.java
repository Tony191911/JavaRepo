package tw.brad.datastructure;

import java.util.ArrayList;
import java.util.List;

public class List01 {

	public static void main(String[] args) {
		List<String> names = new ArrayList<>();
		names.add(0, "Brad");
		names.add(0, "Andy");
		names.add(0, "Brad");
		names.add(0, "Tony");
		names.add(0, "Mark");
		names.add(0, "Eric");
		System.out.println(names.size());
		for (String name: names) {
			System.out.println(name);
		}
		System.out.println("---");
		System.out.println(names.get(0));
		System.out.println(names.get(3));

	}

}
