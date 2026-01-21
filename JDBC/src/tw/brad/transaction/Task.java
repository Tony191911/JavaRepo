package tw.brad.transaction;

public interface Task {
	void execute(StoreService service) throws Exception;
	String label();
}
