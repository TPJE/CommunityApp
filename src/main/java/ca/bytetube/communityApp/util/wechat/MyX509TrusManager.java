package ca.bytetube.communityApp.util.wechat;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Certificate manager for https query
 */
public class MyX509TrusManager implements X509TrustManager {
    /**
     * Verify client side certificate, throw exception if it is not trusted.
     * We only need to execute the default trust manager because we do not need to verify client side.
     * The default trust class is TrustManager in JSSE.
     */
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException{}

    /**
     * Verify server certificate, throw exception if it is not trusted.
     * To trust specific certificate, we can specify any certificate to trust in implement method.
     * To trust any certificate, we can do nothing in the implement method (empty method).
     */
    public void checkServerTrusted(X509Certificate[] chian, String authType) throws CertificateException{}

    /**
     * Return trusted X509 certificate array
     */
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
