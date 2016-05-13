package dao;

import java.util.List;
import java.util.Set;

import domain.Goods;

/**
 * Goods 类对应的DAO借口，提供一些与商品相关的CRUD操作的公开访问方法
 * @author foogle
 *
 */

public interface IGoodsDAO 
{
	//添加一个商品到数据库中
	public void addGoods(Goods goods);
	
	//根据商品ID返回商品信息，以Goods对象的形式返回
	public Goods getGoodsByID(String mid);
	
	//根据商品名获取商品信息，以Goods对象的形式返回
	public Set<Goods> getGoodsByName(String mname);
	
	public Set<Goods> getAllGoods();
	
	public Set<Goods> getGoodsBySName(String sname);
	
	public Set<Goods> getGoodsByKinds(String kname);

}
