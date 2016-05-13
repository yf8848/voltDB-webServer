package dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import domain.Carts;

public interface ICartDAO 
{
	/**
	 * 添加一个商品到数据库中
	 * 如果购物陈中原本不存在，添加，如果存在，数量加1
	 * @param Carts
	 * 
	 */
	public void addCarts(Carts cart);
		
	/**
	 * 品ID返回商品信息，以Carts对象的形式返回
	 * @param mid
	 * @return
	 */
	public Carts getCartsByID(String uid, String mid);
			
	//根据商品名获取商品信息，以Carts对象的形式返回
	public List<Carts> getCartsForOrder(String uid, Timestamp buytime);
	
	/**
	 * FUN : 获得购物车中的商户信息
	 * @return
	 */
	public Set<String> getCartsSP(String uid);
	
	/**
	 * FUN : 通过spid 返回购物车信息。
	 */
	public Set<Carts> getCartsBySpidAndUid(String sp, String uid);
	
	/**
	 * FUN : 通过spnme 返回购物车信息。
	 */
	public Set<Carts> getCartsBySpname(String sp);
	/**
	 * FUN : 返回商户spid对应的spname。
	 */
	public String getSpnameBySpid(String sp);
	
	/**
	 * FUN : 根据mid 和 spid 判断商品是否存在并返回商品数量
	 * 
	 */
	public int getMCartsNum(String mid, String spid, String uid);

	/**
	 * FUN : 更新carts中的商品 cnum
	 * @param uid
	 * @param mid
	 * @param num
	 */
	public void updateCart(String uid, String mid, int num);

	/**
	 * FUN : 删除carts中指定的商品
	 * @param uid
	 * @param mid
	 */
	public void removeCart(String uid, String mid);

	/**
	 * 更新购物车状态和购买时间
	 * @param uid
	 * @param mid
	 * @param time
	 * @param isbuy
	 */
	public void updateCartTimeAndState(String uid, String mid, Timestamp time, int isbuy);

	/**
	 * FUN : 通过cid删除购物车中的物品
	 * @param cid
	 */
	public void removeCartByCid(String cid);

	/**
	 * FUN : 通过 cid 更新cart的paytime和isbuy状态
	 * @param cid
	 * @param time
	 * @param i
	 */
	public void updateCartTimeAndStateByCid(String cid, Timestamp time, int i);
	
	
}
