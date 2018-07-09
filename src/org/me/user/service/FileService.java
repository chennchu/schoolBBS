package org.me.user.service;

import java.util.List;

import org.me.user.dao.FileDao;
import org.me.user.entity.FileCreteria;
import org.me.user.entity.MyFile;

public class FileService {

	/**
	 * 添加文件夹名进数据库
	 * @param classId
	 * @return
	 */
	public int addTeam(String classId) {
		FileDao dao = new FileDao();
	
		return 0;
	}

	/**
	 * 向数据库里添加文件
	 * @param myFile
	 * @return
	 */
	public int add(MyFile myFile) {
		FileDao dao = new FileDao();
		int i = dao.add(myFile);
		return i;
	}

	/**
	 * 获取文件信息
	 * @return
	 */
	public List<MyFile> get() {
		FileDao dao = new FileDao();
		List<MyFile> myfile = dao.getAll();
		return myfile;
	}

	/**
	 * 有条件分页（查询数量）
	 * @param keyword
	 * @param pageNo
	 * @param orderby
	 * @param classid 
	 * @return
	 */
	public FileCreteria getFileByqualified(String keyword, String pageNo, String orderby, String classid) {
		FileDao dao = new FileDao();
		//获取全部用户信息
		FileCreteria fileCreteria = new FileCreteria(Integer.parseInt(pageNo),orderby,keyword);
		int total = dao.getFileByqualified(keyword,classid);
		fileCreteria.setTotal(total);
		//计算总页数
		if(total%11==0){
			fileCreteria.setEnd(total/11);
		}else{
			fileCreteria.setEnd(total/11+1);
		}
		return fileCreteria;
	}

	/**
	 * 无条件分页（查询数量）
	 * @param classId 
	 * @param string
	 * @param string2
	 * @param string3
	 * @return
	 */
	public FileCreteria getFileByqualified2(String keyword, String pageNo, String orderby, String classId) {
		FileDao dao = new FileDao();
		FileCreteria fileCreteria = new FileCreteria(Integer.parseInt(pageNo),orderby,keyword);
		int total = dao.getAllCount(classId);
		fileCreteria.setTotal(total);
		//计算总页数
				if(total%11==0){
					fileCreteria.setEnd(total/11);
				}else{
					fileCreteria.setEnd(total/11+1);
				}
				return fileCreteria;
	}

	/**
	 * 无条件分页
	 * @param fileCreteria
	 * @param classId 
	 * @return
	 */
	public List<MyFile> getPage2(FileCreteria fileCreteria, String classId) {
		FileDao dao = new FileDao();
		List<MyFile> page = dao.getPage2(fileCreteria,classId);
		return page;
	}

	/**
	 * 有条件分页
	 * @param fileCreteria
	 * @param classid 
	 * @return
	 */
	public List<MyFile> getPage(FileCreteria fileCreteria, String classid) {
		FileDao dao = new FileDao();
		List<MyFile> page = dao.getPage(fileCreteria,classid);
		return page;
	}

	/**
	 * 通过id查找该文件
	 * @param id
	 * @return
	 */
	public MyFile getById(int id) {
		
		FileDao fileDao = new FileDao();
		MyFile myFile = fileDao.getById(id);
		return myFile;
	}

	/**
	 * 通过文件id 删除
	 * @param id
	 * @return
	 */
	public int deleteById(Integer id) {
		FileDao dao = new FileDao();
		int i = dao.delete(id);
		return i;
	}

	/**
	 * 通过文件夹名获取文件夹里的全部文件
	 * @param classid
	 * @return
	 */
	public List<MyFile> getAll(String classid) {
		FileDao dao = new FileDao();
		List<MyFile> files = dao.getAllByClassId(classid);
		return files;
	}
	

}
