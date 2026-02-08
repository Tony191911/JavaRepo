package tw.brad.h1.service;

import java.util.List;

import tw.brad.h1.entity.Order;
import tw.brad.h1.entity.OrderItem;

public interface OrderService {
//	建立訂單
	Long createOrder(String customer);
//	建立訂單並加入多個商品項目
	Long createOrderWithItems(String customer, List<OrderItem> items);
//	修改客戶名稱
	void changeCustomerName(Long id, String newName);
//	新增一筆商品項目
	void addItem(Long id, String pname, int qty, int price);
//	修改單一筆品項的的商品數量
	void updateItemQty(Long orderId, Long itemId, int newQty);
//	移除一筆商品項目
	void removeItem(Long orderId, Long itemId);
//	取得訂單的所有商品項目
	Order getOrderWithItem(Long orderId);
//	刪除訂單
	void deleteOrder(Long orderId);
}
