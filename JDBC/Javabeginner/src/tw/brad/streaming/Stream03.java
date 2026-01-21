package tw.brad.streaming;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import tw.brad.object.Student;

public class Stream03 {

	public static void main(String[] args) {
		Student s1 = new Student("Brad", 70, 40, 32);
		s1.getBike().upSpeed().upSpeed().upSpeed();
		System.out.printf("%s : %d : %f : %s\n", s1.getName(), s1.score(), s1.avg(), s1.getBike());
		Student s2 = new Student("Tony", 98, 90, 86);
		System.out.printf("%s : %d : %f\n", s2.getName(), s2.score(), s2.avg());
		// fout(將記憶體接通到檔案的輸出流)，oout(將物件序列化的物件輸出流)
		try(FileOutputStream fout = new FileOutputStream("dir1/brad.score");
			ObjectOutputStream oout = new ObjectOutputStream(fout);)
		{
			// writeObject 負責序列化(將資料轉成bytes)並寫入檔案
			oout.writeObject(s1);
			oout.writeObject(s2);			
			oout.flush();
			System.out.println("OK");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
