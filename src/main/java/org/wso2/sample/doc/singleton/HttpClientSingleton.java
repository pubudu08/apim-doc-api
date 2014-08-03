package org.wso2.sample.doc.singleton;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * Created by pubudu on 8/1/14.
 */
public class HttpClientSingleton {

	private static HttpClient INSTANCE = null;

	public static HttpClient getHttpClientInstance(){
		if(INSTANCE==null){
			INSTANCE = HttpClientBuilder.create().build();
		}
		return INSTANCE;
	}
}
