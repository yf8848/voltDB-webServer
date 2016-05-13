package service;

import domain.Users;

public interface IUserService 
{
	public void addUser(Users user);
	
	public Users getUserByID(String id);
	
	public Users validateUser(String userName, String passwd);
	
	public boolean isUserExist(String userName);
	
}
