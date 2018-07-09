package org.me.user.entity;

public class MyFile {

	// 获取上传文件的编号
	private Integer id;
	// 获取文件的文件名
	private String name;
	// 获取文件的大小（单位kb）
	private long size;
	// 获取文件的类型
	private String type;
	// 获取文件的当前状态
	private Integer progress;
	// 获取文件当前路径
	private String path;
	private String classid;

	
	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

/*	public MyFile(String name, long size, String type, Integer progress) {
		super();
		this.name = name;
		this.size = size;
		this.type = type;
		this.progress = progress;
	}*/

	public MyFile(Integer id, String name, long size, String type, String path,String classid) {
		super();
		this.id = id;
		this.name = name;
		this.size = size;
		this.type = type;
		this.path = path;
		this.classid = classid;
	}

	public MyFile() {
		super();
	}

	@Override
	public String toString() {
		return "MyFile [id=" + id + ", name=" + name + ", size=" + size + ", type=" + type + ", progress=" + progress
				+ ", path=" + path + "]";
	}

	

}
