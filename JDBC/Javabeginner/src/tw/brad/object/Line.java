package tw.brad.object;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Line {
	// 宣告一個私有屬性，是用來存放自訂型別 Point 的清單 (List)
	// List是介面，用 List 介面來宣告
	// ArrayList是實作類別，用來實作 List 介面
	private List<Point> points;  // 線的軌跡：由一連串的點組成
	private Color color;  // 線的顏色
	private float width;  // 線的粗細
	
	// 建構式 (Constructor)：初始化物件
	// 這是一個紀錄畫筆位置資訊的 Line 物件，可設定顏色和粗細
	public Line(Color color, float width) {
		// 實例化/具體化（Instantiate）一個 ArrayList 物件並指派給 points
		points = new ArrayList<Point>();
		this.color = color;
		this.width = width;
	}
	
	// addPoint方法，用來紀錄線的軌跡，每組資料都是有x, y 座標的一個點
	public void addPoint(int x, int y) {
		// 宣告變數p為自訂型別Point，並指派新物件Point(x, y)給變數p
		// 1. 將傳入的座標封裝成一個新的 Point 物件實體
		Point p = new Point(x, y);
		// 使 p 這個包含 x, y 座標的 Point 物件存入 points 清單裡面
		// 2. 將這個物件的引用(Reference)新增到清單中，作為線段的一個軌跡點
		points.add(p);
	}
	
	// getPointX, getPointY方法，根據索引值 i 找出 points容器裡的 第 i 個 Point 物件的x座標或y座標
	// get(i) from List, getX/Y() from Point
	public int getPointX(int i) {
		return points.get(i).getX();
	}
	public int getPointY(int i) {
		return points.get(i).getY();
	}
	
	// getSize方法，回傳 points 容器中 Point 物件的總數，即取得這條線目前有多少個點
	public int getSize() {return points.size();}
	// getColor方法，取得線的顏色
	public Color getColor() {return color;}
	// getWidth方法，取得線的粗細
	public float getWidth() {return width;}
	
}
