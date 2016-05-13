package service.imp;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.management.RuntimeErrorException;

import dao.IOrdersDAO;
import dao.IUserDAO;
import dao.imp.OrderDAO;
import dao.imp.UserDAO;
import domain.Carts;
import domain.Orders;
import service.IOrderService;

public class OrderService implements IOrderService
{

	@Override
	public String createDid(String uid) 
	{
		if(uid.length()!=12)
			throw new RuntimeErrorException(null, "Uid格式不正确");
		String did = new String();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = sdf.format(date);
		did = uid+str;
		System.out.println("OrderService.jav | createDid | uid : "+uid + "\tdate : "+str+"\tDid : "+did);
		return did;
	}
	
	public String getDate()
	{
		Date date = new Date();
		System.out.println(date.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
		String sdate = sdf.format(date);
		return sdate;
	}
	
	public Timestamp getTime()
	{
		Date date = new Date();
		Timestamp tstamp = new Timestamp(date.getTime());
		return tstamp;
	}
	
	@Override
	public void addToOrder(Orders order) 
	{
		System.out.println("OrderService.java | addToOrder | order : " + order);
		IOrdersDAO orderDao = new OrderDAO();
		orderDao.addOrders(order);
	}


	public void updateSuccess(String did) 
	{
		// TODO Auto-generated method stub
		IOrdersDAO orderDao = new OrderDAO();
		orderDao.updateOrder(did, 2);
	}

	public void updateFail(String did) {
		// TODO Auto-generated method stub
		IOrdersDAO orderDao = new OrderDAO();
		orderDao.updateOrder(did, 3);
	}

	public static void main(String[] arg)
	{
		OrderService os =new OrderService();
		//System.out.println(os.createDid("10000000"));
		System.out.println(os.getDate());
		System.out.println(os.getTime());
	}

	@Override
	public Orders getOrderByDid(String did) 
	{
		// TODO Auto-generated method stub
		IOrdersDAO orderDao = new OrderDAO();
		return orderDao.getOrdersByID(did);
	}

	@Override
	public Map<String, Set<Carts>> getOrderMap(String uid) 
	{
		IOrdersDAO orderDao = new OrderDAO();
		
		Set<String> didSet = orderDao.getOrderSet(uid);
		System.out.println("OrderService | getOrderMap | 订房单Did集合: " + didSet+"\t" + didSet.size());
		Map<String, Set<Carts>> mapcartSet = new HashMap<>();
		Set<Carts> cartSet;
		Orders order;
		Iterator<String> didIt = didSet.iterator();
		while(didIt.hasNext())
		{
			String did = didIt.next();
			order = orderDao.getOrderByDid(did);
			cartSet = orderDao.getCartByOrder(order);
			if(cartSet.size()>0)
			{
				mapcartSet.put(did, cartSet);
			}
		}
		
		return mapcartSet;
	}

	@Override
	/**
	 * 根据uid返回对应的did的Set。
	 */
	public Set<String> getOrderSet(String uid) 
	{
		IOrdersDAO orderDao = new OrderDAO();
		return orderDao.getOrderSet(uid);
	}

	//根据uid 返回 map<did, Orders>
	@Override
	public Map<String, Orders> getDidOrder(String uid) 
	{
		IOrdersDAO orderDao = new OrderDAO();
		Set<String> didSet = orderDao.getOrderSet(uid);
		Map<String, Orders> mapdid = new HashMap<>();
		//Set<Orders> orderSet = new HashSet<>();
		Orders order;
		Iterator<String> didIt = didSet.iterator();
		while(didIt.hasNext())
		{
			String did = didIt.next();
			order = orderDao.getOrderByDid(did);
			mapdid.put(did, order);
		}
		return mapdid;
	}
	
	

}
