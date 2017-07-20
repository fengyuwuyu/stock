package com.stock.test;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.stock.util.EETemplate;

public class Testt {

	@Test
	public void test1() throws Exception{
		EETemplate<People> template = new EETemplate<>();
		
		String[] headers = {"用户名", "密码"};
		String[] fields = {"username", "passwd"};
		List<People> dataset = new ArrayList<>();
		dataset.add(new People("张三", "123456"));
		dataset.add(new People("李四", "123456"));
		OutputStream out = new FileOutputStream("D:/test.xls");
		template.callExportExcel("测试", headers, fields, dataset, out);
		out.close();
	}
	
}


