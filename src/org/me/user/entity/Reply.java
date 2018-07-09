package org.me.user.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 回复贴
 * 
 * @author Administrator
 *
 */
public class Reply {
	// 回复的条数
	private Integer id;
	// 主题板块id
	private Integer topicId;
	// 回复者的用户名
	private String userName;
	// 回复者的头像
	private String avatar;
	// 回复的内容
	private String content;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Reply(Integer id, Integer topicId, String userName, String avatar, String content) {
		super();
		this.id = id;
		this.topicId = topicId;
		this.userName = userName;
		this.avatar = avatar;
		this.content = content;
	}

	public Reply(Integer topicId, String userName, String avatar, String content) {
		super();
		this.topicId = topicId;
		this.userName = userName;
		this.avatar = avatar;
		this.content = content;
	}

	public Reply() {
		super();
	}

	@Override
	public String toString() {
		return "Reply [id=" + id + ", topicId=" + topicId + ", userName=" + userName + ", avatar=" + avatar
				+ ", content=" + content + "]";
	}

}
