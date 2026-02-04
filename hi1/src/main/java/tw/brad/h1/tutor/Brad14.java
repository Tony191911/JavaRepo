package tw.brad.h1.tutor;

import tw.brad.h1.dao.SCDao;
import tw.brad.h1.entity.Student;

public class Brad14 {

	public static void main(String[] args) {
		SCDao dao = new SCDao();
		dao.save(new Student("Brad"));
		dao.save(new Student("Tony"));
		dao.save(new Student("Alex"));
		dao.save(new Student("Eric"));
		dao.save(new Student("Amy"));
	}

}
