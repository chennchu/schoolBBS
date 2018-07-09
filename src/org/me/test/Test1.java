package org.me.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.me.base.user.util.Myutils;
import org.me.user.entity.FileCreteria;
import org.me.user.entity.Message;
import org.me.user.entity.MyFile;
import org.me.user.service.FileService;
import org.me.user.service.MessAgeService;

public class Test1 {
	@Test
	public void testConn(){
		Connection connection = null;
		try {
			connection = Myutils.ds .getConnection();
			System.out.println(connection);
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getAllFiles(){
		FileService service = new FileService();
		List<MyFile> list = service.get();
		System.out.println(list);
	}
	
	@Test
	public void fenyeyoutiaojian(){
		FileService service = new FileService();
		FileCreteria fc = service.getFileByqualified("陈楚", "1", "id asc", "14100303");
		List<MyFile> page = service.getPage(fc, "14100303");
		System.out.println(page);
	}
	@Test
	public void fenyegetAllFiles(){
		FileService service = new FileService();
		FileCreteria fileCre = service.getFileByqualified2("", "1", "id asc", "14100303");
		List<MyFile> page = service.getPage2(fileCre,"14100303");
		System.out.println(page);
	}
	
	@Test
	public void getfilebyid(){
		FileService service = new FileService();
		MyFile f = service.getById(1);
		System.out.println(f);
	}
	
	@Test
	public void getMessage(){
		MessAgeService service = new MessAgeService();
		Message message = service.getMessage();
		System.out.println(message);
	}
	
	@Test
	public void getAllByClass(){
		String classid= "14100303";
		FileService service = new FileService();
		List<MyFile> files = service.getAll(classid);
		System.out.println(files);
	}
	
	@Test
	public void deleteFolder(){
		Myutils.delFolder("D:\\myfile\\12100202");
	}
}
