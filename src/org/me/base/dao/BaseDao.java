package org.me.base.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.me.base.user.util.Myutils;

public class BaseDao<T> {
	/**
	 * 获取一个字段
	 * @param sql
	 * @param orgs
	 * @return
	 * @throws SQLException
	 */
	public Object getE(String sql,Object...orgs) throws SQLException{
		Connection conn = null;
		Object obj = null;
		try {
			conn = Myutils.ds.getConnection();
			ScalarHandler<Object> rsh = new ScalarHandler<Object>();
			obj = Myutils.qr.query(conn, sql, rsh,orgs);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
		
	}
	
	/**
	 * 获取一个的方法
	 * @param sql
	 * @param orgs
	 * @return
	 */
	public T getT(String sql,Object...orgs){
		Connection conn = null;
		try {
			conn = Myutils.ds.getConnection();
			BeanHandler<T> rsh = (BeanHandler<T>)new BeanHandler<T>(Myutils.getGenericClass(this.getClass()));
			T t = Myutils.qr.query(conn, sql,rsh,orgs);
			conn.close();
			return t;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<T> getAll(String sql,Object...args){
		Connection conn = null;
		try {
			conn = Myutils.ds.getConnection();
			List<T> ts = new ArrayList<T>();
			BeanListHandler<T> rsh = new BeanListHandler<T>(Myutils.getGenericClass(this.getClass()));
			ts = Myutils.qr.query(conn, sql, rsh,args);
			conn.close();
			return ts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
