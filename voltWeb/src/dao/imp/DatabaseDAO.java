package dao.imp;

import java.sql.*;

import dao.IDatabaseDAO;

import java.io.*;


public class DatabaseDAO implements IDatabaseDAO
{
	public DatabaseDAO()
	{}
	
	private Connection conn = null;
	
	/**
	 * 获取数据库的连接对象
	 * @return 数据库连接对象
	 * @throws classNotFoundException
	 * @throws SQLException
	 */
	public Connection getConnection() throws ClassNotFoundException, SQLException
	{
		Connection conn = null;
		
		//加载数据库驱动
		String driver = "org.voltdb.jdbc.Driver";
		Class.forName(driver);
		
		//初始化数据库链接字符串
		String url = "jdbc:voltdb://localhost:21212?useUnicode=true&characterEncoding=utf-8";
		
		//链接数据库
		conn = DriverManager.getConnection(url);
		System.out.println("建立链接完成");	
		return conn;
	}
	
	/**
	 * 关闭链接对象
	 * @throws SQLException
	 */
	public void disConncted() throws SQLException
	{
		if(this.conn != null)
		{
			this.conn.close();
			this.conn = null;
		}
		System.out.println("断开数据库连接");
	}
	
	/**
	 * 执行一个SQL查询语句并返回结果集
	 * @param SQL查询语句
	 * @return 数据库查询结果集
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * 
	 */
	public ResultSet getResultSet(String querySQL) throws SQLException, ClassNotFoundException
	{
		System.out.println("DatabaseDAO.java : getResultSet : " +querySQL);
		if(conn == null)
		{
			this.conn = getConnection();
		}
		Statement stm = conn.createStatement();
		
		//执行SQL查询语句并返回查询结果集
		ResultSet RST = stm.executeQuery(querySQL);
		System.out.println("RST_size : "+RST.getFetchSize());
		return RST;
	}
	
	/**
	 * 直接执行一条对数据库修改的SQL语句
	 * @param SQL语句
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void executeSQL(String doSQL) throws SQLException, ClassNotFoundException
	{
		if(conn == null)
		{
			this.conn = getConnection();
		}
		Statement stm = conn.createStatement();
		
		//执行给定的SQL语句
		stm.executeUpdate(doSQL);
		
		//关闭连接对象
		this.conn.close();
		this.conn = null;
		System.out.println("DatabaseDAO executeSQL");
	}
	
	/**
	 *测试主函数
	 *@param args 
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		DatabaseDAO myDB = new DatabaseDAO();
		String sql = "INSERT INTO users(uid, upasswd,uname, uphone, umail) VALUES('100000000002', '123456','fooggle', '18182428522', '1071380275@qq.com')";
		String ssql = "select * from users order by uid desc";
		try
		{
			myDB.conn = myDB.getConnection();
			//myDB.executeSQL(sql);
			ResultSet rs = myDB.getResultSet(ssql);
			if(rs.next())
			{
				String uid = rs.getString("uid");
//				String uu = "100000000002";
//				System.out.println(uid.compareTo(uu));
//			//	System.out.println(rs.getString("uid")+"\t"+rs.getString("uname"));
//				System.out.println(uid);
				long in = Long.valueOf(uid);
				System.out.println("uid = " + in);
			}
			myDB.disConncted();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
