package tw.brad.datastructure;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Map01 {

	public static void main(String[] args) {
		Map<String, Object> maps = new HashMap<>();
		maps.put("name", "Brad");
		maps.put("age", 18);
		maps.put("w", 80.1);
		maps.put("g", true);
		maps.put("w", 82);
		System.out.println(maps.get("name"));
		System.out.println(maps.get("age"));
		System.out.println("-----");
		Set<String> keys = maps.keySet();
		for (String key : keys) {
			System.out.println(key + ":" + maps.get(key));
		}
		
	}

}
