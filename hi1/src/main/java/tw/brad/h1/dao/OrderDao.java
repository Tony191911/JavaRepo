package tw.brad.h1.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;

import tw.brad.h1.entity.Order;

public interface OrderDao {
//	儲存訂單
	Long save(Session session, Order order);
//	用 id 查找訂單
	Optional<Order> findById(Session session, Long id);
//  用 id 查找訂單及明細
	Optional<Order> findByIdWithItems(Session session, Long id);
//	刪除訂單
	void delete(Session session, Order order);
//	查找全部訂單
	List<Order> findAll(Session session, int start, int size);
}
