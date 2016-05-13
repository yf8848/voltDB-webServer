package service;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Set;

import domain.Carts;
import domain.Orders;

public interface IOrderService 
{
	public String createDid(String uid);

	public void addToOrder(Orders order);
	
	public Timestamp getTime();

	//public boolean IsPaywdRight(String paywd, String uid);

	public void updateSuccess(String did);

	public void updateFail(String did);

	public Orders getOrderByDid(String did);
	
	public Set<String> getOrderSet(String uid);

	public Map<String, Set<Carts>> getOrderMap(String uid);

	public Map<String, Orders> getDidOrder(String did);

}
