package dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import domain.Carts;
import domain.Orders;

public interface IOrdersDAO 
{
	//添加一个商品到数据库中
	public void addOrders(Orders order);
		
	//根据订单ID返回商品信息，以Orders对象的形式返回
	public Orders getOrdersByID(String did);

	//变更订单状态
	public void updateOrder(String did, int i);

	//返回order 中的 did集合
	public Set<String> getOrderSet(String uid);
	
	//根据did返回order.
	public Orders getOrderByDid(String did);

	//根据 order 返回 order.uid 和 order.paytime 相同的 cart 集合
	public Set<Carts> getCartByOrder(Orders order);
}
