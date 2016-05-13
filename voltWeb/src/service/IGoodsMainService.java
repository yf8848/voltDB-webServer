package service;

import java.util.List;
import java.util.Set;

import domain.Goods;

public interface IGoodsMainService
{
	/**
	 * FUN : 获取所有物品展示
	 *	
	 */
	public Set<Goods> getAllGoods();
	
	/**
	 * FUN : 按照关键字搜索
	 */
	public Set<Goods> getSearchedGoods(String lname);
	
	/**
	 * Fun : 标签分类
	 */
	public Set<Goods> getKindsGoods(String tagName);
}
