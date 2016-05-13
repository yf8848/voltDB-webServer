package domain;

public class Merchants 
{
	
	/**
	 * 默认构造函数
	 */
	public Merchants() {
		super();
	}

	/**
	 * 带参数构造函数
	 * @param spid
	 * @param spname
	 * @param spamt
	 * @param sprank
	 * @param sppthone
	 * @param spaddress
	 */
	public Merchants(String spid, String passwd, String spname, long spamt, byte sprank, String sppthone, String spaddress) {
		super();
		this.spid = spid;
		this.sppasswd = passwd;
		this.spname = spname;
		this.spamt = spamt;
		this.sprank = sprank;
		this.sppthone = sppthone;
		this.spaddress = spaddress;
	}

	/**
	 * 商户id
	 */
	private String spid;
	
	/**
	 * 商户名
	 */
	private String spname;
	
	/**
	 * password
	 */
	private String sppasswd;
	/**
	 * 商户账户金额
	 */
	private long spamt;
	
	/**
	 * 商户等级
	 */
	private byte sprank;
	
	/**
	 * 商户手机号
	 */
	private String sppthone;
	
	/**
	 * 商户地址
	 */
	private String spaddress;

	/**
	 * the mothod of getter and setter
	 * @return
	 */
	public String getSpid() {
		return spid;
	}

	public void setSpid(String spid) {
		this.spid = spid;
	}

	
	public String getSppasswd() {
		return sppasswd;
	}

	public void setSppasswd(String sppasswd) {
		this.sppasswd = sppasswd;
	}

	public String getSpname() {
		return spname;
	}

	public void setSpname(String spname) {
		this.spname = spname;
	}

	public long getSpamt() {
		return spamt;
	}

	public void setSpamt(long l) {
		this.spamt = l;
	}

	public byte getSprank() {
		return sprank;
	}

	public void setSprank(byte sprank) {
		this.sprank = sprank;
	}

	public String getSppthone() {
		return sppthone;
	}

	public void setSppthone(String sppthone) {
		this.sppthone = sppthone;
	}

	public String getSpaddress() {
		return spaddress;
	}

	public void setSpaddress(String spaddress) {
		this.spaddress = spaddress;
	}
	
}
