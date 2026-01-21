package tw.brad.object;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LineV2 implements Serializable{
	// 宣告一個私有屬性，是用來存放自訂型別Point 的清單 (List)
	private List<Map<String, Integer>> points;  // 線的軌跡：由一連串的點組成
	private Color color;
	private float width;
	
	public LineV2(Color color, float width) {
		points = new ArrayList<Map<String, Integer>>();
		this.color = color;
		this.width = width;
	}
	

	public void addPoint(int x, int y) {
		// 宣告變數p為自訂型別Point，並指派新物件Point(x, y)給變數
		Map<String, Integer> p = new HashMap<String, Integer>();
		p.put("x", x); p.put("y", y);
		points.add(p);
	}
	
	public int getPointX(int i) {
		return points.get(i).get("x");  // Integer unboxing int
	}
	public int getPointY(int i) {
		return points.get(i).get("y");
	}
	public int getSize() {return points.size(); }
	
	public Color getColor() {return  color; }
	
	public float getWidth() {return width; }
	
}
