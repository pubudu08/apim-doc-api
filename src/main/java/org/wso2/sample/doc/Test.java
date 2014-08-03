package org.wso2.sample.doc;

import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.http.client.HttpClient;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.wso2.sample.doc.singleton.HttpClientSingleton;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by pubudu on 8/3/14.
 */
public class Test {

	public static void main(String[] args) {

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 100);
		Date date = calendar.getTime();

		DefaultHttpClient httpClient = new DefaultHttpClient();
		httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
		httpClient.setCookieStore(new BasicCookieStore());
		BasicClientCookie basicClientCookie = new BasicClientCookie("JSESSIONID=","E4AA7A07CB754AEB60AB6E1016CF56DC");
		basicClientCookie.setExpiryDate(date);
		basicClientCookie.setPath("/publisher/");
		httpClient.getCookieStore().addCookie(basicClientCookie);
		//httpClient.execute(yourHttpUriRequest);

	}
}
