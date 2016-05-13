package dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.IDatabaseDAO;
import dao.IMerchantDAO;
import domain.Merchants;

public class MerchantDAO implements IMerchantDAO
{

	@Override
	public void addMerchants(Merchants merchant) 
	{
		// TODO Auto-generated method stub
		//初始化数据库访问
				IDatabaseDAO myDB = new DatabaseDAO();
				
				//构造SQL
				String sql = "INSERT INTO merchants VALUES('"+merchant.getSpid()+"','"+merchant.getSppasswd()+"', '"+merchant.getSpname()+"', '"+merchant.getSpamt()+"', '"+merchant.getSprank()+"','"+merchant.getSppthone()+"','"+merchant.getSpaddress()+"')";
				
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

	@Override
	public Merchants getMerchantsByID(String spid) 
	{
		// TODO Auto-generated method stub
		IDatabaseDAO myDB = new DatabaseDAO();
		
		//初始化一个USER 对象类，作为返回对象
		Merchants merc = new Merchants();
		
		//初始化SQL语句
		String sql = "SELECT * FROM merchants WHERE spid = '"+spid+"'";
		
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
				merc.setSpid(mySet.getString("spid"));
				merc.setSppasswd(mySet.getString("sppasswd"));
				merc.setSpname(mySet.getString("spname"));
				merc.setSpamt(mySet.getLong("spamt"));
				merc.setSprank(mySet.getByte("sprank"));
				merc.setSppthone(mySet.getString("sppthone"));
				merc.setSpaddress(mySet.getString("spaddress"));
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
		return merc;
	}

	@Override
	public List<Merchants> getMerchantsByName(String spname) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNameByID(String spid) 
	{
		// TODO Auto-generated method stub
		IDatabaseDAO myDB = new DatabaseDAO();
		
		//构造SQL
		String sql = "SELECT spname from merchants WHERE spid = '"+spid+"'";
		String spname = null;
		try
		{
			System.out.println(sql);
			//执行SQL语句
			ResultSet rs = myDB.getResultSet(sql);
			if(rs.next())
			{
				spname = rs.getString("spname");
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
		return spname;
	}

}
