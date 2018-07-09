package org.me.user.entity;

public class Teacher {
	private Integer id;
	private String teachername;
	private String password;
	private String email;
	private String phoneNum;
	private Integer credit;
	private String avatar;
	private Byte type;
	private String sex;
	private String birthday;
	private Integer state;
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	// 老师所交学生
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTeachername() {
		return teachername;
	}

	public void setTeachername(String teachername) {
		this.teachername = teachername;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Teacher(String teachername, String password, String email, String phoneNum, Integer credit, String avatar,
			Byte type, String sex, String birthday,Integer state) {
		super();

		this.teachername = teachername;
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

	public Teacher() {
		super();
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", teachername=" + teachername + ", password=" + password + ", email=" + email
				+ ", phoneNum=" + phoneNum + ", credit=" + credit + ", avatar=" + avatar + ", type=" + type + ", sex="
				+ sex + ", birthday=" + birthday + ", user=" + user + "]";
	}

}
