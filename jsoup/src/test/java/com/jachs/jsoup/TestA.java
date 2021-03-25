package com.jachs.jsoup;

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

/***
 * jsoup api个包提供用于开发jsoup应用程序的类和接口。<br>
 * org.jsoup<br>
 * org.jsoup.examples<br>
 * org.jsoup.helper<br>
 * org.jsoup.nodes<br>
 * org.jsoup.parser<br>
 * org.jsoup.safety<br>
 * org.jsoup.salect<br>
 * 主要类：<br>
 * Jsoup 类提供了连接，清理和解析HTML文档的方法<br>
 * Document 获取HTML文档<br>
 * Element 获取、操作HTML节点<br>
 * 
 * @author zhanchaohan
 *
 */
public class TestA {
	private static final String testFilePath=TestA.class.getResource("/htmltests/smh-biz-article-1.html").getPath();
	
	@Test
	public void test1() throws Exception {
//		Document document = Jsoup.connect("http://www.baidu.com").get();
		
		Document document = Jsoup.parse(new File(testFilePath), "utf-8");
		
//		System.out.println(document.head());//全部head内容
//		System.out.println(document.body());//全部body内容
	}
}
