package tw.brad.hi2;

import java.util.List;

import org.hibernate.Session;

import tw.brad.hi2.util.HibernateUtil;

public class Brad04 {

	public static void main(String[] args) {
		try(Session session = HibernateUtil.getSessionFactory().openSession();) {
			String sql = """
					SELECT EmployeeID, FirstName fname, LastName, Title
					FROM employees
					ORDER BY Title ASC, LastName DESC
					""";
			List<Object[]> employees =
					session.createNativeQuery(sql, Object[].class).getResultList();
			System.out.println(employees.size());
			System.out.println("-----");
			for (Object[] e : employees) {
				System.out.printf("%d:%s:%s\n",
							e[0],
							e[1],
							e[2]);
			}
			
		}catch (Exception e) {
			System.out.println(e);
		}
	}

}
