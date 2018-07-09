package org.me.user.entity;

import java.util.Date;

public class LoginLog {
	//
	private Long id;
	//登陆用户的id
	private Integer userId;
	//登陆时间
	private Date loginTime;
	//登陆ip
	private String ip;

	private String device;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public LoginLog(Long id, Integer userId, Date loginTime, String ip, String device) {
		super();
		this.id = id;
		this.userId = userId;
		this.loginTime = loginTime;
		this.ip = ip;
		this.device = device;
	}

	public LoginLog() {
		super();
	}

	@Override
	public String toString() {
		return "LoginLog [id=" + id + ", userId=" + userId + ", loginTime=" + loginTime + ", ip=" + ip + ", device="
				+ device + "]";
	}

}
