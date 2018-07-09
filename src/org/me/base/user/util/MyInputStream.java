package org.me.base.user.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyInputStream implements Cloneable {
	public InputStream is;

	public MyInputStream(InputStream is) {
		this.is = is;
	}
	
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
}
