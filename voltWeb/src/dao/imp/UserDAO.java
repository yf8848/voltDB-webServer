package dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.IDatabaseDAO;
import dao.IUserDAO;
import domain.Users;

public class UserDAO implements IUserDAO
{
	
	/**
	 * 默认构造函数
	 */
	public UserDAO()
	{
		
	}
	
	/**
	 * FUN : 网数据库添加用户
	 */
	@Override
	public void addUsers(domain.Users user)
	{
		// TODO Auto-generated method stub
		System.out.println("UserDAO : 添加用户。。。");
		//初始化数据库访问
		IDatabaseDAO myDB = new DatabaseDAO();
		user.setUid(createUid());
		System.out.println(user.getUid()+user.getUname()+user.getUpasswd()+user.getUphone()+user.getUmail()+user.getAge()+user.getUsex()+user.getUamt()+user.getUpayd());
		//构造SQL
		String sql = "INSERT INTO users(uid, upasswd, uname, usex, uphone, umail, uamt, upayed, uage) VALUES"
				+ "('"+user.getUid()+"','"+user.getUpasswd()+"', '"+user.getUname()+"', '"+user.getUsex()+"', '"+user.getUphone()+"','"+user.getUmail()+"', '"+user.getUamt()+"', '"+user.getUpayd()+"', '"+user.getAge()+"')";
		
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
	 * FUN : 从数据库ID为参数uid的用户
	 * @param uid
	 * @return 返回查询所得的用户
	 */
	@Override
	public domain.Users getUsersByID(String uid) {
		// TODO Auto-generated method stub
System.out.println("UserDAO : getUserByID()");
		//初始化数据库操作类
		IDatabaseDAO myDB = new DatabaseDAO();
		
		//初始化一个USER 对象类，作为返回对象
		domain.Users user = new domain.Users();
		
		//初始化SQL语句
		String sql = "SELECT * FROM users WHERE uid = '"+uid+"'";
		
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
				user.setUid(mySet.getString("uid"));
				user.setUpasswd(mySet.getString("upasswd"));
				user.setUname(mySet.getString("uname"));
				user.setUsex(mySet.getString("usex"));
				user.setAge(mySet.getInt("uage"));
				user.setUphone(mySet.getString("upthone"));
				user.setUmail(mySet.getString("umail"));
				user.setUpayd(mySet.getLong("upayd"));
				user.setUamt(mySet.getLong("uamt"));
				
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
		return user;
	}

	/**
	 * 根据userName查询数据库中的用户信息，
	 */
	@Override
	public List<domain.Users> getUsersByName(String mname) {
		// TODO Auto-generated method stub
System.out.println("UserDAO : getUsersByName()");
		IDatabaseDAO myDB = new DatabaseDAO();
		Users user = new Users();
		List<Users> lusers = new ArrayList<Users>();
		String sql = "SELECT * FROM users where uname = '"+mname+"'";
		
		try
		{
			ResultSet mySet = myDB.getResultSet(sql);
			while(mySet.next())
			{
				/**
				 * 为各个字段赋值
				 */
				user.setUid(mySet.getString("uid"));
				user.setUpasswd(mySet.getString("upasswd"));
				user.setUname(mySet.getString("uname"));
				user.setUsex(mySet.getString("usex"));
				user.setAge(mySet.getInt("uage"));
				user.setUphone(mySet.getString("uphone"));
				user.setUmail(mySet.getString("umail"));
				user.setUpayd(mySet.getLong("upayed"));
				user.setUamt(mySet.getLong("uamt"));
				
				lusers.add(user);
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
		System.out.println(lusers.size());
		return lusers;
	}

	/**
	 * FUN : 查询数据库，产生uid。从100000000000开始
	 * @throws ClassNotFoundException 
	 */
	public String createUid()
	{
	System.out.println("UserDAO : createUid()");
		String uid = "100000000000";
		IDatabaseDAO myDB = new DatabaseDAO();
		String sql = "select * from users order by uid desc";
		try
		{
			ResultSet lusers = myDB.getResultSet(sql); 
			if(lusers.next())
			{
				uid = lusers.getString("uid");
				long in = Long.valueOf(uid)+1;
			System.out.println("uid = "+in);
				uid = String.valueOf(in);
			System.out.println("uid = "+in);	
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return uid;
	}
	
}
