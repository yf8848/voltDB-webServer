package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDatabaseDAO 
{
	public Connection getConnection() throws ClassNotFoundException, SQLException;
	
	public void disConncted() throws SQLException;
	
	public ResultSet getResultSet(String querySQL) throws SQLException, ClassNotFoundException;
	
	public void executeSQL(String doSQL) throws SQLException, ClassNotFoundException;
}
