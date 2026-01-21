package tw.brad.streaming;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import tw.brad.object.Student;

public class Stream04 {

	public static void main(String[] args) {
		try(ObjectInputStream oin = new ObjectInputStream(new FileInputStream("dir1/brad.score"))
		) {
			// 強制轉成Student類別，確保能當作Student物件呼叫方法
			// 因為寫入順序是(s1->s2)，要先進先出，所以讀取時s3是s1、s4是s2
			// 第三次執行readObject就沒東西可以還原了，會噴出Exception
			Student s3 = (Student)oin.readObject();
			System.out.printf("%s : %d : %f : %s\n", s3.getName(), s3.score(), s3.avg(), s3.getBike());
			Student s4 = (Student)oin.readObject();
			System.out.printf("%s : %d : %f\n", s4.getName(), s4.score(), s4.avg());

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
