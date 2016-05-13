package service;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Set;

import domain.Carts;

public interface ICartService 
{
	public void addToCart(Carts cart);
	
	/**
	 * FUN : 返回Carts 中uid 为当前user.uid 的商品的商户spid集合
	 * @return
	 */
	public Set<String> getCartSP(String uid);
	
	/**
	 * FUN : 查看购物车中是否存在该商品
	 */
	public int IsExist(String mid, String spid , String uid);
	
	/**
	 * FUN : 返回购物车中用户id为Uid的商品中以商户spname为key的所有集合。
	 * @param uid 
	 * @return
	 */
	public Map<String, Set<Carts>> getCartBySP(String uid);
	
	/**
	 * FUN : 返回Carts 中uid 为当前user.uid 的商品的商户spname集合
	 * @return
	 */
	public Set<String> getCartsSpname(String uid);

	/**
	 * FUN : 更新uid和mid唯一确定的购物车中的商品数量
	 * @param uid
	 * @param mid
	 * @param num
	 */
	public void updateCarts(String uid, String mid, int num);

	/**
	 * FUN : 删除uid和mid唯一确定的购物车中的商品
	 * @param uid
	 * @param mid
	 */
	public void removeCarts(String uid, String mid);

	/**
	 * 提交订单时更新购物车时间和isbuy字段
	 * @param uid
	 * @param mid
	 * @param time
	 */
	public void updateCarts(String uid, String mid, Timestamp time, int isbuy);
	
	/**
	 * 生成购物车物品单号
	 * @param uid
	 * @return
	 */
	public String createDid(String spid);

	/**
	 * FUN : 在（订单）中通过cid删除购物车中的（已购）物品
	 * @param cid
	 */
	public void removeCartsByCid(String cid);

	/**
	 * FUN : 根据cid更新cart的paytime和isbuy状态。
	 * @param cid
	 * @param time
	 * @param i
	 */
	public void updateCartsByCid(String cid, Timestamp time, int i);
}
