package dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.IDatabaseDAO;
import dao.IGoodsDAO;
import domain.Goods;

public class GoodsDAO implements IGoodsDAO
{

	/**
	 * 默认构造函数
	 */
	public GoodsDAO()
	{
		
	}
	
	/**
	 * FUN : 向数据库添加商品
	 */
	public void addGoods(Goods goods)
	{
		// TODO Auto-generated method stub
		//初始化数据库访问
		IDatabaseDAO myDB = new DatabaseDAO();
		
		//获取特定格式的时间
		//String format = ("yyyy-MM-dd hh:mm:ss");
		//String myTime = new SimpleDateFormat(format).format(goods.getMpubtime());
		//System.out.println("时间格式 : "+ myTime);
		//构造SQL
		String sql = "INSERT INTO goods VALUES('"+goods.getMid()+"', '"+goods.getMname()+"', '"+goods.getMspid()+"', '"+goods.getMprice()+"','"+goods.getSaled()+"', '"+ goods.getMmount()+"', '"+ goods.getMpress()+"', '"+goods.getMauthor()+"', '"+goods.getMpath()+"', '"+ goods.getMtag()+"')";

		try
		{
			//执行SQL语句
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

	/**
	 * FUN : 从数据库ID为参数mid的用户
	 * @param mid
	 * @return 返回查询所得的商品
	 */
	public Goods getGoodsByID(String mid) {
		// TODO Auto-generated method stub
		//初始化数据库操作类
		IDatabaseDAO myDB = new DatabaseDAO();
		
		//初始化一个Goods 对象类，作为返回对象
		Goods goods = new Goods();
		
		//初始化SQL语句
		String sql = "SELECT * FROM goods WHERE mid = '"+mid+"'";

	
		try
		{
			//执行SQL语句，获取查询结果
			ResultSet mySet = myDB.getResultSet(sql);
			
			//循环结果集，并对Users对象赋值
			while(mySet.next())
			{
				/**
				 * 为各个字段赋值
				 */
				goods.setMid(mySet.getString("mid"));
				goods.setMname(mySet.getString("mname"));
				goods.setMauthor(mySet.getString("mauthor"));
				goods.setMspid(mySet.getString("mspid"));
				goods.setMmount(mySet.getInt("mmount"));
				goods.setSaled(mySet.getInt("msaled"));
				goods.setMpath(mySet.getString("mpath"));
				goods.setMpress(mySet.getString("mpress"));
				goods.setMprice(mySet.getLong("mprice"));
				goods.setMpubtime(mySet.getTimestamp("mpubtime"));
				goods.setMtag(mySet.getString("mtag"));
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
		//返回用户对象
		return goods;
	}

	/**
	 * 根据userName查询数据库中的商品信息，
	 */
	public Set<Goods> getGoodsByName(String mname) {
		// TODO Auto-generated method stub
		
		mname = mname.toLowerCase();
		IDatabaseDAO myDB = new DatabaseDAO();
		Goods goods = new Goods();
		Set<Goods> lgoods = new HashSet<Goods>();
		String sql = "SELECT * FROM goods where LOWER(mname) = '"+mname+"'";
		
		try
		{
			ResultSet mySet = myDB.getResultSet(sql);
			while(mySet.next())
			{
				/**
				 * 为各个字段赋值
				 */
				/**
				 * 为各个字段赋值
				 */
				goods.setMid(mySet.getString("mid"));
				goods.setMname(mySet.getString("mname"));
				goods.setMauthor(mySet.getString("mauthor"));
				goods.setMspid(mySet.getString("mspid"));
				goods.setMmount(mySet.getInt("mmount"));
				goods.setSaled(mySet.getInt("msaled"));
				goods.setMpath(mySet.getString("mpath"));
				goods.setMpress(mySet.getString("mpress"));
				goods.setMprice(mySet.getLong("mprice"));
				goods.setMpubtime(mySet.getTimestamp("mpubtime"));
				goods.setMtag(mySet.getString("mtag"));
				
				lgoods.add(goods);
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
		System.out.println(lgoods.size());
		return lgoods;
	}
	
	/**
	 * 获取全部书籍
	 */
	public Set<Goods> getAllGoods()
	{
		// TODO Auto-generated method stub
		
		IDatabaseDAO myDB = new DatabaseDAO();
		Goods goods = new Goods();
		Set<Goods> lgoods = new HashSet<Goods>();
		String sql = "SELECT * FROM goods";
		
		try
		{
			ResultSet mySet = myDB.getResultSet(sql);
			while(mySet.next())
			{
				/**
				 * 为各个字段赋值
				 */
				/**
				 * 为各个字段赋值
				 */
				goods.setMid(mySet.getString("mid"));
				goods.setMname(mySet.getString("mname"));
				goods.setMauthor(mySet.getString("mauthor"));
				goods.setMspid(mySet.getString("mspid"));
				goods.setMmount(mySet.getInt("mmount"));
				goods.setSaled(mySet.getInt("msaled"));
				goods.setMpath(mySet.getString("mpath"));
				goods.setMpress(mySet.getString("mpress"));
				goods.setMprice(mySet.getLong("mprice"));
				goods.setMpubtime(mySet.getTimestamp("mpubtime"));
				goods.setMtag(mySet.getString("mtag"));
				
				lgoods.add(goods);
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
		System.out.println(lgoods.size());
		return lgoods;
	}
	
	/**
	 * FUN : 根据名称查找
	 */
	public Set<Goods> getGoodsBySName(String sname) {
		// TODO Auto-generated method stub
		System.out.println("GoodsDAO.java : getGoodsBySName  "+ sname);
		sname = sname.toLowerCase();
		System.out.println("GoodsDAO.java : getGoodsBySName  "+ sname);
		IDatabaseDAO myDB = new DatabaseDAO();
		Goods goods ;
		Set<Goods> lgoods = new HashSet<Goods>();
		String sql = "SELECT * FROM goods where LOWER(mname) like '%"+sname+"%' order by mid desc";
		String sql_tag = "SELECT * FROM goods where LOWER(mtag) like '%"+sname+"%' order by mid desc";
		
		try
		{
			ResultSet mySet = myDB.getResultSet(sql_tag);
			System.out.println("myset : " + mySet );
			while(mySet.next())
			{
				/**
				 * 为各个字段赋值
				 */
				/**
				 * 为各个字段赋值
				 */
				goods = new Goods();
				goods.setMid(mySet.getString("mid"));
				goods.setMname(mySet.getString("mname"));
				goods.setMauthor(mySet.getString("mauthor"));
				goods.setMspid(mySet.getString("mspid"));
				goods.setMmount(mySet.getInt("mmount"));
				goods.setSaled(mySet.getInt("msaled"));
				goods.setMpath(mySet.getString("mpath"));
				goods.setMpress(mySet.getString("mpress"));
				goods.setMprice(mySet.getLong("mprice"));
				goods.setMpubtime(mySet.getTimestamp("mpubtime"));
				goods.setMtag(mySet.getString("mtag"));
				
				lgoods.add(goods);
				System.out.println("GoodsDao.java " + goods);
			}

			ResultSet nameSet = myDB.getResultSet(sql);
			while(nameSet.next())
			{
				goods = new Goods();
				goods.setMid(nameSet.getString("mid"));
				goods.setMname(nameSet.getString("mname"));
				goods.setMauthor(nameSet.getString("mauthor"));
				goods.setMspid(nameSet.getString("mspid"));
				goods.setMmount(nameSet.getInt("mmount"));
				goods.setSaled(nameSet.getInt("msaled"));
				goods.setMpath(nameSet.getString("mpath"));
				goods.setMpress(nameSet.getString("mpress"));
				goods.setMprice(nameSet.getLong("mprice"));
				goods.setMpubtime(nameSet.getTimestamp("mpubtime"));
				goods.setMtag(nameSet.getString("mtag"));
				
				System.out.println(lgoods);
				System.out.println("是否包含 ： " + lgoods.contains(goods));
				if(lgoods.contains(goods))
				{
					continue;
				}
				else
				{
					lgoods.add(goods);
				}
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
		System.out.println("GoodsDAO.java : getGoodsBySName :  lgoogs"+lgoods);
		return lgoods;
	}

	@Override
	public Set<Goods> getGoodsByKinds(String kname) {
		// TODO Auto-generated method stub
		System.out.println("GoodsDAO.java : getGoodsByKinds  "+ kname);
		kname = kname.toLowerCase();
		System.out.println("GoodsDAO.java : getGoodsByKinds  "+ kname);
		IDatabaseDAO myDB = new DatabaseDAO();
		Goods goods;
		Set<Goods> lgoods = new HashSet<Goods>();
		String sql = "SELECT * FROM goods where Lower(mtag) like '%"+kname+"%' order by mid desc";
		
		
		try
		{
			ResultSet mySet = myDB.getResultSet(sql);
			while(mySet.next())
			{
				/**
				 * 为各个字段赋值
				 */
				goods = new Goods();
				goods.setMid(mySet.getString("mid"));
				goods.setMname(mySet.getString("mname"));
				goods.setMauthor(mySet.getString("mauthor"));
				goods.setMspid(mySet.getString("mspid"));
				goods.setMmount(mySet.getInt("mmount"));
				goods.setSaled(mySet.getInt("msaled"));
				goods.setMpath(mySet.getString("mpath"));
				goods.setMpress(mySet.getString("mpress"));
				goods.setMprice(mySet.getLong("mprice"));
				goods.setMpubtime(mySet.getTimestamp("mpubtime"));
				goods.setMtag(mySet.getString("mtag"));
				
				lgoods.add(goods);
				System.out.println("GoodsDao.java " + goods);
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
		System.out.println(lgoods.size());
		System.out.println("GoodsDAO.java : getGoodsByKinds :  lgoogs"+lgoods);
		return lgoods;
	}
	
}
