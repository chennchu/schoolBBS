package org.me.user.entity;

/**
 * 帖子
 * 
 * @author Administrator
 *
 */

public class Topic {
	// 发表贴子的用户
	private String userName;
	// 帖子id
	private Integer id;
	// 帖子题目
	private String title;
	// 帖子内容
	private String content;
	// 审核
	private Integer audit;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getAudit() {
		return audit;
	}

	public void setAudit(Integer audit) {
		this.audit = audit;
	}

	public Topic() {
		super();
	}

	public Topic(String userName, Integer id, String title, String content, Integer audit) {
		super();
		this.userName = userName;
		this.id = id;
		this.title = title;
		this.content = content;
		this.audit = audit;
	}

	@Override
	public String toString() {
		return "Topic [userName=" + userName + ", id=" + id + ", title=" + title + ", content=" + content + ", audit="
				+ audit + "]";
	}

}
