package tw.brad.practice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import tw.brad.object.MyClock;
import tw.brad.object.MyDrawer;

public class MySign extends JFrame {
	private  MyDrawer myDrawer;
	private JButton clear, undo, redo, color, saveObj, loadObj, saveJPEG;
	private JSlider width;
	private MyClock myClock;
	
	public MySign() {
		// from JFrame
		// 初始化標題，也可以用setTitle()
		super("MySign");
		
		// MyDrawer from JPanel
		// setLayout from JFrame 的 Container
		// 準備物件 → 決定佈局規則 → 放置物件到指定位置
		myDrawer = new MyDrawer();
		setLayout(new BorderLayout());
		add(myDrawer, BorderLayout.CENTER);
		
		// top：建立一個「小容器」JPanel，並設定其「排隊規則」FlowLayout
		// 建立clear(清除), undo(撤銷), redo(恢復), color(選顏色)按鈕, width(選粗細)滑動條等物件
		// =>放到top容器裡=>放置到BorderLayout裡的正上方位置
		JPanel top = new JPanel(new FlowLayout());
		clear = new JButton("Clear");
		undo = new JButton("Undo");
		redo = new JButton("Redo");
		color = new JButton("Color");
		saveObj = new JButton("Save Lines");
		loadObj = new JButton("Load Lines");
		saveJPEG = new JButton("Save Jpeg");
		width = new JSlider(10, 200, 40);
		myClock = new MyClock();
		top.add(clear);	top.add(undo);	top.add(redo);
		top.add(color); 
		top.add(saveObj); top.add(loadObj);
		top.add(saveJPEG);
		top.add(width);
		top.add(myClock);
		add(top, BorderLayout.NORTH);
		
		// from JFrame
		// 設定視窗大小、顯示視窗、關閉行為(關閉視窗，同時結束程式)
		setSize(1024, 480);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// 初始化所有按鈕的事件邏輯
		initEvent();
	}
	// initEvent方法，把監聽器裝到按鈕和滑動條上，並設定事件邏輯
	// addActionListener(ActionListener l) from AbstractButton
	// addChangeListener(ChangeListener l) from AbstractButton
	// ActionListener/ChangeListener()是Interface(介面)，不能直接new，後面必須接大括號{actionPerformed/stateChanged方法}
	// new ActionListener/ChangeListener(){}是Anonymous Inner Class(匿名內部類別)
	private void initEvent() {
		// 加上動作監聽器到按鈕上，被按了就產生ActionEvent物件
		// 呼叫監聽器的actionPerformed方法
		// 監聽器執行清空myDrawer裡的資料，然後更新畫面變回空白
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myDrawer.clear();
			}
		});
		
		// 監聽器執行刪掉myDrawer裡最後一條線，然後更新畫面
		undo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				myDrawer.undo();
			}
		});
		
		// 監聽器執行復原myDrawer裡刪掉的最後一條線，然後更新畫面
		redo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				myDrawer.redo();
			}
		});
		
		// 監聽器執行彈出視窗並可選取新顏色，確定後改變myDrawer線的顏色
		color.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 呼叫定義好的方法來處理顏色切換邏輯
				changeColor();
			}
		});
		
		// 加上「狀態改變監聽器」到滑動條上，當數值變動就產生 ChangeEvent 物件
		// 呼叫監聽器的 stateChanged 方法
		// 抓取滑動條數值並轉換比例，更新 myDrawer 接下來線的粗細
		width.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				myDrawer.changeWidth(width.getValue() * 0.1f);
			}
			// getValue from JSlider
		});
		
		// 監聽器執行彈出視窗可輸入要儲存的路徑跟檔名
		saveObj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				saveLines();
			}
		});
		
		// 監聽器執行彈出視窗可輸入要載入的路徑跟檔名
		loadObj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadLines();
			}
		});
		
		saveJPEG.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				myDrawer.saveJPEG();
			}
		});
	}
	
	// changeColor方法，彈出顏色選擇對話框，並更新畫布的畫筆顏色
	private void changeColor() {
		Color color = JColorChooser.showDialog(null, "Change Color", myDrawer.getColor());
		if (color != null) {
			myDrawer.changeColor(color);
		}
		// JColorChooser.showDialog(Component component, String title, Color initialColor)
		// null：代表這個對話框不屬於任何特定視窗(置中顯示)
		// "Change Color"：調色盤視窗左上角的標題文字
		// myDrawer.getColor()：取得myDrawer目前的顏色，調色盤打開時預設目前顏色
		// color != null，代表點擊「確定」，則更新畫布顏色
	}
	
	// saveLines/loadLines方法
	// JFileChooser建構式：檔案選擇工具
	// showSaveDialog/showOpenDialog(null)方法：阻塞程式，直到選好路徑按下「存檔」或「取消」
	// APPROVE_OPTION靜態方法：代表真的點了「確定/存檔」按鈕
	// getSelectedFile方法：抓取在視窗中輸入的檔案名稱與路徑
	// saveLines方法：呼叫先前寫好的「序列化」方法
	// showMessageDialog靜態方法：若存檔失敗，跳出對話框
	private void saveLines() {
		JFileChooser jfc = new JFileChooser();
		if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			File saveFile = jfc.getSelectedFile();
			try {
				myDrawer.saveLines(saveFile);
			} catch (Exception e) {
				// null：對話框會出現在螢幕的正中央，它不屬於任何視窗
				JOptionPane.showMessageDialog(null, "ERROR");
			}
			// showSaveDialog, APPROVE_OPTION, getSelectedFile from JFileChooser
			// showMessageDialog from JOptionPane
		}
	}
	private void loadLines() {
		JFileChooser jfc = new JFileChooser();
		if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File loadFile = jfc.getSelectedFile();
			try {
				myDrawer.loadLines(loadFile);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ERROR");
			}
			// showOpenDialog, APPROVE_OPTION, getSelectedFile from JFileChooser
			// showMessageDialog from JOptionPane
		}
	}
	
	
	public static void main(String[] args) {
		// 建立MySign物件
		new MySign();
	}
}
