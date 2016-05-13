package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Carts;
import domain.Goods;
import domain.Users;
import service.ICartService;
import service.imp.CartService;
import service.imp.GoodsInfoService;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		String method = request.getParameter("method");
		System.out.println("CartServlet | doGet | 直接获取数量 ： "+ request.getParameter("num"));
		
		if("addCart".equals(method))
		{
			addToCart(request, response);
		}
		else if("showCart".equals(method))
		{
			showAllCart(request, response);
		}
		else if("update".equals(method))
		{
			update(request, response);
		}
		else if("remove".equals(method))
		{
			remove(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void addToCart(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("CartServlet.java | addToCart ");
		String mid = request.getParameter("mid");
		int num = Integer.valueOf(request.getParameter("count"));
		String uid = request.getParameter("uid");
		ICartService cartServ = new CartService();
		// 1.根据mid获取商品信息
		GoodsInfoService goodsInfoService = new GoodsInfoService();
		Goods goods = goodsInfoService.getGoodsInfoById(mid);
		
		String spid = goods.getMspid();
		// 1.判断是购物车中否存在该商品,还要查看 cnum 是否 大于 0,isbuy == 0 ?；
		int snum = cartServ.IsExist(mid, spid, uid);
		
		
		//2.存在则添加数量，不存在则添加
		if(snum > 0)
		{
			cartServ.updateCarts(uid,mid,snum+num);
		}
		else if(snum == 0)
		{
			Carts cart = new Carts();
			cart.setCid(cartServ.createDid(spid));
			cart.setUid(uid);
			cart.setMid(mid);
			cart.setMname(goods.getMname());
			cart.setMprice(goods.getMprice());
			cart.setIsbuyed(0);
			//cart.setPaytime(null);
			cart.setSpid(spid);
			cart.setCnum(num);
			System.out.println("CartServlet.java | addToCart : " + cart );
			cartServ.addToCart(cart);
		}
	
		request.setAttribute("goodsMid", goods.getMid());
		request.setAttribute("goodsName", goods.getMname());
		request.setAttribute("goodsAuthor", goods.getMauthor());
		request.setAttribute("goodsPress", goods.getMpress());
		request.setAttribute("goodsPubTime", goods.getMpubtime());
		request.setAttribute("goodsPrice", goods.getMprice());
		
		request.getRequestDispatcher("goodsInfo.jsp?").forward(request, response);
	}
	
	protected void showAllCart(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("CartServlet.java | showAllCart ");
		
		HttpSession session = request.getSession();
		Users user = (Users)session.getAttribute("user");
		System.out.println("CartServlet | showAllCart | user :" + user);
		if(user == null)
		{
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		else
		{
			//为保险起见，只显示cnum>0的项。
			ICartService cartService = new CartService();
			Map<String, Set<Carts>> mCarts = new HashMap<>();
			Set<String> sspname = new HashSet<>();
			try
			{
				mCarts = cartService.getCartBySP(user.getUid());
				sspname = cartService.getCartsSpname(user.getUid());
				request.setAttribute("mCarts",mCarts);
				request.setAttribute("sspname", sspname);
				request.getRequestDispatcher("cart.jsp").forward(request, response);
			}
			catch (ServletException e) 
			{
				e.printStackTrace();
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		System.out.println("CartServlet.java | showAllCart : " + mCarts );
		}
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		// 获取参数
		String uid = request.getParameter("uid");
		String mid = request.getParameter("mid");
		int num = Integer.valueOf(request.getParameter("count"));
		
		System.out.println("CartServlet | showAllCart | user :" + uid + " | " + mid + " | " + num);
		//添加到数据库
		ICartService cartService = new CartService();
		
		try
		{
			if(num > 0)	//大于0，update
			{
					//已经在购物车的，肯定是已经存在与数据库中的。所以直接根据 uid 和 mid 更新 cnum 即可。
					cartService.updateCarts(uid, mid, num);
			}
			else	//小于等于0，直接删除该记录
			{
				cartService.removeCarts(uid, mid);
			}
			//request.getRequestDispatcher("cart.jsp").forward(request, response);
			showAllCart(request, response);
		}
		catch (ServletException e) 
		{
				e.printStackTrace();
		} 
		catch (Exception e) 
		{
				e.printStackTrace();
		}
	}
		
	
	private void remove(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		// 获取参数
		String uid = request.getParameter("uid");
		String mid = request.getParameter("mid");
		
		System.out.println("CartServlet | showAllCart | user :" + uid + " | " + mid );
		//添加到数据库
		ICartService cartService = new CartService();
		
		try
		{
			cartService.removeCarts(uid, mid);	
			
			showAllCart(request, response);
			//request.getRequestDispatcher("cart.jsp").forward(request, response);
		}
		catch (ServletException e) 
		{
				e.printStackTrace();
		} 
		catch (Exception e) 
		{
				e.printStackTrace();
		}
	}
	
}
