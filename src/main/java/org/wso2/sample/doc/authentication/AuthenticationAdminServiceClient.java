package org.wso2.sample.doc.authentication;

/**
 * Created by pubudu on 8/3/14.
 */
public class AuthenticationAdminServiceClient {

	public static void setSystemProperties(String keyStorePath, String keyStoreType,
	                                       String keyStorePassword) {
		System.setProperty("javax.net.ssl.trustStore", keyStorePath);
		System.setProperty("javax.net.ssl.trustStorePassword", keyStorePassword);
		System.setProperty("javax.net.ssl.trustStoreType", keyStoreType);
	}
}
