package com.jachs.jsoup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JsoupApplicationTests {
	@Test
	public void test1() throws IOException {
		// 从URL加载HTML
		Document document = Jsoup.connect("http://www.baidu.com").get();
		String title = document.title();
		// 获取html中的标题
		System.out.println("title :" + title);

		// 从字符串加载HTML
		String html = "<html><head><title>First parse</title></head>"
				+ "<body><p>Parsed HTML into a doc.</p></body></html>";
		Document doc = Jsoup.parse(html);
		title = doc.title();
		System.out.println("title :" + title);

		// 从文件加载HTML
		doc = Jsoup.parse(new File("F:\\jsoup\\html\\index.html"), "utf-8");
		title = doc.title();
		System.out.println("title :" + title);
	}

	/***
	 * 获取html中的head，body,url等信息
	 * 
	 * @throws IOException
	 */
	@Test
	public void test2() throws IOException {
		Document document = Jsoup.connect("http://www.baidu.com").get();
		String title = document.title();

		System.out.println("title :" + title);
		// 获取html中的head
		System.out.println(document.head());
		// 获取html中的body
		// System.out.println(document.body());

		// 获取HTML页面中的所有链接
		Elements links = document.select("a[href]");
		for (Element link : links) {
			System.out.println("link : " + link.attr("href"));
			System.out.println("text :" + link.text());
		}
	}

	/***
	 * 获取URL的元信息
	 * 
	 * @throws IOException
	 */
	@Test
	public void test3() throws IOException {
		Document document = Jsoup.connect("https://passport.lagou.com").get();

		System.out.println(document.head());
		// 获取URL的元信息
		String description = document.select("meta[name=description]").get(0).attr("content");
		System.out.println("Meta description : " + description);

		String keywords = document.select("meta[name=keywords]").first().attr("content");
		System.out.println("Meta keyword : " + keywords);
	}

	/***
	 * 根据class名称获取表单
	 * 
	 * @throws IOException
	 */
	@Test
	public void test4() throws IOException {
		Document document = Jsoup.connect(
				"https://passport.lagou.com/login/login.html?signature=8ECBCDF2B86061432B425A0B94FC863B&service=https%253A%252F%252Fwww.lagou.com%252F&action=login&serviceId=lagou&ts=1547711303033")
				.get();
		// 获取拉勾网登入页面的body
		// System.out.println(document.body());
		// 根据class名称获取表单
		Elements formElement = document.getElementsByClass("form_body");
		System.out.println(formElement.html());
		// 获取URL的元信息
		for (Element inputElement : formElement) {
			String placeholder = inputElement.getElementsByTag("input").attr("placeholder");
			System.out.println(placeholder);
		}
	}

	/***
	 * 提取并打印表单参数
	 * 
	 * @throws IOException
	 */
	@Test
	public void test5() throws IOException {
		Document document = Jsoup.parse(new File("F:\\jsoup\\html\\login.html"), "utf-8");
		Element loginform = document.getElementById("registerform");

		Elements inputElements = loginform.getElementsByTag("input");
		for (Element inputElement : inputElements) {
			String key = inputElement.attr("name");
			String value = inputElement.attr("value");
			System.out.println("Param name: " + key + " -- Param value: " + value);
		}
	}

	/***
	 * 设置元素的html内容
	 * 
	 * @throws IOException
	 */
	@Test
	public void test6() throws IOException {
		Document document = Jsoup.parse(new File("F:\\jsoup\\html\\index.html"), "utf-8");
		System.out.println(document.body());// <div id="div1"></div>
		System.out.println("*************");
		Element div = document.select("div").first();
		div.html("<p>Hello</p>"); // <div id="div1"><p>Hello</p></div>
		div.prepend("<p>Fiest</p>"); // <div id="div1"><p>Fiest</p><p>Hello</p></div>
		div.append("<p>Last</p>"); // <div id="div1"><p>Fiest</p><p>Hello</p><p>Last</p></div>
		System.out.println(document.body());
		System.out.println("*************");
		System.out.println(div.text());

		System.out.println("*************");
		// 对元素包裹一个外部HTML内容
		div.wrap("<div id=\"div2\"></div>"); // <div id="div2"><div id="div1"><p>Fiest</p><p>Hello</p><p>Last</p></div>
		System.out.println(document.body());
	}

	/***
	 * 设置元素的文本内容
	 * 
	 * @throws IOException
	 */
	@Test
	public void test7() throws IOException {
		Document document = Jsoup.parse(new File("F:\\jsoup\\html\\index.html"), "utf-8");
		System.out.println(document.body());// <div id="div1"></div>
		System.out.println("*************");
		Element div = document.select("div").first();
		div.text("7 > 8 "); // <div id="div1">7 &gt; 8 </div>
		div.prepend("Fiest "); // <div id="div1">Fiest 7 &gt; 8</div>
		div.append("Last "); // <div id="div1">Fiest 7 &gt; 8 Last</div>
		System.out.println(document.body());
		System.out.println("*************");
		System.out.println(div.text());
	}
	//扒一波图片
	@Test
	public void test8() throws IOException {
		Document document = Jsoup.connect("https://car.autohome.com.cn/pic/").get();
		Elements links = document.select("img[src]");
		for (Element link : links) {
			System.out.println("link : " + link.attr("src"));
			System.out.println("text :" + link.text());
			downLoad("http:"+link.attr("src"));
		}
	}
	private void downLoad(String url) throws IOException {
		String []names=url.split("/");
		URL urls=new URL(url);
		URLConnection urlConn= urls.openConnection();
		int len=0;
		byte []byteArr=new byte [1024];
		InputStream is=urlConn.getInputStream();
		OutputStream os=new FileOutputStream(new File(new File("").getAbsoluteFile()+File.separator+"img"+File.separator+names[names.length-1]));
		while((len=is.read(byteArr))!=-1) {
			os.write(byteArr, 0,len);
		}
		os.close();
		is.close();
	}
}
