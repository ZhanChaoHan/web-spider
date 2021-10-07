package com.jachs.jsoup.web;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.junit.Test;

/***
 * @author zhanchaohan
 * @see https://jsoup.org/cookbook/cleaning-html/safelist-sanitizer
 */
public class CleaningHTML {
	@Test
	public void clearUp() {
		Whitelist Safelist=new Whitelist();//白名單
		
		String unsafe = 
				  "<p><a href='http://example.com/' onclick='stealCookies()'>Link</a></p>";
				String safe = Jsoup.clean(unsafe, Safelist.basic());
				// now: <p><a href="http://example.com/" rel="nofollow">Link</a></p>
				
				System.out.println(safe.toString());
	}
}
