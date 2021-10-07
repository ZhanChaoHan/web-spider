package com.jachs.jsoup.web;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

/****
 * 提取内容
 * @author zhanchaohan
 *@see https://jsoup.org/cookbook/extracting-data/dom-navigation
 *@see https://jsoup.org/cookbook/extracting-data/selector-syntax
 *@see https://jsoup.org/cookbook/extracting-data/attributes-text-html
 *@see https://jsoup.org/cookbook/extracting-data/working-with-urls
 *@see https://jsoup.org/cookbook/extracting-data/example-list-links
 */
public class ExtractingData {
	/***
	 * <b>Finding elements</b></br>
			getElementById(String id)</br>
			getElementsByTag(String tag)</br>
			getElementsByClass(String className)</br>
			getElementsByAttribute(String key) (and related methods)</br>
			Element siblings: siblingElements(), firstElementSibling(), lastElementSibling(); nextElementSibling(), previousElementSibling()</br>
			Graph: parent(), children(), child(int index)</br>
		<b>Element data</b></br>
			attr(String key) to get and attr(String key, String value) to set attributes</br>
			attributes() to get all attributes</br>
			id(), className() and classNames()</br>
			text() to get and text(String value) to set the text content</br>
			html() to get and html(String value) to set the inner HTML content</br>
			outerHtml() to get the outer HTML value</br>
			data() to get data content (e.g. of script and style tags)</br>
			tag() and tagName()</br>
		<b>Manipulating HTML and text</b></br>
			append(String html), prepend(String html)</br>
			appendText(String text), prependText(String text)</br>
			appendElement(String tagName), prependElement(String tagName)</br>
			html(String value)</br>
			Cookbook contents</br>
			Introduction</br>
			Parsing and traversing a Document</br>
			Input</br>
			Parse a document from a String</br>
			Parsing a body fragment</br>
			Load a Document from a URL</br>
			Load a Document from a File</br>
			Extracting data</br>
			Use DOM methods to navigate a document</br>
			Use selector-syntax to find elements</br>
			Extract attributes, text, and HTML from elements</br>
			Working with URLs</br>
			Example program: list links</br>
			Modifying data</br>
			Set attribute values</br>
			Set the HTML of an element</br>
			Setting the text content of elements</br>
			Cleaning HTML</br>
			Sanitize untrusted HTML (to prevent XSS)</br>

	 * @throws IOException
	 */
	@Test
	public void Use_DOM_methods_to_navigate_a_document() throws IOException {
		File input = new File(ExtractingData.class.getResource("/htmltests/news-com-au-home.html").getPath());
		Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");

		Element content = doc.getElementById("content");
		Elements links = content.getElementsByTag("a");
		for (Element link : links) {
		  String linkHref = link.attr("href");
		  String linkText = link.text();
		  
		  System.out.println(linkHref+"\t"+linkText);
		}
	}
	/***
	<b>Selector overview</b></br>
		tagname: find elements by tag, e.g. a</br>
		ns|tag: find elements by tag in a namespace, e.g. fb|name finds <fb:name> elements</br>
		#id: find elements by ID, e.g. #logo</br>
		.class: find elements by class name, e.g. .masthead</br>
		[attribute]: elements with attribute, e.g. [href]</br>
		[^attr]: elements with an attribute name prefix, e.g. [^data-] finds elements with HTML5 dataset attributes</br>
		[attr=value]: elements with attribute value, e.g. [width=500] (also quotable, like [data-name='launch sequence'])</br>
		[attr^=value], [attr$=value], [attr*=value]: elements with attributes that start with, end with, or contain the value, e.g. [href*=/path/]</br>
		[attr~=regex]: elements with attribute values that match the regular expression; e.g. img[src~=(?i)\.(png|jpe?g)]</br>
		*: all elements, e.g. *</br>
	<b>Selector combinations</b></br>
		el#id: elements with ID, e.g. div#logo</br>
		el.class: elements with class, e.g. div.masthead</br>
		el[attr]: elements with attribute, e.g. a[href]</br>
		Any combination, e.g. a[href].highlight</br>
		ancestor child: child elements that descend from ancestor, e.g. .body p finds p elements anywhere under a block with class "body"</br>
		parent > child: child elements that descend directly from parent, e.g. div.content > p finds p elements; and body > * finds the direct children of the body tag</br>
		siblingA + siblingB: finds sibling B element immediately preceded by sibling A, e.g. div.head + div</br>
		siblingA ~ siblingX: finds sibling X element preceded by sibling A, e.g. h1 ~ p</br>
		el, el, el: group multiple selectors, find unique elements that match any of the selectors; e.g. div.masthead, div.logo</br>
	<b>Pseudo selectors</b></br>
		:lt(n): find elements whose sibling index (i.e. its position in the DOM tree relative to its parent) is less than n; e.g. td:lt(3)</br>
		:gt(n): find elements whose sibling index is greater than n; e.g. div p:gt(2)</br>
		:eq(n): find elements whose sibling index is equal to n; e.g. form input:eq(1)</br>
		:has(selector): find elements that contain elements matching the selector; e.g. div:has(p)</br>
		:not(selector): find elements that do not match the selector; e.g. div:not(.logo)</br>
		:contains(text): find elements that contain the given text. The search is case-insensitive; e.g. p:contains(jsoup)</br>
		:containsOwn(text): find elements that directly contain the given text</br>
		:matches(regex): find elements whose text matches the specified regular expression; e.g. div:matches((?i)login)</br>
		:matchesOwn(regex): find elements whose own text matches the specified regular expression</br>
		Note that the above indexed pseudo-selectors are 0-based, that is, the first element is at index 0, the second at 1, etc</br>
	 * @throws IOException
	 */
	@Test
	public void Use_selector_syntax_to_find_elements() throws IOException{
		File input = new File("/tmp/input.html");
		Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");

		Elements links = doc.select("a[href]"); // a with href
		Elements pngs = doc.select("img[src$=.png]");
		  // img with src ending .png

		Element masthead = doc.select("div.masthead").first();
		  // div with class=masthead

		Elements resultLinks = doc.select("h3.r > a"); // direct a after h3
	}
}
