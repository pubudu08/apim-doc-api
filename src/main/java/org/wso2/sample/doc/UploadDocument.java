package org.wso2.sample.doc;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.wso2.sample.doc.authentication.AuthenticationAdminServiceClient;
import org.wso2.sample.doc.constants.APIConstantValues;
import org.wso2.sample.doc.singleton.HttpClientSingleton;

import java.io.IOException;

/**
 * Created by pubudu on 8/1/14.
 */
public class UploadDocument {

	public static String ADD_DOC_API_CALL =
			"https://localhost:9453/publisher/site/blocks/documentation/ajax/docs.jag?action=addDocumentation&mode='-'&provider=subscriber1&apiName=TestAPI&version=1.0.0&docName=testDoc&docType=how+to&sourceType=inline&docUrl='-'&summary=testing";
	public static String LOGIN_API_CALL =
			"https://localhost:9453/publisher/site/blocks/user/login/ajax/login.jag?action=login&username=admin&password=admin";

	public static void main(String[] args) {
		//String cookie = AccessToServer.signIn();
		AuthenticationAdminServiceClient
				.setSystemProperties(APIConstantValues.CLIENT_TRUST_STORE_PATH,
				                     APIConstantValues.KEY_STORE_TYPE,
				                     APIConstantValues.KEY_STORE_PASSWORD);
		try {

			HttpClient httpClient = HttpClientSingleton.getHttpClientInstance();
			//HttpGet request = new HttpGet(API_CALL);
			HttpPost request = new HttpPost(LOGIN_API_CALL);
			HttpPost docRequest = new HttpPost(ADD_DOC_API_CALL);
			request.addHeader("Content-Type", "application/x-www-form-urlencoded");
			request.addHeader("Connection", "keep-alive");
			// login to Publisher
			HttpResponse response = httpClient.execute(request);
			System.out.println("Http Status Code := " + response.getStatusLine());
			for (Header header : response.getAllHeaders()) {
				if (header.getName().equals("Set-Cookie")) {
					docRequest.setHeader("Cookie", header.getValue().split(";", 2)[0]);
					//System.out.println(header.getValue().split(";",2)[0]);
				}
				System.out.println(header);
			}
			System.out.println();
			HttpResponse docResponse = httpClient.execute(docRequest);
			System.out.println("Http Status Code := " + docResponse.getStatusLine());
			for (Header header : docResponse.getAllHeaders()) {
				System.out.println(header);
			}
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
