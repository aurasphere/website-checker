/*
 * MIT License
 *
 * Copyright (c) 2018 Donato Rimenti
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package co.aurasphere.website;

import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test class which checks that a website is currently up and working properly
 * for each of its TLD .
 * 
 * @author Donato Rimenti
 *
 */
public class WebsiteCheckerTest {

	/**
	 * The .it website to check.
	 */
	private final static String IT_WEBSITE_URL = "http://www.google.it";

	/**
	 * The .com website to check.
	 */
	private final static String COM_WEBSITE_URL = "http://www.google.com";

	/**
	 * Expected website title to check.
	 */
	private final static String SITE_TITLE = "Google";

	/**
	 * ID of an element present on the website to check.
	 */
	private final static String ELEMENT_ID_TO_CHECK = "hplogo";

	/**
	 * Checks that {@value #IT_WEBSITE_URL} is working properly.
	 *
	 * @throws IOException
	 */
	@Test
	public void testItWebsite() throws IOException {
		testConnection(IT_WEBSITE_URL);
	}

	/**
	 * Checks that {@value #COM_WEBSITE_URL} is working properly.
	 *
	 * @throws IOException
	 */
	@Test
	public void testComWebsite() throws IOException {
		testConnection(COM_WEBSITE_URL);
	}

	/**
	 * Tests common code. Checks that when opening the website the status returned
	 * is 200, the title of the page is {@value #SITE_TITLE} and there's an element
	 * with ID {@value #ELEMENT_ID_TO_CHECK}.
	 *
	 * @param url the URL of the website to check
	 * @throws IOException
	 */
	private void testConnection(String url) throws IOException {
		// Establishes a connection to the website.
		Response response = Jsoup.connect(url).execute();
		int responseCode = response.statusCode();

		// Checks the response code.
		Assert.assertEquals(
				String.format("Connection to %s failed with code %d. Website may be down!", url, responseCode),
				responseCode, 200);

		// Parses the HTML page.
		Document page = response.parse();

		// Checks that the page title matches the one expected.
		Assert.assertEquals(String.format("Website title %s doesn't match %s", url, SITE_TITLE), SITE_TITLE,
				page.title());

		// Checks that an element with a predefined ID is present on page.
		Assert.assertNotNull(String.format("Element with id %s not found on website %s", ELEMENT_ID_TO_CHECK, url),
				page.getElementById(ELEMENT_ID_TO_CHECK));
	}

}
