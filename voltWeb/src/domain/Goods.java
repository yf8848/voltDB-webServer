package domain;

import java.sql.Timestamp;

import org.voltdb.*;
import org.voltdb.types.*;

public class Goods 
{
	/**
	 * 默认构造函数
	 */
	public Goods()
	{
		
	}
	
	@Override
	public String toString() {
		return "Goods [mid=" + mid + ", mnane=" + mname + ", mprice=" + mprice + ", mspid=" + mspid + ", msaled="
				+ msaled + ", mmount=" + mmount + ", mpress=" + mpress + ", mauthor=" + mauthor + ", mpubtime="
				+ mpubtime + ", mpath=" + mpath + ", mtag=" + mtag + "]";
	}

	/**
	 * 带参数构造函数
	 * @param mid
	 * @param mnane
	 * @param mprice
	 * @param mspid
	 * @param msaled
	 * @param mmount
	 * @param mpress
	 * @param mauthor
	 * @param mpubtime
	 * @param mpath
	 * @param mtag
	 */
	public Goods(String mid, String mname, long mprice, String mspid, int msaled, int mmount, String mpress,
			String mauthor, Timestamp mpubtime, String mpath, String mtag) 
	{
		super();
		this.mid = mid;
		this.mname = mname;
		this.mprice = mprice;
		this.mspid = mspid;
		this.msaled = msaled;
		this.mmount = mmount;
		this.mpress = mpress;
		this.mauthor = mauthor;
		this.mpubtime = mpubtime;
		this.mpath = mpath;
		this.mtag = mtag;
	}
	/**
	 * 商品ID
	 */
	private String mid;
	/**
	 * 商品名称
	 */
	private String mname;
	/**
	 * 商品价格
	 */
	private long mprice;
	/**
	 * 商品所属商ID
	 */
	private String mspid;
	/**
	 * 已出售商品数量
	 */
	private int msaled;
	/**
	 * 商品余量
	 */
	private int mmount;
	/**
	 * 出版社，制造商
	 */
	private String mpress;
	/**
	 * 作者，生产者
	 */
	private String mauthor;
	/**
	 * 出版（生产）时间
	 */
	private Timestamp mpubtime;
	/**
	 * 图片存储路径
	 */
	private String mpath;
	/**
	 * 标签，用来搜索
	 */
	private String mtag;
	
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mnane) {
		this.mname = mnane;
	}
	public long getMprice() {
		return mprice;
	}
	public void setMprice(long mprice) {
		this.mprice = mprice;
	}
	public String getMspid() {
		return mspid;
	}
	public void setMspid(String mspid) {
		this.mspid = mspid;
	}
	public int getSaled() {
		return msaled;
	}
	public void setSaled(int saled) {
		this.msaled = saled;
	}
	public int getMmount() {
		return mmount;
	}
	public void setMmount(int mmount) {
		this.mmount = mmount;
	}
	public String getMpress() {
		return mpress;
	}
	public void setMpress(String mpress) {
		this.mpress = mpress;
	}
	public String getMauthor() {
		return mauthor;
	}
	public void setMauthor(String mauthor) {
		this.mauthor = mauthor;
	}
	public Timestamp getMpubtime() {
		return mpubtime;
	}
	public void setMpubtime(Timestamp timestamp) {
		this.mpubtime = timestamp;
	}
	public String getMpath() {
		return mpath;
	}
	public void setMpath(String mpath) {
		this.mpath = mpath;
	}
	public String getMtag() {
		return mtag;
	}
	public void setMtag(String mtag) {
		this.mtag = mtag;
	}

	public boolean equals(Object object)
	{
		if (object == null) {  
            return false;  
        }  
        if (object == this) {  
            return true;  
        }  
        if (!(object instanceof Goods)) {  
            return false;  
        }  
        Goods other = (Goods) object;  
        System.out.println(other.getMid()+"\t" +this.getMid());
        if (other.getMid().equals(this.getMid())) {  
            return true;  
        }  
        return false;  
	}
	
	public int hashCode(){  
        int code =  this.getMid().hashCode();
        System.out.println("hashcode ; "+ code);
        return code;
    }
}
