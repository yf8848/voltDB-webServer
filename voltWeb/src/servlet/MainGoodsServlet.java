package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Goods;
import service.imp.GoodsMainService;

/**
 * Servlet implementation class MainGoods
 */
@WebServlet("/MainGoods")
public class MainGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private GoodsMainService goods;
	
    public MainGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method");
		if("kindsDisplay".equals(method))
		{
			kindsDisplay(request, response);
		}
		else if("searchedDispaly".equals(method))
		{
			searchedDisplay(request, response);
		}
		else if("allDisplay".equals(method))
		{
			allDisplay(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public void kindsDisplay(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		//1.得到商品的分类 kinds
		String kinds = request.getParameter("kinds");
		
		//2. 调用查询方法
		try
		{
			goods = new GoodsMainService();
			Set<Goods> lgoods = goods.getKindsGoods(kinds);
			System.out.println("MainGoodsServlet : kindsDisplay = "+lgoods);
			request.setAttribute("kgoods", lgoods);
			request.setAttribute("kind", kinds);
			request.getRequestDispatcher("kindsGoods.jsp").forward(request, response);
		}
		catch (ServletException e) 
		{
			e.printStackTrace();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void searchedDisplay(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		//1.得到商品的查找字段
				String sname = request.getParameter("sname");
				
				//2. 调用查询方法
				try
				{
					goods = new GoodsMainService();
					Set<Goods> lgoods = goods.getSearchedGoods(sname);
					
					request.setAttribute("sgoods", lgoods);
					request.setAttribute("sname", sname);
					request.getRequestDispatcher("searchedGoods.jsp").forward(request, response);
				}
				catch (ServletException e) 
				{
					e.printStackTrace();
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
	}
	
	public void allDisplay(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{		
			//2. 调用查询方法
			try
			{
				goods = new GoodsMainService();
				Set<Goods> lgoods = goods.getAllGoods();
					
				request.setAttribute("allgoods", lgoods);
				request.getRequestDispatcher("main.jsp").forward(request, response);
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
