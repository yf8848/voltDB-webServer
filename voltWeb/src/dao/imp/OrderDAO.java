package dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import dao.IDatabaseDAO;
import dao.IOrdersDAO;
import domain.Carts;
import domain.Orders;

public class OrderDAO implements IOrdersDAO
{

	public OrderDAO()
	{}
	
	@Override
	public void addOrders(Orders order) 
	{
		// TODO Auto-generated method stub
		IDatabaseDAO myDB = new DatabaseDAO();
		Timestamp time = order.getCreatetime();
		time.setHours(time.getHours()+8);
		String sql = "INSERT INTO orders(did,uid,recname,recaddr,recphtone,passcode,dprice,dstate,createtime) VALUES('"+order.getDid()+"','"+order.getUid()+"','"+order.getRecname()+"','"+order.getRecaddr()+"','"+order.getRecpthone()+"','"+order.getPassCode()+"','"+order.getDprice()+"','"+order.getDstate()+"','"+time +"')";
		
		try
		{
			System.out.println("OrderDAO.java | addOrders | sql :" + sql);
			myDB.executeSQL(sql);
		}
		//处理异常
		catch(SQLException sqlEX)
		{
			sqlEX.printStackTrace();
		}
		catch(ClassNotFoundException cnfEX)
		{
			cnfEX.printStackTrace();
		}
	}

	@Override
	public Orders getOrdersByID(String did) 
	{
		// TODO Auto-generated method stub
		IDatabaseDAO myDB =new DatabaseDAO();
		
		Orders order = new Orders();
		
		String sql = "SELECT * FROM orders where did = '"+did+"'";
		
		try
		{
			ResultSet mySet = myDB.getResultSet(sql);
			while(mySet.next())
			{
				order.setDid(mySet.getString("did"));
				order.setUid(mySet.getString("uid"));
				order.setRecname(mySet.getString("recname"));
				order.setRecaddr(mySet.getString("recaddr"));
				order.setRecpthone(mySet.getString("recphtone"));
				order.setPassCode(mySet.getString("passcode"));
				order.setDprice(mySet.getLong("dprice"));
				order.setDstate(mySet.getByte("dstate"));
				order.setCreatetime(mySet.getTimestamp("createtime"));
			}
		}
		//处理异常
		catch(SQLException sqlEX)
		{
			sqlEX.printStackTrace();
		}
		catch(ClassNotFoundException cnfEX)
		{
			cnfEX.printStackTrace();
		}
		finally
		{
			try
			{
				myDB.disConncted();
			}
			catch(SQLException sqlEx)
			{
				sqlEx.printStackTrace();
			}
		}
		return order;
	}

	@Override
	public void updateOrder(String did, int i) 
	{
		IDatabaseDAO myDB =new DatabaseDAO();
		String sql = "UPDATE orders SET dstate = '"+ i +"' where did = '"+did+"'";
		
		try
		{
			System.out.println("OrderDAO.java | updateOrder | sql : " + sql);
			myDB.executeSQL(sql);
		}
		//处理异常
		catch(SQLException sqlEX)
		{
			sqlEX.printStackTrace();
		}
		catch(ClassNotFoundException cnfEX)
		{
			cnfEX.printStackTrace();
		}
		finally
		{
			try
			{
				myDB.disConncted();
			}
			catch(SQLException sqlEx)
			{
				sqlEx.printStackTrace();
			}
		}	
	}

	@Override
	public Set<String> getOrderSet(String uid) 
	{
		IDatabaseDAO myDB = new DatabaseDAO();
		Set<String> didSet = new HashSet<>();
		
		String sql = "SELECT did FROM orders where uid = '"+uid+"' group by did";
		try
		{
			System.out.println("OrderDAO.java | getOrderSet | sql : " + sql);
			ResultSet mySet = myDB.getResultSet(sql);
			
			while(mySet.next())
			{
				didSet.add(mySet.getString("did"));
			}
		}
		catch(SQLException sqlEx)
		{
			sqlEx.printStackTrace();
		}
		catch(ClassNotFoundException cnfEx)
		{
			cnfEx.printStackTrace();
		}
		finally
		{
			try
			{
				//结果集使用完毕，关闭数据库操作的连接对象
				myDB.disConncted();
			}
			catch(Exception sqlEx)
			{
				sqlEx.printStackTrace();
			}
		}
		return didSet;
	}

	@Override
	public Orders getOrderByDid(String did) 
	{
		// TODO Auto-generated method stub
		IDatabaseDAO myDB = new DatabaseDAO();
		Orders order = new Orders();
		String sql = "SELECT * FROM orders where did = '"+did+"'";
		try
		{
			System.out.println("OrderDAO.java | getDidOrder | sql : " + sql);
			ResultSet mySet = myDB.getResultSet(sql);
			
			while(mySet.next())
			{
				order.setDid(did);
				order.setUid(mySet.getString("uid"));
				order.setRecname(mySet.getString("recname"));
				order.setRecpthone(mySet.getString("recphtone"));
				order.setRecaddr(mySet.getString("recaddr"));
				order.setPassCode(mySet.getString("passcode"));
				order.setDstate(mySet.getShort("dstate"));
				order.setDprice(mySet.getLong("dprice"));
				order.setCreatetime(mySet.getTimestamp("createtime"));
			}
		}
		catch(SQLException sqlEx)
		{
			sqlEx.printStackTrace();
		}
		catch(ClassNotFoundException cnfEx)
		{
			cnfEx.printStackTrace();
		}
		finally
		{
			try
			{
				//结果集使用完毕，关闭数据库操作的连接对象
				myDB.disConncted();
			}
			catch(Exception sqlEx)
			{
				sqlEx.printStackTrace();
			}
		}
		System.out.println("OrderDAO.java | getDidOrder | 订单号订单MAP:" + order);
		return order;
	}

	@Override
	public Set<Carts> getCartByOrder(Orders order) {
		IDatabaseDAO myDB =new DatabaseDAO();
		
		Carts cart;
		Set<Carts> cartSet = new HashSet<>();
		
		String sql = "SELECT * FROM carts where uid = '"+order.getUid()+"' AND paytime = '"+order.getCreatetime()+"' and isbuy = 1";
		
		try
		{
			System.out.println("OrderDAO.java | getCartByOrder | sql: " + sql);
			ResultSet mySet = myDB.getResultSet(sql);
			while(mySet.next())
			{
				cart = new Carts();
				
				cart.setCid(mySet.getString("cid"));
				cart.setUid(mySet.getString("uid"));
				cart.setMid(mySet.getString("mid"));
				cart.setSpid(mySet.getString("spid"));
				cart.setMname(mySet.getString("mname"));
				cart.setMprice(mySet.getLong("mprice"));
				cart.setCnum(mySet.getInt("cnum"));
				cart.setPaytime(mySet.getTimestamp("paytime"));
				cart.setIsbuyed(mySet.getByte("isbuy"));
				
				cartSet.add(cart);
			}
		}
		//处理异常
		catch(SQLException sqlEX)
		{
			sqlEX.printStackTrace();
		}
		catch(ClassNotFoundException cnfEX)
		{
			cnfEX.printStackTrace();
		}
		finally
		{
			try
			{
				myDB.disConncted();
			}
			catch(SQLException sqlEx)
			{
				sqlEx.printStackTrace();
			}
		}
		return cartSet;
	}
}
