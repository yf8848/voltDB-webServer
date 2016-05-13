package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Users;
import service.IUserService;
import service.imp.UserService;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		String method = request.getParameter("method");
		System.out.println("UserServlet | doGet | method : " + method);
		if("login".equals(method))
		{
			login(request, response);
		}
		else if("logout".equals(method))
		{
			logout(request, response);
		}
		else if("register".equals(method))
		{
			register(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		// 1. 获取用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// 2. 根据用户名和密码返回 user
		IUserService userServ = new UserService();
		Users user = userServ.validateUser(username, password);
		
		// 3. user 不为空则登录
		try
		{
			if(user != null)
			{
				request.getSession().invalidate();
				request.getSession().setAttribute("user", user);
				response.sendRedirect(request.getContextPath() + "/main2.jsp");
				return;
			} 
			else 
			{
				request.setAttribute("login.message", "用户名和密码不匹配");
				request.getRequestDispatcher("/login.jsp").forward(request,response);
				return;
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("login.message", e.getMessage());
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
	}
	
	
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		request.getSession().invalidate(); 

		Cookie cookie = new Cookie("autologin", "");
		cookie.setMaxAge(0);
		cookie.setPath("/");

		response.addCookie(cookie);

		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}

	public void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
	    //获取用户名，"userName"和register.jsp中name属性为"userName"的文本框对应
		String userName=request.getParameter("userName");

	    //获取密码，"password"和register.jsp中name属性为"password"的文本框对应
		String password = request.getParameter("password");
	    
	    String uphone = request.getParameter("uphone");
	    String umail= request.getParameter("umail");
	    
	    //获取性别，"gender"和register.jsp中name属性为"gender"的文本框对应
		String gender = request.getParameter("gender");
	    
	    //获取年龄，"age"和register.jsp中name属性为"age"的文本框对应
		Integer age = Integer.valueOf(request.getParameter("age"));
	    
		Users user = new Users();
		
		//设置用户类对象
		user.setUname(userName);
		user.setUpasswd(password);
		user.setUphone(uphone);
		user.setUmail(umail);
		user.setUsex(gender);
		user.setAge(age);
		
		IUserService userServ = new UserService();
		System.out.println(user.getUid() + user.getUname() + user.getUphone() + user.getUmail()+user.getUsex()+user.getAge());
		try
		{
			//调用业务逻辑层的方法完成注册功能
			userServ.addUser(user);

	        // 注册成功，跳转到登录界面
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}
		//处理异常
		catch(Exception ex)
		{
			System.out.println("注册异常");
			ex.printStackTrace();
				
			//注册失败，回到原界面 
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
	}
}
