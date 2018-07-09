package org.me.user.entity;

public class Message {
	private Integer id;
	private String content;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Message(Integer id, String content) {
		super();
		this.id = id;
		this.content = content;
	}

	public Message() {
		super();
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", content=" + content + "]";
	}

}
