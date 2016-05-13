package service.imp;

import dao.IGoodsDAO;
import dao.IMerchantDAO;
import dao.imp.GoodsDAO;
import dao.imp.MerchantDAO;
import domain.Goods;
import domain.Merchants;
import service.IGoodsInfoService;

public class GoodsInfoService implements IGoodsInfoService
{
	IGoodsDAO goodsDao = new GoodsDAO();

	@Override
	public Goods getGoodsInfoById(String mid) 
	{
		System.out.println("GoodsInfoService : getGoodsInfoById");
		return goodsDao.getGoodsByID(mid);
	}

	@Override
	public Merchants getMerchant(String mspid) 
	{
		// TODO Auto-generated method stub
		IMerchantDAO mDao = new MerchantDAO();
		return mDao.getMerchantsByID(mspid);
		
	}
}
