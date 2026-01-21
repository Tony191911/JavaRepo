package tw.brad.tutor;

import tw.brad.object.TWId;

public class Brad15TWId {

	public static void main(String[] args) {
		
		System.out.println(TWId.isRight("A123456789"));
		System.out.println("-----");
		TWId id1 = new TWId();
		TWId id2 = new TWId(false);
		TWId id3 = new TWId('P');
		TWId id4 = new TWId(true, 'C');
		System.out.println(id1);
		System.out.println(id2);
		System.out.println(id3);
		System.out.println(id4);
		System.out.println("-----");
		TWId id5 = null;
		try {
			id5 = TWId.createTWId("A123456787");
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println(id5);
		
	}

}
