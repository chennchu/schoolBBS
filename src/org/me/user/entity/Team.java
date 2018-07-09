package org.me.user.entity;

/**
 * 班级类，用来存储学生作业
 * 
 * @author Administrator
 *
 */
public class Team {
	private Integer id;
	// 文件夹的id，即班级的班号
	private String classId;
	// 文件夹里的文件

	public Team() {
		super();
	}

	public Team(Integer id , String classId) {
		super();
		this.id = id;
		this.classId = classId;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

}
