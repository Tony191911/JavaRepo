package tw.brad.object;

import java.io.Serializable;

// Point用來代表二維平面上的一個「點」
public class Point implements Serializable{
	// 宣告私有屬性：水平座標 x, 垂直座標 y
	// x, y分別對應到this.的x, y
	private int x, y;
	
	// 建構式 (Constructor)：初始化物件
	// 這是一個代表座標位置的 Point 物件
	public Point(int x, int y) {
		// 等號左邊是當前已被new出來的物件屬性
		// 等號右邊的值是小括號傳進來的外部參數
		this.x = x; this.y = y;
	}
	
	// getX(), getY()的int是宣告這個方法的回傳值型別（Return Type）
	// getX/Y()方法，取得 Point 物件的私有屬性x, y
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
