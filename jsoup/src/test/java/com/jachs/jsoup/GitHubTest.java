package com.jachs.jsoup;


import java.io.File;
import java.io.FileWriter;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/***
 * 
 * @author zhanchaohan
 * ps:如果github网站更新HTML结构或CSS层叠样式表发生变化则代码肯定不好使毕竟是根据HTML标签和CSS抓的
 */
public class GitHubTest {
	private static final String MAINURL="https://github.com/spring-projects/protocol-fallback-demo";//项目地址
	private static final String BRANCH="/blob/master/";//分支目录
	private static final String[] FILTERLIST=new String[] {"java","spring"};//关键字过滤集合
	private static FileWriter  fw=null;
	/****
	 * 
	 * @param url  文件或文件夹路径
	 * @param Name 文件或文件夹名称
	 * @param IsFile 是否为文件,true文件
	 * @throws Exception 
	 */
	public static void loopFiles(String url,String Name,boolean IsFile) throws Exception {
		if(StringUtils.isBlank(Name)) {//空的就不要跑了
			return;
		}
		System.out.println(url);
		fw.write(url);
		if(IsFile) {
			Document document = null;
			try {
				document = Jsoup.connect(url.toString()).get();
			}catch (Exception e) {
				System.out.println("超时");
			}
			Elements tbody = document.select("tbody");//存放内容展示的tableHtml
			String info=tbody.text();//不要什么样式了连里边的span标签一起拿
			if(StringUtils.isBlank(info)) {//非文本文件
				return;
			}
			for (String filter : FILTERLIST) {//开始过滤
				if(StringUtils.containsIgnoreCase(info, filter)) {//不区分大小写
					System.out.println();
					fw.write("抓取到了："+info+"\t\t和关键字"+filter+"匹配上了，文件url："+url);
				}
			}
		}else {
			//不多说什么了就是循环，唯一不同就是把当前文件夹名称带进去
			Document folder = Jsoup.connect(url.toString()).get();
			Elements Tds = folder.select("div.Box-row");
			for (Element td : Tds) {
				String color= td.select(".mr-3").select("svg").attr("color");//获取到图片区分文件夹或文件
				String FileOrFolderName=td.select("div :eq(1) a").text();//文件夹或文件名称
				String LastCommWolds=td.select("div :eq(2) a").text();//最后提交注释
				String LastCommTime=td.select("div :eq(3) time-ago").text();//最后提交时间
				
				if(color.equals("blue-3")) {//文件夹
					loopFiles(url+"/"+FileOrFolderName,FileOrFolderName,false);
				}else {
					loopFiles(url+"/"+FileOrFolderName,FileOrFolderName,true);
				}
			}
		}
	}
	public static void main(String[] args) throws Exception {
//		fw=new FileWriter(new File(GitHubTest.class.getResource("").getPath()+File.separator+"info.txt"));
		fw=new FileWriter(new File("a.txt"));
		long startTime=System.currentTimeMillis();
		Document document = Jsoup.connect(MAINURL).get();
		Elements Tds = document.select("div.Box-row");//全部文件包括文件夹文件
		/*下边四个DIV
		 * 1:存放图片的区分文件夹图片还是文件图片就OK
		 * 2:文件夹或文件名称
		 * 3:最后提交注释
		 * 4:最后修改日期
		 */
		for (Element td : Tds) {
			String color= td.select(".mr-3").select("svg").attr("color");//获取到图片区分文件夹或文件
			String FileOrFolderName=td.select("div :eq(1) a").text();//文件夹或文件名称
			String LastCommWolds=td.select("div :eq(2) a").text();//最后提交注释
			String LastCommTime=td.select("div :eq(3) time-ago").text();//最后提交时间
			
//			System.out.println(color);
//			System.out.println(FileOrFolderName);
//			System.out.println(LastCommWolds);
//			System.out.println(LastCommTime);
			if(color.equals("blue-3")) {//文件夹
				StringBuffer newsUrl=new StringBuffer(MAINURL).append(BRANCH).append(FileOrFolderName);//整个新的文件路径
				loopFiles(newsUrl.toString(),FileOrFolderName,false);
			}else {
				loopFiles(MAINURL,FileOrFolderName,true);
			}
		}
		long endTime=System.currentTimeMillis();
		System.out.println("耗时:"+(double)(endTime-startTime)/1000+"秒");
		fw.close();
	}
}
