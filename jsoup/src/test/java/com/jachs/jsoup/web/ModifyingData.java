package com.jachs.jsoup.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

/***
 * 修改
 * @author zhanchaohan
 *@see https://jsoup.org/cookbook/modifying-data/set-attributes
 *@see https://jsoup.org/cookbook/modifying-data/set-html
 *@see https://jsoup.org/cookbook/modifying-data/set-text
 */
public class ModifyingData {
	/***
	 * 添加屬性
	 */
	@Test
	public void Set_attribute_values() {
		String html = "<html><head><title>First parse</title></head>"
				+ "<body><div class='comments'><p>Parsed HTML into a doc.</p><a>我是标签</a></div><div class='masthead'>class masthead 内容</div></body></html>";
		Document doc = Jsoup.parse(html);
		//添加一个属性rel,=nofollow
		Elements addElements=doc.select("div.comments a").attr("rel", "nofollow");
		System.out.println(addElements.toString());
		System.out.println("-----------------");
		//添加个title和class
		Elements addElements2=doc.select("div.masthead").attr("title", "jsoup").addClass("round-box");
		System.out.println(addElements2.toString());
	}
	@Test
	public void Set_the_HTML_of_an_element() {
		StringBuffer sb=new StringBuffer();
		sb.append("<div>第一个div</div>");
		sb.append("<div>第二个div</div>");
		sb.append("<span>One</span>");
		sb.append("<span>第二个span</span>");
		
		Document doc = Jsoup.parse(sb.toString());
		Element div = doc.select("div").first(); // <div></div>
		div.html("<p>lorem ipsum</p>"); // <div><p>lorem ipsum</p></div>
		div.prepend("<p>First</p>");
		div.append("<p>Last</p>");
		// now: <div><p>First</p><p>lorem ipsum</p><p>Last</p></div>
		
		System.out.println(div.toString());
		Element span = doc.select("span").first(); // <span>One</span>
		Element wrap=span.wrap("<li><a href='http://example.com/'></a></li>");
		// now: <li><a href="http://example.com"><span>One</span></a></li>
		
		System.out.println("------------------");
		System.out.println(span.toString());
		System.out.println(wrap.toString());//不知为啥没生效。。。。。
	}
	@Test
	public void Setting_the_text_content_of_elements() {
		StringBuffer sb=new StringBuffer();
		sb.append("<div>第一个div</div>");
		sb.append("<div>第二个div</div>");
		
		Document doc = Jsoup.parse(sb.toString());
		
		Element div = doc.select("div").first(); // <div></div>
		div.text("five > four"); // <div>five &gt; four</div>
		div.prepend("First ");
		div.append(" Last");
		// now: <div>First five &gt; four Last</div>
		
		System.out.println(div.toString());
	}
}
