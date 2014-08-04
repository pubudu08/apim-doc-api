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

	public static void main(String[] args) {

		AuthenticationAdminServiceClient
				.setSystemProperties(APIConstantValues.CLIENT_TRUST_STORE_PATH,
				                     APIConstantValues.KEY_STORE_TYPE,
				                     APIConstantValues.KEY_STORE_PASSWORD);
		try {
			HttpClient httpClient = HttpClientSingleton.getHttpClientInstance();
			HttpPost request = new HttpPost(APIConstantValues.LOGIN_API_CALL);
			HttpPost docRequest = new HttpPost(APIConstantValues.ADD_DOC_API_CALL);
			HttpResponse response = httpClient.execute(request);
			System.out.println("Sign In Http Status Code := " + response.getStatusLine());
			for (Header header : response.getAllHeaders()) {
				if (header.getName().equals("Set-Cookie")) {
					docRequest.setHeader("Cookie", header.getValue().split(";", 2)[0]);
				}
			}
			File document = new File(APIConstantValues.FILE_PATH);
			MultipartEntity multipartEntity = new MultipartEntity();
			multipartEntity.addPart("action",new StringBody("addDocumentation"));
			multipartEntity.addPart("mode",new StringBody("-"));
			multipartEntity.addPart("provider",new StringBody("subscriber1"));
			multipartEntity.addPart("apiName",new StringBody("TestDocAPI"));
			multipartEntity.addPart("version",new StringBody("1.0.0"));
			multipartEntity.addPart("docName",new StringBody("attachment"));
			multipartEntity.addPart("docType",new StringBody("how to"));
			multipartEntity.addPart("sourceType",new StringBody("file"));
			multipartEntity.addPart("summary",new StringBody("testing"));
			multipartEntity.addPart("docUrl",new StringBody("-"));
			ContentBody fileBody = new FileBody(document, "text/plain");
			multipartEntity.addPart("docLocation", fileBody);
			docRequest.setEntity(multipartEntity);

			for (Header header : docRequest.getAllHeaders()) {
				System.out.println(header);
			}
			System.out.println();
			System.out.println("Executing request " + docRequest.getRequestLine());
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
