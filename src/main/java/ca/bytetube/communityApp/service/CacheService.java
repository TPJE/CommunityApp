package ca.bytetube.communityApp.service;

public interface CacheService {
    /**
     * According to prefix of key, delete all matched key value, ex: shopcategory, then DELETE shopcategory_allfirstlevel etc.
     * Empty all key_value which begin with "shopcategory".
     */

    void removeFromCache(String keyPrefix);
}
