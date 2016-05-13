package service.imp;

import java.util.List;
import java.util.Set;

import dao.IGoodsDAO;
import dao.imp.GoodsDAO;
import domain.Goods;
import service.IGoodsMainService;


public class GoodsMainService implements IGoodsMainService
{
	
	IGoodsDAO goodsDao = new GoodsDAO();


	@Override
	public Set<Goods> getAllGoods() 
	{
		// TODO Auto-generated method stub
		System.out.println("Goods_main : getAllGoods");
		return goodsDao.getAllGoods();
	}

	@Override
	public Set<Goods> getSearchedGoods(String lname) 
	{
		// TODO Auto-generated method stub
		System.out.println("Goods_main : getSearchedGoods");
		return goodsDao.getGoodsBySName(lname);
	}

	@Override
	public Set<Goods> getKindsGoods(String tagName) 
	{
		// TODO Auto-generated method stub
		System.out.println("Goods_main : getKindsGoods");
		return goodsDao.getGoodsByKinds(tagName);
	}

}
