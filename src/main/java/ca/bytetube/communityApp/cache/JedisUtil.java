package ca.bytetube.communityApp.cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.util.SafeEncoder;

import java.security.Key;
import java.util.Set;

public class JedisUtil {
    /**
     * Cache alive period
     */
    private final int expire = 60000;

    /**
     * Control key method
     */
    public Key KEYS;

    /**
     * Operations for String
     */
    public String STRINGS;

    /**
     * Redis connection pool object
     */
    private JedisPool jedisPool;

    /**
     * Obtain Redis connection pool
     *
     * @return
     */
    public JedisPool getJedisPool() {
        return jedisPool;
    }

    /**
     * Set Redis connection pool
     *
     * @param jedisPoolWriper
     */
    public void setJedisPool(JedisPoolWriper jedisPoolWriper) {
        this.jedisPool = jedisPoolWriper.getJedisPool();
    }

    /**
     * Get jedis object from jedis pool
     *
     * @return
     */
    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    //    <----------------------------------- KEYS ----------------------------------->
    public class Keys {
        /**
         * Clean all keys
         */
        public String fluchAll() {
            Jedis jedis = getJedis();
            String status = jedis.flushAll();
            jedis.close();

            return status;
        }

        /**
         * Delete the records corresponding to keys, can be multiple keys
         */
        public long del(String... keys) {
            Jedis jedis = getJedis();
            long count = jedis.del(keys);
            jedis.close();

            return count;
        }

        /**
         * Check if key exists
         */
        public boolean exists(String key) {
            Jedis jedis = getJedis();
            boolean exist = jedis.exists(key);
            jedis.close();

            return exist;
        }

        /**
         * Find all keys matching the given pattern
         * Syntax of key: * -> multiple keys, ? -> single key
         */
        public Set<String> keys(String pattern) {
            Jedis jedis = getJedis();
            Set<String> set = jedis.keys(pattern);
            jedis.close();

            return set;
        }

    }

    //    <----------------------------------- STRINGS ----------------------------------->
    public class Strings {
        /**
         * Get records by key
         */
        public String get(String key) {
            Jedis jedis = getJedis();
            String value = jedis.get(key);
            jedis.close();

            return value;
        }

        /**
         * Insert record
         * Replace the value if exists
         */
        public String set(String key, String value) {
            return set(SafeEncoder.encode(key), SafeEncoder.encode(value));
        }

        public String set(byte[] key, byte[] value) {
            Jedis jedis = getJedis();
            String status = jedis.set(key, value);
            jedis.close();

            return status;
        }
    }
}
