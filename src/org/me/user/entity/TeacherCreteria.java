package org.me.user.entity;

public class TeacherCreteria {
	// 所在页码
	private Integer pageNo;
	// 选择排序方式
	private String orderby;
	// 模糊查询
	private String keyword;
	// 总页数
	private Integer total;
	// 每一页的条数
	private Integer pageSize = 10;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	// 起始的条数，通过total计算需要查询的数据
	private Integer from;
	// 结束的条数
	private Integer end;

	public Integer getFrom() {
		from = (this.pageNo - 1) * this.pageSize;
		return from;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public TeacherCreteria() {
		super();
	}

	public TeacherCreteria(Integer pageNo, String orderby, String keyword, Integer from, Integer end) {
		super();
		this.pageNo = pageNo;
		this.orderby = orderby;
		this.keyword = keyword;
		this.from = from;
		this.end = end;
	}

	public TeacherCreteria(Integer pageNo, String orderby, String keyword) {
		super();
		this.pageNo = pageNo;
		this.orderby = orderby;
		this.keyword = keyword;
	}

}
