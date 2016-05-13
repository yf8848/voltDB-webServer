package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ICartDAO;
import dao.IGoodsDAO;
import dao.IMerchantDAO;
import dao.imp.CartDAO;
import dao.imp.GoodsDAO;
import dao.imp.MerchantDAO;
import domain.Carts;
import domain.Goods;
import domain.Orders;
import domain.Users;
import service.ICartService;
import service.IGoodsInfoService;
import service.IOrderService;
import service.imp.CartService;
import service.imp.GoodsInfoService;
import service.imp.OrderService;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private Map<String, Set<Carts>> morder;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method");
		
		if("suborder".equals(method))
		{
			subOrder(request, response);
		}
		else if("createorder".equals(method))
		{
			createOrder(request, response);
		}
		else if("payorder".equals(method))
		{
			payOrder(request, response);
		}
		else if("repay".equals(method))
		{
			rePay(request, response);
		}
		else if("oneKeyBuy".equals(method))
		{
			oneKeyBuy(request, response);
		}
		else if("showOrder".equals(method))
		{
			showOrder(request, response);
		}
		else if("remove".equals(method))
		{
			remove(request, response);
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void subOrder(HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException, IOException
	{
		//request.setCharacterEncoding("UTF-8");
		String spname = request.getParameter("spname");
		String smid = request.getParameter("smid");
		int msum = Integer.valueOf(request.getParameter("mnum"));
		String uid = request.getParameter("uid");
		System.out.println(" 总价格 : " + msum+"\n商户集合 ："+spname +"\n商品集合 ： "+smid + "\nUID : "+uid);
		
		String[] sname = spname.trim().split(" ");
		String[] mids= smid.trim().split(" ");
		Set<Carts> scart = null ;
		morder = new HashMap<>();
		Carts cart;
		ICartDAO cartDao = new CartDAO();
		IMerchantDAO merDao = new MerchantDAO();
		try
		{
			for(int i=0;i<sname.length; ++i)
			{	
				scart = new HashSet<>();
				for(int j=0; j<mids.length;++j)
				{
					System.out.println("OrderServlet | 商品 mid ： "+mids[j]);
					String spid = mids[j].substring(0, 10);
					String name = merDao.getNameByID(spid);
					
					if(name.equals(sname[i]))
					{
						cart = cartDao.getCartsByID(uid,mids[j]);
						
						scart.add(cart);
					}	
				}
				if(scart.size()>0)
				{
					morder.put(sname[i], scart);
				}
			}
			
	
			System.out.println("OrderServlet.java | subOrder | morder :" + morder);
			Set<String> setSpname = morder.keySet();
			request.setAttribute("spname",setSpname);
			request.setAttribute("morder", morder);
			
			request.getRequestDispatcher("/suborder.jsp").forward(request, response);
		}
		catch (ServletException e) 
		{
			e.printStackTrace();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	
	}
	
	/**
	 * FUN : 1. 生成订单 did(12<uid> + 8<time> + 6 <rand> )
	 * 		 2. 创建订单，填充相应的收货信息等
	 * 		 3. 因为下一步是确认支付，所以这一步将刚创建的订单状态 dstate 置为1（待支付）
	 * 		 4. 将订单更新到数据库。
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void createOrder(HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException, IOException
	{
		//request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Users user = (Users)session.getAttribute("user");
		if(user == null)
		{
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		System.out.println("OrderServlet.java | createOrder | user: "+ user.getUname());
		
		// 1.获取参数
		String recName = request.getParameter("recName");
		String recPhone = request.getParameter("recPhone");
		String recAddr = request.getParameter("recAddress");
		String recPcode = request.getParameter("passCode");
		String totalmon =request.getParameter("mnum");
		
		System.out.println("totalNum : " + totalmon);
		
		// 2.添加订单信息
		IOrderService orderServ = new OrderService();
		ICartService cartServ = new CartService();
		Orders order = new Orders();
		
		//带小数点的string 可以转化为 double，将double乘100转化为long，存入数据库
		double dd = Double.parseDouble(totalmon);
		long tmon = (long) (dd*100);
		
		order.setDid(orderServ.createDid(user.getUid()));
		order.setRecname(recName);
		order.setRecpthone(recPhone);
		order.setRecaddr(recAddr);
		order.setPassCode(recPcode);
		order.setUid(user.getUid());
		order.setDprice(tmon);
		order.setDstate(1);
		//获取当前时间，作为 order的createtime 和 carts的paytime，
		Timestamp time = orderServ.getTime();

		order.setCreatetime(time);
		
		System.out.println("OrderServlet.java | createOrder | morder集合的 size:" + morder.size());
		Iterator<String> spit = morder.keySet().iterator();
		Iterator<Carts> cartit;
		Set<Carts> sCart ;
		Carts cart;
		//更新到数据库
		try
		{
			while(spit.hasNext())
			{
				String key = spit.next().trim();
				System.out.println("OrderServlet.java | createOrder | morder中的KEY ： " + key);
				sCart = morder.get(key);
				System.out.println("OrderServlet.java | createOrder | 商户对应商品集合的 size:" + sCart.size());
				cartit = sCart.iterator();
				while(cartit.hasNext())
				{
					cart = (Carts) cartit.next();
					cartServ.updateCartsByCid(cart.getCid(), time, 1);
					System.out.println("OrderServlet.java | createOrder | cart : " + cart);
				}
			}
			
			System.out.println("OrderServlet.java | createOrder | order :" + order);
		
			orderServ.addToOrder(order);
			
			request.setAttribute("order",order);
			request.getRequestDispatcher("/orderinfo.jsp").forward(request, response);
		}
		catch (ServletException e) 
		{
			e.printStackTrace();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
		
	private void payOrder(HttpServletRequest request, HttpServletResponse response) 
				 throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		Users user = (Users)session.getAttribute("user");
		if(user == null)
		{
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		String paywd = request.getParameter("payword");
		String did = request.getParameter("did");
		IOrderService orderServ = new OrderService();
		try
		{
			//检验支付密码是否正确
			if(user.getUpasswd().equals(paywd))
			{
				// 更改订单状态为 2，支付成功
				orderServ.updateSuccess(did);
				String mess = "恭喜你，支付成功 !";
				String name = "success";
				request.setAttribute("message", mess);
				request.setAttribute("name", name);
				request.getRequestDispatcher("/payResult.jsp").forward(request, response);
			}
			else
			{
				System.out.println("走付款失败流程");
				// 更改订单状态为 3，支付失败
				orderServ.updateFail(did);
				String mess = "似乎出现问题了 !";
				String name = "fail";
				request.setAttribute("message", mess);
				request.setAttribute("did", did);
				request.setAttribute("name", name);
				System.out.println("走付款失败流程中。。。");
				request.getRequestDispatcher("/payResult.jsp").forward(request, response);
				System.out.println("走付款失败流程结束/////////////");
			}
		}
		catch (ServletException e) 
		{
			e.printStackTrace();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	private void rePay(HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		Users user = (Users)session.getAttribute("user");
		if(user == null)
		{
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		String did = request.getParameter("did");
		IOrderService orderServ = new OrderService();
		Orders order;
		try
		{
			order = orderServ.getOrderByDid(did);
			request.setAttribute("order",order);
			request.getRequestDispatcher("/orderinfo.jsp").forward(request, response);
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
	

	private void oneKeyBuy(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
	{
		//request.setCharacterEncoding("UTF-8");
		System.out.println("OrderServlet.java | oneKeyBuy ");
		String mid = request.getParameter("mid");
		String spid = mid.substring(0, 10);
		int num = Integer.valueOf(request.getParameter("count"));
		String uid = request.getParameter("uid");
		ICartService cartServ = new CartService();
		//IGoodsInfoService goodsInfoService = new GoodsInfoService();
		IGoodsDAO gooddao =new GoodsDAO();
		
			/*
			 * FUN ： 由于carts表中保存着历史订单，所以 oneKeyBuy函数完成两个任务
			 * 一、添加商品到购物车
			 * 二、将商户，商品添加到map，传给 subOrder.jsp
			 */
				
			//一、添加商品到购物车
	
			// 1.根据mid获取商品信息
			//Goods goods = goodsInfoService.getGoodsInfoById(mid);
			Goods goods = gooddao.getGoodsByID(mid);
			//String spid = goods.getMspid();
			
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
			//cart.setIsbuyed(1);
			System.out.println("OrderServlet.java | oneKeyBuy : " + cart );

			try
			{
				cartServ.addToCart(cart);
				System.out.println("添加到购物车完成。。。。。");
				
			 // 二、将商户，商品添加到map，传给 subOrder.jsp
			IMerchantDAO merchant = new MerchantDAO();
			String spname = merchant.getNameByID(spid);
			Set<String> setSpname = new HashSet<>();
			setSpname.add(spname);
			Set<Carts> scart = new HashSet<>() ;
			scart.add(cart);
			morder = new HashMap<>();
			morder.put(spname, scart);
	
			System.out.println("OrderServlet.java | oneKeyBuy | order :" + morder);
			System.out.println("添加到MAP完成。。。。。");
			request.setAttribute("spname",setSpname);
			request.setAttribute("morder", morder);
			System.out.println("准备跳转页面。。。。。");
			
			request.getRequestDispatcher("/suborder.jsp").forward(request, response);
			//response.sendRedirect(request.getContextPath() + "/suborder.jsp");
			System.out.println("跳转完成。。。。。");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	protected void showOrder(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("OrderServlet.java | showOrder ");
		
		HttpSession session = request.getSession();
		Users user = (Users)session.getAttribute("user");
		System.out.println("OrderServlet.java | showOrder | user :" + user);
		if(user == null)
		{
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		else
		{
			//为保险起见，只显示cnum>0的项。
			IOrderService orderService = new OrderService();
			Map<String, Set<Carts>> mCarts = null ;
			Map<String, Orders> mapOrder ;
			Set<String> didSet ;
			try
			{
				mCarts = orderService.getOrderMap(user.getUid());
				didSet = mCarts.keySet();
				mapOrder = orderService.getDidOrder(user.getUid());
				
				request.setAttribute("mCarts",mCarts);
				request.setAttribute("didSet", didSet);
				request.setAttribute("mapOrder", mapOrder);
				request.getRequestDispatcher("orders.jsp").forward(request, response);
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

	private void remove(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		// 获取参数
		String cid = request.getParameter("cid");
		
		System.out.println("OrderServlet | remove | user :" + cid );
		//添加到数据库
		ICartService cartService = new CartService();
		
		try
		{
			cartService.removeCartsByCid(cid);	
			showOrder(request, response);
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
