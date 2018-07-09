package org.me.user.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.me.base.dao.BaseDao;
import org.me.base.dao.Dao;
import org.me.base.user.util.Myutils;
import org.me.user.entity.FileCreteria;
import org.me.user.entity.MyFile;
import org.me.user.entity.User;


public class FileDao extends BaseDao<MyFile> implements Dao<MyFile>{

	/**
	 * 添加文件夹名到数据库
	 * @param classId
	 * @return
	 */
	public int addTeam(String classId) {
		
		return 0;
	}

	@Override
	public MyFile getT(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public int update(MyFile t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int t) {
		Connection conn = null;
		int i = 0;
		//删除文件
		try {
			conn = Myutils.ds.getConnection();
			String sql = "delete from myfile where id=?";
			i = Myutils.qr.update(conn, sql, t);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * 向数据库里添加文件
	 */
	@Override
	public int add(MyFile t) {
		Connection conn = null;
		int i =0;
		try {
			conn = Myutils.ds.getConnection();
			String sql = "insert into myfile values(?,?,?,?,?,?,?)";
			i = Myutils.qr.update(conn, sql, null,t.getName(),t.getSize(),t.getType(),t.getProgress(),t.getPath(),t.getClassid());
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int insert(MyFile t) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 有条件分页查总数量
	 * @param keyword
	 * @param classid 
	 * @return
	 */
	public int getFileByqualified(String keyword, String classid) {
		Connection conn = null;
		String upkeyword = "'%" + keyword + "%'";
		int total = 0;
		// 查询所有含有关键字的帖子数量
		try {
			conn = Myutils.ds.getConnection();
			String sql = "select count(id) from myfile where classId='"+classid+"' and name like '%" + keyword + "%'";
			Object obj = Myutils.qr.query(conn, sql, new ScalarHandler());
			Number n = (Number) obj;
			total = n.intValue();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}

	/**
	 * 无条件分页查数量
	 * @param classId 
	 * @return
	 */
	public int getAllCount(String classId) {
		Connection conn = null;
		int total = 0;
		try {
			conn = Myutils.ds.getConnection();
			String sql = "select count(id) from myfile where classId='"+classId+"'";
			Object obj = Myutils.qr.query(conn, sql, new ScalarHandler());
			Number n = (Number) obj;
			total = n.intValue();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}

	/**
	 * 无条件分页
	 * @param fileCreteria
	 * @param classId 
	 * @return
	 */
	public List<MyFile> getPage2(FileCreteria fileCreteria, String classId) {
		List<MyFile> files = new ArrayList<MyFile>();
		String sql = "select * from myfile where classId=? limit ?,?";
		files = this.getAll(sql,classId, fileCreteria.getFrom(), fileCreteria.getPageSize());
		return files;
	}

	/**
	 * 有条件分页
	 * @param fileCreteria
	 * @param classid 
	 * @return
	 */
	public List<MyFile> getPage(FileCreteria fileCreteria, String classid) {
		List<MyFile> files = new ArrayList<MyFile>();
		/*String sql = "select id,name,classId from myfile where order by "
				+ fileCreteria.getOrderby()+" limit "","+fileCreteria.getPageNo();*/
		/*String sql = "select id,name,classId from myfile where name like '%"+fileCreteria.getKeyword()+"%' order by ? limit ?,?";*/
				
		String sql = "select id,name,classId from myfile where name like '%"+fileCreteria.getKeyword()+"%' order by "
				+ fileCreteria.getOrderby()+" limit "+fileCreteria.getFrom()+","+fileCreteria.getPageSize();
				files = this.getAll(sql);
		return files;
	}

	/**
	 * 通过id寻找该文件
	 * @param id
	 * @return
	 */
	public MyFile getById(int id) {
		String sql ="select * from myfile where id=?";
		MyFile t = this.getT(sql,id);
		return t;
	}

	@Override
	public List<MyFile> getAll(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 通过classid获取文件
	 * @param classid
	 * @return
	 */
	public List<MyFile> getAllByClassId(String classid) {
			String sql = "select * from myfile where classId=?";
			List<MyFile> myfiles = this.getAll(sql, classid);
		return myfiles;
	}

	
	}

	

