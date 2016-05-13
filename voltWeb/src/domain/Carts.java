package domain;

import java.sql.Timestamp;

import org.voltdb.types.TimestampType;

public class Carts 
{
	/**
	 * 默认构造函数
	 */
	public Carts() {
		super();
	}
	
	/**
	 * 带参数构造函数
	 * @param uid
	 * @param mid
	 * @param mname
	 * @param mprice
	 * @param cnum
	 * @param isbuyed
	 * @param paytime
	 */
	public Carts(String uid, String mid, String spid, String mname, long mprice, int cnum, int isbuyed, Timestamp paytime) {
		super();
		this.uid = uid;
		this.mid = mid;
		this.spid = spid;
		this.mname = mname;
		this.mprice = mprice;
		this.cnum = cnum;
		this.isbuyed = isbuyed;
		this.paytime = paytime;
	}

	/**
	 * 购物车中商品对应 ID
	 */
	private String cid;
	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	/**
	 * 购物车所属用户
	 */
	private String uid;

	/**
	 * 商品id
	 */
	private String mid;
	
	/**
	 * 商品所属商户ID
	 */
	private String spid;
	/**
	 * 商品名称
	 */
	private String mname;
	
	/**
	 * 商品价格
	 */
	private long mprice;
	
	/**
	 * 商品数目
	 */
	private int cnum;
	
	/**
	 * 是否购买该商品
	 * 购买过的商品不在购物车在显示
	 */
	private int isbuyed; 
	
	/**
	 * 购买时间，下单的时候更改，和oders表的createtime相同 
	 * 通过uid和该字段来和订单中的商品关联
	 */
	private Timestamp paytime;

	/**
	 * The method of getter and setter
	 * @return
	 */
	public String getUid() {
		return uid;
	}

	public String getSpid() {
		return spid;
	}

	public void setSpid(String spid) {
		this.spid = spid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public long getMprice() {
		return mprice;
	}

	public void setMprice(long mprice) {
		this.mprice = mprice;
	}

	public int getCnum() {
		return cnum;
	}

	public void setCnum(int cnum) {
		this.cnum = cnum;
	}

	public int getIsbuyed() {
		return isbuyed;
	}

	public void setIsbuyed(int isbuyed) {
		this.isbuyed = isbuyed;
	}

	public Timestamp getPaytime() {
		return paytime;
	}

	public void setPaytime(Timestamp timestamp) {
		this.paytime = timestamp;
	}

	@Override
	public String toString() {
		return "Carts [cid=" + cid + ", uid=" + uid + ", mid=" + mid + ", spid=" + spid + ", mname=" + mname
				+ ", mprice=" + mprice + ", cnum=" + cnum + ", isbuyed=" + isbuyed + ", paytime=" + paytime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cid == null) ? 0 : cid.hashCode());
		result = prime * result + ((mid == null) ? 0 : mid.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carts other = (Carts) obj;
		if (cid == null) {
			if (other.cid != null)
				return false;
		} else if (!cid.equals(other.cid))
			return false;
		return true;
	}
	
	
}
