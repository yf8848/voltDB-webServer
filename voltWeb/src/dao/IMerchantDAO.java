package dao;

import java.util.List;

import domain.Merchants;;

public interface IMerchantDAO 
{
	//添加一个商户到数据库中
	public void addMerchants(Merchants merchant);
		
	//根据商户ID返回商户信息，以Merchants对象的形式返回
	public Merchants getMerchantsByID(String spid);
		
	//根据商品名获取商品信息，以Merchants对象的形式返回
	public List<Merchants> getMerchantsByName(String spname);
	/**
	 * FUN : 根据 spid返回spname
	 * @param spid
	 * @return
	 */
	public String getNameByID(String spid);
}
