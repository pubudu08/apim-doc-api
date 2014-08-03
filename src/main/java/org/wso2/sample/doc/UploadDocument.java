package org.wso2.sample.doc;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.wso2.sample.doc.authentication.AuthenticationAdminServiceClient;
import org.wso2.sample.doc.constants.APIConstantValues;
import org.wso2.sample.doc.singleton.HttpClientSingleton;

import java.io.File;
import java.io.IOException;

/**
 * Created by pubudu on 8/1/14.
 */
public class UploadDocument {

	public static final String ADD_DOC_API_CALL =
			"https://" + APIConstantValues.HOST_NAME + ":" + APIConstantValues.HTTPS_PORT +
			"/publisher/site/blocks/documentation/ajax/docs.jag?action=addDocumentation&mode='-'&" +
			"provider=subscriber1&apiName=TestDocAPI&version=1.0.0&docName=attachment&" +
			"docType=how+to&sourceType=file&docUrl='-'&summary=testing";
	public static final String LOGIN_API_CALL =
			"https://" + APIConstantValues.HOST_NAME + ":" + APIConstantValues.HTTPS_PORT +
			"/publisher/site/blocks/user/login/ajax/login.jag?action=login&username=" +
			APIConstantValues.USER_NAME + "&password=" + APIConstantValues.PASSWORD;
	public static final String FILE_PATH = "/home/pubudu/Desktop/t";

	public static void main(String[] args) {

		AuthenticationAdminServiceClient
				.setSystemProperties(APIConstantValues.CLIENT_TRUST_STORE_PATH,
				                     APIConstantValues.KEY_STORE_TYPE,
				                     APIConstantValues.KEY_STORE_PASSWORD);
		try {
			HttpClient httpClient = HttpClientSingleton.getHttpClientInstance();
			HttpPost request = new HttpPost(LOGIN_API_CALL);
			HttpPost docRequest = new HttpPost(ADD_DOC_API_CALL);
			HttpResponse response = httpClient.execute(request);
			System.out.println("Http Status Code := " + response.getStatusLine());
			for (Header header : response.getAllHeaders()) {
				if (header.getName().equals("Set-Cookie")) {
					docRequest.setHeader("Cookie", header.getValue().split(";", 2)[0]);
					System.out.println(header.getValue().split(";", 2)[0]);
				}
				System.out.println(header);
			}
			System.out.println();

			File document = new File(FILE_PATH);
			MultipartEntity multipartEntity = new MultipartEntity();
			System.out.println(document.getAbsolutePath());
			System.out.println();
			multipartEntity.addPart("docLocation", new StringBody(document.getAbsolutePath()));
			ContentBody fileBody = new FileBody(document, "text/plain");
			multipartEntity.addPart("attachment", fileBody);
			docRequest.setEntity(multipartEntity);

			for (Header header : docRequest.getAllHeaders()) {
				System.out.println(header);
			}
			System.out.println();
			System.out.println("executing request " + docRequest.getRequestLine());
			HttpResponse docResponse = httpClient.execute(docRequest);
			System.out.println("Http Status Code := " + docResponse.getStatusLine());
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
