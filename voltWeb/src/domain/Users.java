package domain;

public class Users 
{
	/**
	 * 默认构造函数
	 */
	public Users() {
		super();
	}

	/**
	 * 带参数的构造函数
	 * @param uid
	 * @param uname
	 * @param usex
	 * @param uphone
	 * @param umail
	 * @param uamt
	 * @param upayd
	 * @param age
	 */
	public Users(String uid, String upasswd, String uname, String usex, String uphone, String umail, long uamt, long upayd, int age) {
		super();
		this.uid = uid;
		this.upasswd = upasswd;
		this.uname = uname;
		this.usex = usex;
		this.uphone = uphone;
		this.umail = umail;
		this.uamt = uamt;
		this.upayd = upayd;
		this.age = age;
	}

	/**
	 * 用户ID
	 */
	private String uid;
	
	/**
	 * password
	 */
	private String upasswd;
	/**
	 * 用户名
	 */
	private String uname;
	
	/**
	 * 性别
	 */
	private String usex;
	
	/**
	 * 用户手机号
	 */
	private String uphone;
	
	/**
	 * 用户邮箱
	 */
	private String umail;
	
	/**
	 * 用户余额
	 */
	private long uamt;
	
	/**
	 * 用户以前的消费，利用该字段判定哟过户等级
	 */
	private long upayd;
	
	/**
	 * 用户年龄
	 */
	private int age;

	/**
	 * the method of setter and getter
	 * @return
	 */
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	

	public String getUpasswd() {
		return upasswd;
	}

	public void setUpasswd(String upasswd) {
		this.upasswd = upasswd;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUsex() {
		return usex;
	}

	public void setUsex(String usex) {
		this.usex = usex;
	}

	public String getUphone() {
		return uphone;
	}

	public void setUphone(String uphone) {
		this.uphone = uphone;
	}

	public String getUmail() {
		return umail;
	}

	public void setUmail(String umail) {
		this.umail = umail;
	}

	public long getUamt() {
		return uamt;
	}

	public void setUamt(long uamt) {
		this.uamt = uamt;
	}

	public long getUpayd() {
		return upayd;
	}

	public void setUpayd(long upayd) {
		this.upayd = upayd;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
