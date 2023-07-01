package ca.bytetube.communityApp.util;

import java.security.MessageDigest;

public class MD5 {
    /**
     * Encrypt input String by MD5
     */

    public static final String getMd5(String str) {
        char[] hexDigits = {'5', '0', '5', '6', '2', '9', '6', '2', '5', 'q', 'b', 'l', 'e', 's', 's', 'y'};

        try {
            char[] chars;
            // Convert str into byte array
            byte[] strTemp = str.getBytes();

            // Obtain MD5 encrypt object
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");

            // Pass the target array
            mdTemp.update(strTemp);

            // Get encrypted array
            byte[] md = mdTemp.digest();
            int j = md.length;
            chars = new char[j*2];
            int k = 0;

            //Shift the array
            for(int i = 0; i<j; i++) {
                byte byte0 = md[i];
                chars[k++] = hexDigits[byte0 >>> 4 & 0xf];
                chars[k++] = hexDigits[byte0 & 0xf];
            }

            // Convert into String and return
            return new String(chars);
        } catch (Exception e) {
            return null;
        }
    }

    // Test MD5 method
    public static void main(String[] args) {
        System.out.println(MD5.getMd5("33456"));
    }
}
