package org.wso2.sample.doc.constants;

/**
 * Created by pubudu on 8/3/14.
 */
public class APIConstantValues {

	public static final String CLIENT_TRUST_STORE_PATH =
			"/home/pubudu/Development/Workspace/Support/BANKOFAMERICADEV-21/Sample/DocumentAPI/src/main/resources/security/client-truststore.jks";
	public static final String HOST_NAME = "<Host_Name>"; // localhost
	public static final String HTTPS_PORT = "<Port>"; // 9443
	public static final String USER_NAME = "<Username>";
	public static final String PASSWORD = "<Password>";
	public static final String KEY_STORE_PASSWORD = "wso2carbon";
	public static final String KEY_STORE_TYPE = "jks";
	public static final String ADD_DOC_API_CALL=
			"https://" + APIConstantValues.HOST_NAME + ":" + APIConstantValues.HTTPS_PORT +
			"/publisher/site/blocks/documentation/ajax/docs.jag";
	public static final String LOGIN_API_CALL =
			"https://" + APIConstantValues.HOST_NAME + ":" + APIConstantValues.HTTPS_PORT +
			"/publisher/site/blocks/user/login/ajax/login.jag?action=login&username=" +
			APIConstantValues.USER_NAME + "&password=" + APIConstantValues.PASSWORD;
	public static final String FILE_PATH = "<FilePath>";
}
