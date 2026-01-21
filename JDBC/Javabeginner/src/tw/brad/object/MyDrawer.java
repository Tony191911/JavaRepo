package tw.brad.object;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

// 外部類別
// 用JPanel面板當作畫布放到JFrame裡面
public class MyDrawer extends JPanel {
	// 宣告私有屬性 lines, recycle，用來存放自訂型別 Line 的清單 (List)
	// 宣告私有屬性 defaultColor, defaultWidth，作為線的預設顏色及粗細
	// 屬性定義：封裝線條資料(lines)、資源回收桶(recycle)及繪圖預設值
	private List<LineV2> lines, recycle;
	private Color defaultColor;
	private float defaultWidth;
	
	// 無傳參數建構式 (No-Arg/Default Constructor)：初始化物件
	// 建立畫布實體並配置初始環境
	public MyDrawer() {
		// from JPanel 的 JComponent
		setBackground(Color.yellow);
		
		// 初始化一個 ArrayList 物件並指派給 lines, recycle
		// 初始化容器實體，確保繪圖資料有存放空間
		lines = new ArrayList<>();
		recycle = new ArrayList<>();
		
		// 初始化一個 Color 類別的靜態常數 BLUE 並指派給 defaultColor
		// 初始化一個 float 基本型別的常數並指派給 defaultWidth
		// 設定物件的預設屬性數值
		defaultColor = Color.BLUE;
		defaultWidth = 4f;
		
		// 內部類別 MyListener
		// 建立監聽器實體，建立物件(只要滑鼠點擊或拖曳時把座標點放進容器的物件裡)
		MyListener listener = new MyListener();
		
		// from JPanel 的 Component
		// 監聽滑鼠是否有沒有按下去？現在位置在哪裡？
		// 監聽listener有移動位置時，MyListener物件的mouseDragged裡的repaint被觸發後，
		// ->呼叫paintComponent，觸發drawLine開始畫線
		addMouseListener(listener);
		addMouseMotionListener(listener);
	}
	
	
	// paintComponent from JPanel(直接父類別) 的 JComponent(間接父類別)
	// 覆寫JComponent的 paintComponent方法
	@Override
	protected void paintComponent(Graphics g) {
		// paintComponent方法由JPanel要求必須有一個「畫筆」物件才能執行繪圖
		super.paintComponent(g);

		// 已宣告為Graphics類別的參數g強制轉型成Graphics2D類別，並指派為變數g2d
		Graphics2D g2d = (Graphics2D)g;
		
		// 這裡的line是二維陣列，每個index都指向一個line的ArrayList物件
		// 即取出所有線當中的一條線，根據這條線的當前座標點和前一個座標點來畫出實際線段
		for (LineV2 line : lines) {
			// 設定每條線的顏色和粗細
			g2d.setColor(line.getColor());
			g2d.setStroke(new BasicStroke(line.getWidth()));
			
			// i從1開始，因為必須同時抓住兩個點才能拉出一條直線
			for (int i = 1; i< line.getSize(); i++) {
				g2d.drawLine(
					line.getPointX(i - 1), line.getPointY(i - 1), // 前一個座標點(x, y)
					line.getPointX(i), line.getPointY(i) // 當前座標點(x, y)
				);
			}
			// BasicStroke from BasicStroke
			// drawLine from Graphics
			// getX/Y() from MouseEvent
		}

	}
	
	// 內部類別(Inner Class)，可直接存取外部 MyDrawer 的私有屬性
	// 除了面板還需要畫筆，把滑鼠當作筆，用MouseAdapter監聽滑鼠
	// 用MouseAdapter，而不是用MouseListener(需先把方法都寫好才能用，不好用)
	// MouseAdapter把方法寫好，只需extends挑想要的Override(覆寫)就好
	private class MyListener extends MouseAdapter {

		// 覆寫MouseAdapter的 mousePressed, mouseDreagged方法
		// mousePressed方法，當滑鼠按下的瞬間
		// 建立Line物件=>捕捉點下去的座標=>放進lines屬性(指向一個ArrayList物件)=>最後把recycle清空
		@Override
		public void mousePressed(MouseEvent e) {			
			LineV2 line = new LineV2(defaultColor, defaultWidth);
			line.addPoint(e.getX(), e.getY());
			lines.add(line);
			
			recycle.clear();
			// getX/Y() from MouseEvent
			// add(), clear() from List
		}
		
		// mouseDragged方法，當滑鼠拖曳的這段時間
		// 抓取「當前」線段(由一連串的點組成)=>更新畫面
		@Override
		public void mouseDragged(MouseEvent e) {
			// 持續向lines屬性(指向一個ArrayList物件)的最後位置追加座標點
			// 重新整理畫面，沒有repaint()就會顯示不出線
			lines.getLast().addPoint(e.getX(), e.getY());
			repaint();
			
			// getX/Y() from MouseEvent
			// repaint() from JPanel 的 Component
		}
	}
	// clear方法，使用List類別的clear方法清除掉lines裡的全部物件並更新畫面
	public void clear() {
		lines.clear();
		repaint();
	}
	
	// undo方法，當lines裡面有線時移除最後畫上去的線，並把它塞進recycle(資源回收桶)然後更新畫面
	public void undo() {
		if (lines.size() >0) {
			recycle.add(lines.removeLast());
			repaint();			
		}
	}
	
	// redo方法，當recycle(資源回收桶)裡面有線時移除最後一條線，並把它塞進lines(顯示清單)然後更新畫面
	// 即把最後一條線重新顯示到畫布上
	public void redo() {
		if (recycle.size() >0) {
			lines.add(recycle.removeLast());
			repaint();
		}
	}
	
	// changeColor方法，更新defaultColor屬性變成外部參數的值
	// 即更換畫筆的顏色，但不影響已畫好的線，只影響之後要畫得線
	public void changeColor(Color newColor) {defaultColor = newColor;}
	
	// getColor方法，讓外部（MySign 的調色盤）知道目前選中的顏色是什麼
	public Color getColor() {return defaultColor;}
	
	// changeWidth方法，更新defaultWidth屬性變成外部參數的值
	// 即更換線條粗細，同樣只會影響之後要畫得線
	public void changeWidth(float width) {defaultWidth = width;}
	
	
	// saveLines方法，儲存序列化的檔案
	// ObjectOutputStream建構式傳入的參數必須是OutputStream類別
	// OutputStream類別是abstract(抽象類別)，要使用子類別(Subclasses)FileOutputStream
	// 所有的子類別都繼承了「把資料寫出去」的基本能力
	// FileOutputStream物件：基礎水管（字節流），負責把二進位數據寫進硬碟
	// ObjectOutputStream物件：轉換器（過濾流），負責將物件轉成二進位數據
	// 轉換過程稱為序列化(Serialization)，會把LineV2物件裡面的顏色、座標、粗細等屬性拆解並打包成二進位數據
	// try()：Try-with-resources，不加小括號檔案會被鎖死，無法對檔案做任何動作
	// 加上小括號不論儲存成功還是中途出錯，都會自動把這根「水管」拔掉並釋放記憶體
	// 使用 throws Exception 就不需要把要處理的例外順序先寫好，可簡化代碼
	public void saveLines(File file) throws Exception{
		try (ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(file) )
			) {
			oout.writeObject(lines);
			// FileOutputStream from OutputStream(abstract)
			// writeObject from ObjectOutputStream
		}
	}
	
	// loadLines方法，載入序列化的檔案並還原成lines物件
	// 1.解鎖檔案 2.格式轉換 3.身份驗證 4.畫面更新
	// obj instanceof List，判斷obj是不是List類型的物件，是就強制轉型成LineV2的物件
	// 如果 instanceof 檢查失敗，則告訴呼叫者「這不是我要的資料」
	public void loadLines(File file) throws Exception{
		// 建立物件輸入流(反序列化)
		try (ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file) )
			) {
			// 讀取物件並檢查是否為正確的 List 格式
			Object obj = oin.readObject();
			if (obj instanceof List) {
				lines = (List<LineV2>)obj; // 還原數據
				repaint();				   // 同步畫面
				recycle.clear();			   // 清空待復原的紀錄
			}else {
				throw new Exception("你來亂的!");
			}
		}
	}
	
	public void saveJPEG() {
		BufferedImage bimg = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bimg.createGraphics();
		paint(g2d);
		g2d.dispose();
		try {
			ImageIO.write(bimg, "JPEG", new File("dir1/brad.jpg"));
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	
}

