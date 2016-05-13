package domain;

import java.sql.Timestamp;

import org.voltdb.types.*;
public class Orders 
{
	/**
	 * 默认构造函数
	 */
	public Orders() {
		super();
	}
	
	/**
	 * 带参数构造函数
	 * @param did
	 * @param uid
	 * @param recname
	 * @param recaddr
	 * @param recpthone
	 * @param dprice
	 * @param dstate
	 * @param createtime
	 */
	public Orders(String did, String uid, String recname, String recaddr, String recpthone, long dprice, byte dstate,
			Timestamp createtime) {
		super();
		this.did = did;
		this.uid = uid;
		this.recname = recname;
		this.recaddr = recaddr;
		this.recpthone = recpthone;
		this.dprice = dprice;
		this.dstate = dstate;
		this.createtime = createtime;
	}

	/**
	 * 订单号.
	 * uid(12)+date(8)+addnum(6)
	 */
	private String did;
	
	/**
	 * 订单发起用户ID
	 */
	private String uid;
	
	/**
	 * 收货人姓名
	 */
	private String recname;
	
	/**
	 * 收货地址
	 */
	private String recaddr;
	
	/**
	 * 收货人联系方式
	 */
	private String recpthone;
	
	/**
	 * 订单金额
	 */
	private long dprice;
	
	/**
	 * 订单状态
	 * 0:初始状态
	 * 1:创建成功
	 * 2:支付中
	 * 3:支付成功
	 * 4:支付失败
	 */
	private int dstate;
	
	/**
	 * 订单创建时间
	 */
	private Timestamp createtime;

	/**
	 * 收货邮编
	 */
	private String passCode;
	
	public String getPassCode() {
		return passCode;
	}

	public void setPassCode(String passCode) {
		this.passCode = passCode;
	}

	/**
	 * the method of setter and getter
	 * @return
	 */
	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getRecname() {
		return recname;
	}

	public void setRecname(String recname) {
		this.recname = recname;
	}

	public String getRecaddr() {
		return recaddr;
	}

	public void setRecaddr(String recaddr) {
		this.recaddr = recaddr;
	}

	public String getRecpthone() {
		return recpthone;
	}

	public void setRecpthone(String recpthone) {
		this.recpthone = recpthone;
	}

	public long getDprice() {
		return dprice;
	}

	public void setDprice(long dprice) {
		this.dprice = dprice;
	}

	public int getDstate() {
		return dstate;
	}

	public void setDstate(int i) {
		this.dstate = i;
	}

	@SuppressWarnings("deprecation")
	public Timestamp getCreatetime() {
		createtime.setHours(createtime.getHours()-8);
		return createtime;
	}

	public void setCreatetime(Timestamp timestamp) {
		this.createtime = timestamp;
	}

	@Override
	public String toString() {
		return "Orders [did=" + did + ", uid=" + uid + ", recname=" + recname + ", recaddr=" + recaddr + ", recpthone="
				+ recpthone + ", dprice=" + dprice + ", dstate=" + dstate + ", createtime=" + createtime + ", passCode="
				+ passCode + "]";
	}
	
	
}
