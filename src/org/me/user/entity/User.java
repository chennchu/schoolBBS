package org.me.user.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用户信息
 * 
 * @author Administrator
 *
 */
public class User {
	private Integer id;

	private String username;

	private String password;

	private String email;

	private String phoneNum;
	

	// private Date createTime;
	//
	// private Date updateTime;

	private Integer credit;
	//头像
	private String avatar;

	private Byte type;
	//性别
	private String sex;
	//生日
	private String birthday;
	private Integer state;
	

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	/*
	 * public Date getCreateTime() { return createTime; }
	 * 
	 * public void setCreateTime(Date createTime) { this.createTime =
	 * createTime; }
	 * 
	 * public Date getUpdateTime() { return updateTime; }
	 * 
	 * public void setUpdateTime(Date updateTime) { this.updateTime =
	 * updateTime; }
	 */

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	

	public User(String username, String password, String email, String phoneNum, Integer credit, String avatar,
			Byte type, String sex, String birthday,Integer state) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.phoneNum = phoneNum;
		this.credit = credit;
		this.avatar = avatar;
		this.type = type;
		this.sex = sex;
		this.birthday = birthday;
		this.state = state;
	}

	/*
	 * public User(Integer id, String username, String password, String email,
	 * String phoneNum, Date createTime, Date updateTime, Integer credit, String
	 * avatar, Byte type) { super(); this.id = id; this.username = username;
	 * this.password = password; this.email = email; this.phoneNum = phoneNum;
	 * this.createTime = createTime; this.updateTime = updateTime; this.credit =
	 * credit; this.avatar = avatar; this.type = type; }
	 */
	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", phoneNum=" + phoneNum + ", credit=" + credit + ", avatar=" + avatar + ", type=" + type + "]";
	}

	/*
	 * @Override public String toString() { return "User [id=" + id +
	 * ", username=" + username + ", password=" + password + ", email=" + email
	 * + ", phoneNum=" + phoneNum + ", createTime=" + createTime +
	 * ", updateTime=" + updateTime + ", credit=" + credit + ", avatar=" +
	 * avatar + ", type=" + type + "]"; }
	 */

	/*
	 * public String getLocalCreateTime() { SimpleDateFormat df = new
	 * SimpleDateFormat("yyyy-M-dd HH:mm");//设置日期格式 String date =
	 * df.format(this.createTime); return date; } public String
	 * getLocalUpdateTime() { SimpleDateFormat df = new
	 * SimpleDateFormat("yyyy-M-dd HH:mm");//设置日期格式 String date =
	 * df.format(updateTime); return date; }
	 */

}
