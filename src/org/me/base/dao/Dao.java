package org.me.base.dao;

import java.util.List;

public interface Dao<T> {
	//获取一个数据
	T getT(int id);
	//获取全部信息
	List<T> getAll(Object...args);
	//修改信息
	int update(T t);
	//删除信息
	int delete(int t);
	//添加
	int add(T t);
	//
	int insert(T t);
}
