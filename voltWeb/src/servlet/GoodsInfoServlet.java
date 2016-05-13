package servlet;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Goods;
import domain.Merchants;
import service.IGoodsInfoService;
import service.imp.GoodsInfoService;

/**
 * Servlet implementation class GoodsInfo
 */
@WebServlet("/GoodsInfo")
public class GoodsInfoServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private GoodsInfoService goodsInfoService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsInfoServlet() 
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
		request.setCharacterEncoding("UTF-8");
		
		String mthod = request.getParameter("method");
		if(mthod.equals("getGoodsInfo"))
		{
			getGoodsInfo(request, response);
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
	
	public void getGoodsInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String mid = request.getParameter("good");
		IGoodsInfoService goodsServ = new GoodsInfoService();
		try
		{
			goodsInfoService = new GoodsInfoService();
			Goods goods = goodsInfoService.getGoodsInfoById(mid);
			Merchants merc = goodsServ.getMerchant(goods.getMspid());
			
			request.setAttribute("goodsMid", goods.getMid());
			request.setAttribute("goodsName", goods.getMname());
			request.setAttribute("goodsAuthor", goods.getMauthor());
			request.setAttribute("goodsPress", goods.getMpress());
			request.setAttribute("goodsPubTime", goods.getMpubtime());
			request.setAttribute("goodsPrice", goods.getMprice());
			request.setAttribute("goodsSp", merc.getSpname());
			
			System.out.println("Goods mid : " + goods.getMid() +"|"+ request.getAttribute("goodsMid"));
			request.getRequestDispatcher("goodsInfo.jsp").forward(request, response);
		}
		catch (ServletException e) 
		{
			e.printStackTrace();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	
}
