package ca.bytetube.communityApp.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;

/**
 * DES is a symmetric encryption algorithm.
 * The so-called symmetric encryption algorithm is an algorithm that uses the same key for encryption and decryption.
 */
public class DESUtil {
    private static Key key;
    // Set key
    private static String KEY_STR = "myKey";
    private static String CHARSETNAME = "UTF-8";
    private static String ALGORITHM = "DES";

    static {
        try {
            // Generate DES algorithm object
            KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
            // Use SHA1 security policy
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            // Set key seed
            secureRandom.setSeed(KEY_STR.getBytes());
            // Initialize algorithm object by SHA1
            generator.init(secureRandom);
            // Generate key object
            key = generator.generateKey();
            generator= null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Obtain the encrypted data
     * @keyword encoder
     */
    public static String getEncryptString(String str) {
        // Based on BASE64 ENCODER, receive byte[] and convert to String
        BASE64Encoder base64Encoder = new BASE64Encoder();
        try {
            // Use UTF-8
            byte[] bytes = str.getBytes(CHARSETNAME);
            // Get encrypt object
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            // Encrypt
            byte[] doFinal = cipher.doFinal(bytes);
            // Convert byte[] to encoded String and return it
            return base64Encoder.encode(doFinal);
        } catch (Exception e ) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Obtain decrypted data
     * @keyword decoder
     */
    public static String getDecryptString(String str) {
        // Based on BASE64 DECODER, receive byte[] and convert to String
        BASE64Decoder base64Decoder = new BASE64Decoder();
        try {
            // Convert string to byte[] by decoder
            byte[] bytes = base64Decoder.decodeBuffer(str);
            // Obtain encrypt object
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            // Initialize decrypt data
            cipher.init(Cipher.DECRYPT_MODE, key);
            // Decoder
            byte[] doFinal = cipher.doFinal(bytes);
            // Return decrypted data
            return new String(doFinal, CHARSETNAME);
        } catch (Exception e ){
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(getEncryptString("root")); //WnplV/ietfQ=
        System.out.println(getEncryptString(""));
    }
}
