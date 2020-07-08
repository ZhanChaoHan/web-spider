package com.jachs.Reptiles;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/***
 * 
 * @author zhanchaohan
 *
 */
public class ReptilesApplication {
	private static final String MainUrl="https://github.com/spring-projects/spring-boot";
	private static final String[] FilterList=new String[] {"apche","java"};
	
	
	//循环文件夹或查询文件
	private static void LoopFileOrFolder(String Url,String Name) throws IOException {
		System.out.println(Name+"\t\t"+Url);
		StringBuffer sb=new StringBuffer(Url);
		if(!StringUtils.startsWith(Name,".")&& StringUtils.contains(Name, '.')) {
			sb.append("/blob/master/").append(Name); 
			Document filedoc = Jsoup.connect(sb.toString()).get();
			Elements td=filedoc.select("td");
			for (Element ele : td) {
				for (String filter : FilterList) {
					if(StringUtils.contains(ele.text(), filter)) {
						System.out.println("抓取内容:"+ele.text());
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Document document = Jsoup.connect(MainUrl).get();
		//首页所有文件夹或文件名称
	                                                                                                                                                                                                                                                                                                                   
		Elements links = document.select("a.js-navigation-open.link-gray-dark");
		for (Element link : links) {
//			System.out.println("text :" + link.text());
//			System.out.println(link);
//			System.out.println(link.attr("title"));
			LoopFileOrFolder(MainUrl,link.attr("title"));
		}
//		SpringApplication.run(ReptilesApplication.class, args);
	}

}
