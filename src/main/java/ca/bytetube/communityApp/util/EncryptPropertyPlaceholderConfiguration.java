package ca.bytetube.communityApp.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class EncryptPropertyPlaceholderConfiguration extends PropertyPlaceholderConfigurer {
    // String[] which need to encrypt
    private String[] encryptPropNames = {"jdbc.username", "jdbc.password"};

    /**
     * Transform the key attributes
     */

    @Override
    protected String convertProperty(String propertyName, String propertyValue){
        if(isEncryptProp(propertyName)) {
            // Decode encrypt String
            String decryptValue = DESUtil.getDecryptString(propertyValue);
            return decryptValue;
        }
        return propertyValue;
    }

    /**
     * Check if the attribute is encrypted
     * @param propertyName
     * @return
     */
    private boolean isEncryptProp(String propertyName){
        // If equals to the field required encrypt, then encrypt
        for(String encryptPropertyName : encryptPropNames) {
            if(encryptPropertyName.equals(propertyName))
                return true;
        }

        return false;
    }
}
