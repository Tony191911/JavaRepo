package tw.brad.streaming;

import java.io.BufferedReader;
import java.io.FileReader;

public class Input03 {

	public static void main(String[] args) {
		// 小括號只能寫可自動關閉的東西，而剛好串流物件都需要有關閉的動作
		// 把File裝進FileReader再裝進BufferedReader
		// BufferedReader一次抓一大把資料(8192個字元約16KB)存進記憶體的緩衝區(一個陣列容器)，比直接讀取快很多
		// reader(接通檔案到記憶體的字元讀取器)，br(在記憶體中事先框出容量為8192個字元的緩衝區)
		// 先由reader把檔案跟記憶體接起來，再換br在記憶體劃分出一個緩衝區，readLine讀取時會從檔案搬資料到記憶體上，直到填滿整個緩衝區
		try (
			FileReader reader = new FileReader("dir1/ns1hosp.csv"); 
			BufferedReader br = new BufferedReader(reader) ) 
		{
			// 用readLine方法會掃描緩衝區的字元直到看到換行符號(\n或\r\n)，然後把這段字元打包成完整的String
			// csv檔將數據以逗號分隔，用split(",")按照逗號切分資料，每個資料點會對應一個欄位
			// 最後顯示第3欄和第8欄的全部資料
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				System.out.printf("%s : %s\n", data[2], data[7]);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
