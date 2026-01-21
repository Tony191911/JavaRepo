package tw.brad.streaming;

import java.io.File;
import java.io.IOException;

public class File03 {

	public static void main(String[] args) {
		// "."：現在所在的路徑或資料夾
		File root = new File(".");
		// 絕對路徑的寫法是從根講起，否則就是相對路徑
		System.out.println(root.getAbsolutePath());
		File dir1 = new File("./dir1");
		File dir2 = new File("./dir2");
		File dir3 = new File("./dir3");
		System.out.println(dir1.exists());
		System.out.println(dir2.exists());
		if (!dir3.exists()) {
			// mkdir 創建資料夾
			dir3.mkdir();
		}else {
			System.out.println("dir3 exist");
		}
		System.out.println("-----");
		File f1 = new File("dir1/file1.txt");
		if (!f1.exists()) {
			try {
				f1.createNewFile();				
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		
	}

}
