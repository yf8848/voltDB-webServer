package service.imp;

import java.util.List;

import dao.IUserDAO;
import dao.imp.UserDAO;
import domain.Users;
import service.IUserService;

public class UserService implements IUserService
{
	private IUserDAO userDao = new UserDAO();
	
	public UserService()
	{}

	@Override
	public void addUser(Users user) {
		// TODO Auto-generated method stub
	System.out.println("UserService ： 添加用户");
	System.out.println(user.getUid() + user.getUname() + user.getUphone() + user.getUmail()+user.getUsex()+user.getAge());
		if(user == null)
		{
			System.out.println("user == null" + user);
			return;
		}
		if(user.getUid() != null)
		{
			System.out.println("user.uid == null" + user.getUid());
			return;
		}
		String userName = user.getUname();
		List<Users> users = userDao.getUsersByName(userName);
		
		if(users.size() <= 0)
		{
			System.out.println("UserService ： 添加user");
			userDao.addUsers(user);
		}
		else
		{
			System.out.println("UserService ： 添加异常");
			throw new RuntimeException("该用户已存在，请更换用户名");
		}
		
	}

	@Override
	public Users getUserByID(String id) {
		System.out.println("UserService ： getUserByID");
		// TODO Auto-generated method stub
		if(id == null)
			return null;
		return userDao.getUsersByID(id);
	
	}

	@Override
	public Users validateUser(String userName, String passwd) {
		System.out.println("UserService ：validateUser");
		// TODO Auto-generated method stub
		List<Users> users = userDao.getUsersByName(userName);
		if(users != null && users.size()!= 0)
		{
			if(users.get(0).getUpasswd().equals(passwd))
			{
				//如果用户名和密码合法，返回完整信息，否则返回null.
				System.out.println("UserService ：validateUser ： 用户名，密码匹配，返回");
				return users.get(0);
			}
			else 
			{
				return null; 
			}
		}
		else
		{	
			return null;
		}
	}

	/**
	 * FUN : 根据用户名查找用户是否存在
	 */
	@Override
	public boolean isUserExist(String userName) {
		System.out.println("UserService ： isUserExist");
		// TODO Auto-generated method stub
		List<Users> users = userDao.getUsersByName(userName);
		if(users.size() != 0 && users != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
