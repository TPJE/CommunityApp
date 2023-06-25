package ca.bytetube.communityApp.config.redis;

import ca.bytetube.communityApp.cache.JedisPoolWriper;
import ca.bytetube.communityApp.cache.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Config from spring-redis.xml
 * redis.hostname=47.90.179.171
 * redis.port=6379
 * redis.database=0
 * redis.pool.maxActive=100
 * redis.pool.maxIdle=20
 * redis.pool.maxWait=3000
 * redis.pool.testOnBorrow=true
 */
public class RedisConfiguration {
    @Value("redis.hostname")
    private String hostname;

    @Value("redis.port")
    private int port;

    @Value("redis.pool.maxActive")
    private int maxTotal;

    @Value("redis.pool.maxIdle")
    private int maxIdle;

    @Value("redis.pool.maxWait")
    private int maxWaitMillis;

    @Value("redis.pool.testOnBorrow")
    private boolean testOnBorrow;

    @Autowired
    private JedisPoolConfig jedisPoolConfig;
    @Autowired
    private JedisPoolWriper jedisWritePool;
    @Autowired
    private JedisUtil jedisUtil;

    /**
     * Create Redis connection pool config
     */
    @Bean(name = "jedisPoolConfig")
    public JedisPoolConfig createJedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // Set how many jedis instances can be allocated to a pool
        jedisPoolConfig.setMaxTotal(maxTotal);

        // Set the maximum idle connections to a pool, the maxIdle is 20 in this case.
        // Means there will be 20 idle connections can stand by even there is no database connection.
        jedisPoolConfig.setMaxIdle(maxIdle);

        // Set maximum waiting time if there is no connection,
        // Throw exception if exceed the maxWaitTime (unit is millisecond)
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);

        // Check the validation while getting connection
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);

        return jedisPoolConfig;
    }

    /**
     * Generate and config Redis connection pool
     */
    @Bean(name = "jedisWritePool")
    public JedisPoolWriper createJedisPoolWriper(){
        JedisPoolWriper jedisPoolWriper = new JedisPoolWriper(jedisPoolConfig, hostname, port);
        return jedisPoolWriper;
    }

    /**
     * Create Redis util class and packing Redis connection for related operation.
     */
    @Bean(name = "jedisUtil")
    public JedisUtil createJedisUtil(){
        JedisUtil jedisUtil = new JedisUtil();
        jedisUtil.setJedisPool(jedisWritePool);

        return jedisUtil;
    }

    /**
     * Redis key operation
     */
    @Bean(name = "jedisKey")
    public JedisUtil.Keys createJedisKeys() {
        JedisUtil.Keys jedisKeys = jedisUtil.new Keys();
        return jedisKeys;
    }

    /**
     * Redis String operation
     */
    @Bean(name = "jedisStrings")
    public JedisUtil.Strings createJedisStrings() {
        JedisUtil.Strings jedisStrings = jedisUtil.new Strings();
        return jedisStrings;
    }
}
