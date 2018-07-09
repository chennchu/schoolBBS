package org.me.base.user.util;

import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.dbutils.QueryRunner;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Myutils {
	//配置信息
	public static Map<String,String> config = new HashMap<String, String>();
	public static final ComboPooledDataSource ds = new ComboPooledDataSource();
	
	public static final QueryRunner qr = new QueryRunner();
	
	public static final int isonsale = 1;
	
	public static final int pageSize = 15;
	
	public static final ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	static{
		//通过类加载器获取配置文件的输入流
		InputStream is = Myutils.class.getClassLoader().getResourceAsStream("config.properties");
		Properties props = new Properties();
		//通过Properties对象将配置文件的信息读取出来放到properties对象中
		try {
			props.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//获取配置信息
		String driver = props.getProperty("driver");
		String url = props.getProperty("url");
		String user = props.getProperty("user");
		String pwd = props.getProperty("password");
		String max = props.getProperty("max");
		String min = props.getProperty("min");
		String init = props.getProperty("init");
		String increment = props.getProperty("increment");
		//配置数据源
		try {
			ds.setDriverClass(driver);
			ds.setJdbcUrl(url);
			ds.setUser(user);
			ds.setPassword(pwd);
			ds.setMinPoolSize(Integer.parseInt(min));
			ds.setMaxPoolSize(Integer.parseInt(max));
			ds.setInitialPoolSize(Integer.parseInt(init));
			ds.setAcquireIncrement(Integer.parseInt(increment));
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取父类泛型
	 */
	public static Class getGenericClass(Class c){
		ParameterizedType pt = (ParameterizedType)c.getGenericSuperclass();
		Type t = (Type)pt.getActualTypeArguments()[0];
		Class clazz = (Class)t;
		return clazz;
	}

	/**
	 * 使用MD5加密
	 * @return 
	 */
	public static String md5(String password) {
		String str = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("md5");
			byte[] digest = md5.digest(password.getBytes());
			//转成十六进制的
			str = byteToHexStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return str;
		
	}
	
	//把byte数组变成字符串
	public static String byteToHexStr(byte[] digest) {
		char[] c = {'1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G'};
		StringBuffer sb = new StringBuffer();
		for(byte b:digest){
			sb.append(c[(b>>4)&15]);
			sb.append(c[b&15]);
			
		}
		return sb.toString();
	}
	
	//删除空文件夹
	public static void delFolder(String folderPath) {  
	     try {  
	     //实例化File
	        java.io.File myFilePath = new java.io.File(folderPath);  
	        myFilePath.delete(); //删除空文件夹  
	     } catch (Exception e) {  
	       e.printStackTrace();   
	     }  
	}
		

}
