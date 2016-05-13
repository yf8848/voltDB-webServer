package service;

import domain.Goods;
import domain.Merchants;

public interface IGoodsInfoService 
{
	public Goods getGoodsInfoById(String mid);

	public Merchants getMerchant(String mspid);
	
	
}
