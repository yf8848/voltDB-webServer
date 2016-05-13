package service.imp;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.management.RuntimeErrorException;

import dao.ICartDAO;
import dao.imp.CartDAO;
import domain.Carts;
import service.ICartService;

public class CartService implements ICartService
{
	
	ICartDAO cartDao = new CartDAO();
	@Override
	public void addToCart(Carts cart)
	{
		System.out.println("CartService.java | addToCart : " + cart);
		cartDao.addCarts(cart);	
	}
	
	/**
	 * FUN ： 返回carts中商户spid集合
	 */
	public Set<String> getCartSP(String uid)
	{
		System.out.println("Cartservice.java | getCartSp : ");
		System.out.println("当前用户UID ："+ uid);
		return cartDao.getCartsSP(uid);
	}
	
	/**
	 * FUN ： 返回carts中商户spname集合
	 */
	public Set<String> getCartsSpname(String uid)
	{
		System.out.println("当前用户UID ："+ uid);
		Set<String> spSet = getCartSP(uid);
		Iterator<String> iterator = spSet.iterator();
		Set<String> spnameSet = new HashSet<String>();
		while(iterator.hasNext())
		{
			String sp = iterator.next();
			String spname = cartDao.getSpnameBySpid(sp);
			System.out.println("CartService | getCartBySP | 商户spid: " + sp + "\tspname : " + spname);
			spnameSet.add(spname);
		}
		return spnameSet;
	}
	public Map<String, Set<Carts>> getCartBySP(String uid)
	{
		System.out.println("CartSercice.java | getCartBySP : ");
		System.out.println("当前用户UID ："+ uid);
		
		Map<String, Set<Carts>> cartMap = new HashMap<String, Set<Carts>>();
		ICartDAO cartDao = new CartDAO();
		Set<Carts> sCart;
		Set<String> sspid = getCartSP(uid);
		Iterator<String> iterator = sspid.iterator();
		while(iterator.hasNext())
		{
			String sp = iterator.next();
			String spname = cartDao.getSpnameBySpid(sp);
			System.out.println("CartService | getCartBySP | 商户spname: " + spname + "\t商户spid ： "+ sp);
			sCart  = cartDao.getCartsBySpidAndUid(sp, uid);
			cartMap.put(spname, sCart);
			
			System.out.println("CartSercice.java | getCartBySP : " + spname + " | " + sCart);
			System.out.println("CartSercice.java | getCartBySP : " + cartMap);
		}
		
		return cartMap;
	}

	@Override
	public int IsExist(String mid, String spid, String uid) 
	{
		// 返回购物车中该商品数量
		ICartDAO cartDao = new CartDAO();
		return cartDao.getMCartsNum(mid, spid,uid);
	}

	@Override
	public void updateCarts(String uid, String mid, int num) 
	{
		ICartDAO cartDao = new CartDAO();
		cartDao.updateCart(uid, mid, num);
	}

	@Override
	public void removeCarts(String uid, String mid) 
	{
		ICartDAO cartDao = new CartDAO();
		cartDao.removeCart(uid, mid);
	}

	@Override
	public void updateCarts(String uid, String mid, Timestamp time, int isbuy) 
	{
		ICartDAO cartDao = new CartDAO();
		cartDao.updateCartTimeAndState(uid, mid, time, isbuy);
		
	}

	public String createDid(String uid) 
	{
		if(uid.length()!=10)
			throw new RuntimeErrorException(null, "SPid格式不正确");
		String did = new String();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = sdf.format(date);
		did = uid+str;
		System.out.println("CartsService.jav | createDid | uid : "+uid + "\tdate : "+str+"\tDid : "+did);
		return did;
	}

	@Override
	public void removeCartsByCid(String cid) {
		// TODO Auto-generated method stub
		ICartDAO cartDao = new CartDAO();
		cartDao.removeCartByCid(cid);
	}

	@Override
	public void updateCartsByCid(String cid, Timestamp time, int i) {
		// TODO Auto-generated method stub
		ICartDAO cartDao = new CartDAO();
		cartDao.updateCartTimeAndStateByCid(cid, time, i);
	}
}
