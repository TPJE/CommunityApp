package ca.bytetube.communityApp.util.wechat;

import ca.bytetube.communityApp.dto.UserAccessToken;
import ca.bytetube.communityApp.dto.WechatUser;
import ca.bytetube.communityApp.entity.PersonInfo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;

/**
 * Wechat util class
 */
public class WechatUtil {
    private static Logger logger = LoggerFactory.getLogger(WechatUtil.class);

    /**
     * Get UserAccessToken entity
     */

    public static UserAccessToken getUserAccessToken(String code) throws IOException {
        // appId message from test account
        String appId = "wx8e9c4bd828891561";
        logger.debug("appId: " + appId);

        // appSecret message from test account
        String appSecret = "c70d18897649f2daf10b280b088a1b49";
        logger.debug("secret: " + appSecret);

        // Append Wechat request url by input code
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=?" + appId + "&secret=" + appSecret + "&code=" + code + "&grant_type=authorization_code";

        // Send token json request to relevant url
        String tokenStr = httpsRequest(url, "GET", null);
        logger.debug("userAccessToken: " + tokenStr);
        UserAccessToken token = new UserAccessToken();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Convert Json into relevant object
            token = objectMapper.readValue(tokenStr, UserAccessToken.class);
        } catch (JsonParseException e) {
            logger.error("Failed to get user access token: " + e.getMessage());
            e.printStackTrace();
        } catch (JsonMappingException e) {
            logger.error("Failed to get user access token: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("Failed to get user access token: " + e.getMessage());
            e.printStackTrace();
        }

        if (token == null) {
            logger.error("Failed to get user access token, token == null");
            return null;
        }

        return token;
    }

    /**
     * Obtain WechatUser entity
     */

    public static WechatUser getUserInfo(String accessToken, String openId) {
        // Append url for Wechat request server port by input accessToken and openId
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN";

        // Get user info Json and String by visit the url
        String userStr = httpsRequest(url, "GET", null);
        logger.debug("User info: " + userStr);
        WechatUser user = new WechatUser();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Convert Json into relevant object
            user = objectMapper.readValue(userStr, WechatUser.class);
        } catch (JsonParseException e) {
            logger.error("Failed to get user info: " + e.getMessage());
            e.printStackTrace();
        } catch (JsonMappingException e) {
            logger.error("Failed to get user info: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("Failed to get user info: " + e.getMessage());
            e.printStackTrace();
        }

        if (user == null) {
            logger.error("Failed to get user info, user == null ");
            return null;
        }

        return user;
    }

    /**
     * Convert WechatUser info into PersonInfo and return PersonInfo entity class
     */

    public static PersonInfo getPersonInfoFromRequest(WechatUser user) {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setName(user.getNickName());
        personInfo.setGender(user.getSex());
        personInfo.setProfileImg(user.getHeadImgUrl());
        personInfo.setEnableStatus(1);
        return personInfo;
    }

    /**
     * HttpsRequest method
     */

    public static String httpsRequest(String requestUrl, String requstMethod, String outputStr) {
        StringBuffer buffer = new StringBuffer();

        try {
            // Create SSLContext object and initialize by the specific trust manager
            TrustManager[] trustManagers = {new MyX509TrusManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSEE");
            sslContext.init(null, trustManagers, new java.security.SecureRandom());

            // Get SSLSocketFactory object from the SSLContext above
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setSSLSocketFactory(sslSocketFactory);

            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setUseCaches(false);

            // Set request method (GET/ POST)
            httpsURLConnection.setRequestMethod(requstMethod);

            if("GET".equals(requstMethod)) httpsURLConnection.connect();

            // If there is input data
            if(outputStr != null) {
                OutputStream outputStream = httpsURLConnection.getOutputStream();
                // Check charset type to prevent chinese garbled characters
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // Convert result array buffer into string
            InputStream inputStream = httpsURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while((str = bufferedReader.readLine()) != null) buffer.append(str);

            bufferedReader.close();
            inputStreamReader.close();

            // Release resource
            inputStream.close();
            inputStream = null;
            httpsURLConnection.disconnect();
            logger.debug("https buffer: " + buffer.toString());
        } catch (ConnectException ce) {
            logger.error("Weixin server connection timeout.");
        } catch (Exception e ){
            logger.error("https request error: " + e);
        }

        return buffer.toString();
    }
}
