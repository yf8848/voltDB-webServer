package dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.voltdb.VoltTable;

import dao.ICartDAO;
import dao.IDatabaseDAO;
import domain.Carts;

public class CartDAO implements ICartDAO
{

	public void CartDAO()
	{
		
	}
	
	@Override
	public void addCarts(Carts cart) 
	{
		// TODO Auto-generated method stub
		
		IDatabaseDAO myDB = new DatabaseDAO();
		
		//获取特定格式的时间
		//String format = ("yyyy-MM-dd hh:mm:ss");
		//String myTime = new SimpleDateFormat(format).format(cart.getPaytime());
		Timestamp myTime = cart.getPaytime();
		System.out.println("时间格式 : "+ myTime);
		
		String sql = "INSERT INTO carts(cid,uid, mid, spid,mname, mprice, cnum, isbuy) VALUES('"+cart.getCid()+"' ,'"+cart.getUid()+"' , '"+cart.getMid()+"', '"+cart.getSpid()+"' , '"+cart.getMname()+"', '"+cart.getMprice()+"' , '"+cart.getCnum() +"' , '"+cart.getIsbuyed() +"')";
		try
		{
			System.out.println("CartDAO.java | addCarts | sql : " + sql);
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
	public Carts getCartsByID(String uid, String mid) 
	{
		// TODO Auto-generated method stub
		IDatabaseDAO myDB = new DatabaseDAO();
		Carts cart =new Carts();
		
		String sql = "SELECT * FROM carts where uid = '"+uid+"' and mid = '"+mid+"' AND isbuy=0";
		try
		{
			ResultSet mySet = myDB.getResultSet(sql);
			
			while(mySet.next())
			{
				cart.setCid(mySet.getString("cid"));
				cart.setUid(mySet.getString("uid"));
				cart.setMid(mySet.getString("mid"));
				cart.setSpid(mySet.getString("spid"));
				cart.setMname(mySet.getString("mname"));
				cart.setMprice(mySet.getLong("mprice"));
				cart.setCnum(mySet.getInt("cnum"));
				cart.setPaytime(mySet.getTimestamp("paytime"));
				cart.setIsbuyed(mySet.getByte("isbuy"));
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
		System.out.println("CartDAO.java | getCartsByID | cart : " + cart);
		return cart;
	}
	
	@Override
	public Set<String> getCartsSP(String uid) 
	{
		// TODO Auto-generated method stub
		IDatabaseDAO myDB = new DatabaseDAO();
		Set<String> spidSet = new HashSet<>();
		
		String sql = "SELECT spid FROM carts where uid = '"+uid+"' and cnum > 0 and isbuy = 0 group by spid";
		try
		{
			ResultSet mySet = myDB.getResultSet(sql);
			
			while(mySet.next())
			{
				spidSet.add(mySet.getString("spid"));
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
		return spidSet;
	}
	
	public List<Carts> getCartsForOrder(String uid, Timestamp buytime) 
	{
		// TODO Auto-generated method stub
		IDatabaseDAO myDB = new DatabaseDAO();
		
		Carts cart = new Carts();
		List<Carts> lcarts = new ArrayList<Carts>();
		
		String sql = "SELECT * FROM carts WHERE uid = '"+uid+"' and paytime = '"+buytime+"' and isbuy = 1";
		try 
		{
			ResultSet mySet = myDB.getResultSet(sql);
			while(mySet.next())
			{
				cart.setCid(mySet.getString("cid"));
				cart.setUid(mySet.getString("uid"));
				cart.setMid(mySet.getString("mid"));
				cart.setSpid(mySet.getString("spid"));
				cart.setMname(mySet.getString("mname"));
				cart.setMprice(mySet.getLong("mprice"));
				cart.setCnum(mySet.getInt("cnum"));
				cart.setPaytime(mySet.getTimestamp("paytime"));
				cart.setIsbuyed(mySet.getByte("isbuy"));
				lcarts.add(cart);
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
		System.out.println("carts size = " + lcarts.size());
		return lcarts;
	}

	@Override
	public Set<Carts> getCartsBySpidAndUid(String sp, String uid) {
		// TODO Auto-generated method stub
		IDatabaseDAO myDB = new DatabaseDAO();
		String sql = "SELECT * FROM carts WHERE spid = '"+sp+"' AND uid = '"+uid+"' and isbuy = 0";
		Set<Carts> sCarts = new HashSet<>();
		Carts cart ;
		
		try
		{
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
				
				System.out.println("CartDAO | getCartsBySpid : " + cart);
				sCarts.add(cart);
				System.out.println("CartDAO | getCartsBySpid : " + sCarts);
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
		System.out.println("CartDAO | getCartsBySpid : " + sCarts);
		return sCarts;
	}

	public Set<Carts> getCartsBySpname(String sp) {
		// TODO Auto-generated method stub
		IDatabaseDAO myDB = new DatabaseDAO();
		String sql = "SELECT * FROM carts WHERE spname like '"+sp+"' and isbuy = 0";
		Set<Carts> sCarts = new HashSet<>();
		Carts cart ;
		
		try
		{
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
				
				System.out.println("CartDAO | getCartsBySpname : " + cart);
				sCarts.add(cart);
				System.out.println("CartDAO | getCartsBySpname : " + sCarts);
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
		System.out.println("CartDAO | getCartsBySpid : " + sCarts);
		return sCarts;
	}

	@Override
	public String getSpnameBySpid(String sp) {
		// TODO Auto-generated method stub
		IDatabaseDAO myDB = new DatabaseDAO();
		String sql = "SELECT * FROM merchants where spid = '"+sp+"'";
		String spname = null;
		try
		{
			ResultSet rs = myDB.getResultSet(sql);
			System.out.println("CartDAO | getSpnameBySpid | sql : " + sql);
			if(rs.next())
			{	
				spname = rs.getString("spname");
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
		System.out.println("CartDAO | getSpnameBySpid : spname" + spname);
		
		return spname;
	}

	public int getMCartsNum(String mid, String spid, String uid) 
	{
		IDatabaseDAO myDB = new DatabaseDAO();
		//返回数据库中对应mid 和 spid ，且isbuy=0（为购买的）商品；
		String sql = "SELECT COUNT(*) as num FROM carts where uid='"+uid+"' and spid = '"+spid+"' and mid='"+mid+"' and isbuy = 0";
		int cnum = 0;
		try
		{
			ResultSet rs = myDB.getResultSet(sql);
			if(rs.next())
			{
				cnum = rs.getInt("num");
			}
			if(cnum > 0)
			{
				sql = "SELECT * FROM carts where uid='"+uid+"' and spid = '"+spid+"' and mid='"+mid+"' and isbuy = 0";
				rs = myDB.getResultSet(sql);
				if(rs.next())
				{
					cnum = rs.getInt("cnum");
				}
			}
			else
			{
				cnum = 0;
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
		System.out.println("CartDAO | getSpnameBySpid : " + cnum);
		
		return cnum;
	}

	@Override
	public void updateCart(String uid, String mid, int num) 
	{
		IDatabaseDAO myDB = new DatabaseDAO();
		//返回数据库中对应mid 和 spid ，且isbuy=0（为购买的）商品；
		String sql = "UPDATE carts SET cnum = '"+num+"' where uid='"+uid+"' and mid='"+mid+"' and isbuy = 0";
		try
		{
			System.out.println("CartDAO | updateCart | sql : " + sql);
			myDB.executeSQL(sql);
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
	}

	@Override
	public void removeCart(String uid, String mid)
	{
		IDatabaseDAO myDB = new DatabaseDAO();
		//返回数据库中对应mid 和 spid ，且isbuy=0（为购买的）商品；
		String sql = "DELETE FROM carts where uid='"+uid+"' and mid='"+mid+"' and isbuy = 0";
		try
		{
			System.out.println("CartDAO | updateCart | sql : " + sql);
			myDB.executeSQL(sql);
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
	}

	@Override
	public void updateCartTimeAndState(String uid, String mid, Timestamp time, int isbuy) {
		// TODO Auto-generated method stub
		IDatabaseDAO myDB = new DatabaseDAO();
		//返回数据库中对应mid 和 spid ，且isbuy=0（为购买的）商品；
		String sql = "UPDATE carts SET paytime = '"+time+"', isbuy = '"+ isbuy+"' where uid='"+uid+"' and mid='"+mid+"' and isbuy = 0";
		try
		{
			System.out.println("CartDAO | updateCartTimeAndState | sql : " + sql);
			myDB.executeSQL(sql);
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
	}

	@Override
	public void removeCartByCid(String cid) 
	{
		IDatabaseDAO myDB = new DatabaseDAO();
		//返回数据库中对应mid 和 spid ，且isbuy=0（为购买的）商品；
		String sql = "DELETE FROM carts where cid='"+cid+"'";
		try
		{
			System.out.println("CartDAO | removeCartByCid | sql : " + sql);
			myDB.executeSQL(sql);
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
	}

	@Override
	public void updateCartTimeAndStateByCid(String cid, Timestamp time, int i) 
	{
		// TODO Auto-generated method stub
		IDatabaseDAO myDB = new DatabaseDAO();
		//返回数据库中对应mid 和 spid ，且isbuy=0（为购买的）商品；
		String sql = "UPDATE carts SET paytime = '"+time+"', isbuy = '"+i+"' where cid='"+cid+"' and isbuy = 0";
		try
		{
			System.out.println("CartDAO | updateCartTimeAndStateByCid | sql : " + sql);
			myDB.executeSQL(sql);
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
	}
	
}
