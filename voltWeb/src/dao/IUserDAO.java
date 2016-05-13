package dao;

import java.util.List;
import domain.Users;

public interface IUserDAO
{
	//添加一个商品到数据库中
	public void addUsers(Users user);
		
	//根据商品ID返回商品信息，以Users对象的形式返回
	public Users getUsersByID(String mid);
		
	//根据商品名获取商品信息，以Users对象的形式返回
	public List<Users> getUsersByName(String mname);
	
	//产生uid。
	//public static String createUid();
}
