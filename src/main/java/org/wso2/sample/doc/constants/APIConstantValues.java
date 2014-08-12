package org.wso2.sample.doc.constants;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by pubudu on 8/3/14.
 */
public class APIConstantValues {

	public static final String CLIENT_TRUST_STORE_PATH =
			"/home/pubudu/Development/Workspace/Support/BANKOFAMERICADEV-21/Sample/DocumentAPI/src/main/resources/security/client-truststore.jks";
	public static final String HOST_NAME = "localhost"; // localhost
	public static final String HTTPS_PORT = "9453"; // 9443
	public static final String USER_NAME = "admin";
	public static final String PASSWORD = "admin";
	public static final String KEY_STORE_PASSWORD = "wso2carbon";
	public static final String KEY_STORE_TYPE = "jks";
	public static final String ADD_DOC_API_CALL=
			"https://" + APIConstantValues.HOST_NAME + ":" + APIConstantValues.HTTPS_PORT +
			"/publisher/site/blocks/documentation/ajax/docs.jag";
	public static final String LOGIN_API_CALL =
			"https://" + APIConstantValues.HOST_NAME + ":" + APIConstantValues.HTTPS_PORT +
			"/publisher/site/blocks/user/login/ajax/login.jag?action=login&username=" +
			APIConstantValues.USER_NAME + "&password=" + APIConstantValues.PASSWORD;
	//public static final String FILE_PATH = "/home/pubudu/Desktop/IS";  //linux
	public static final Path FILE_PATH = Paths.get("/home/pubudu/Desktop/IS");  //Windows

}
