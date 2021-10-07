package com.jachs.jsoup.web;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

/***
 * 加载文件从URL或物理文件
 * @author zhanchaohan
 *@see https://jsoup.org/cookbook/input/parse-document-from-string
 *@see https://jsoup.org/cookbook/input/parse-body-fragment
 *@see https://jsoup.org/cookbook/input/load-document-from-url
 *@see https://jsoup.org/cookbook/input/load-document-from-file
 */
public class LoadUrlOrFiles {
	@Test
	public void Parsing_and_traversing_a_Document() {
		String html = "<html><head><title>First parse</title></head>"
				+ "<body><p>Parsed HTML into a doc.</p></body></html>";
		Document doc = Jsoup.parse(html);

		System.out.println(doc.toString());
	}

	/***
	 * parseBodyFragment方法创建一个空shell文档，并将解析后的HTML插入body元素。如果使用普通的Jsoup.parse（stringhtml）方法，</br>
	 * 通常会得到相同的结果，但是显式地将输入视为一个body片段可以确保用户提供的任何bozohtml都被解析到body元素中。</br>
	 * body（）方法检索文档的body元素的元素子元素；它相当于doc.getElementsByTag（“body”）。</br>
	 */
	@Test
	public void Parsing_a_body_fragment() {
		String html = "<div><p>Lorem ipsum.</p>";
		Document doc = Jsoup.parseBodyFragment(html);
		Element body = doc.body();

		System.out.println(body.toString());
	}
	@Test
	public void Load_a_Document_from_a_URL() throws IOException {
		Document doc = Jsoup.connect("http://example.com/").get();
		String title = doc.title();
		
		System.out.println(title);
		Document doc2 = Jsoup.connect("http://example.com")
				  .data("query", "Java")
				  .userAgent("Mozilla")
				  .cookie("auth", "token")
				  .timeout(3000)
				  .post();
		
		System.out.println(doc2.title());
	}
	@Test
	public void Load_a_Document_from_a_File() throws IOException {
		File input = new File(LoadUrlOrFiles.class.getResource("/bomtests/bom_utf16be.html").getPath());
		Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
		
		System.out.println(doc.text());
	}
}
