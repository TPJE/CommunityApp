package ca.bytetube.communityApp.cache;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolWriper {
    /**
     * Redis pool connection object
     */
    private JedisPool jedisPool;

    public JedisPoolWriper(final JedisPoolConfig poolConfig, final String host, final int port) {
        try {
            jedisPool = new JedisPool(poolConfig, host, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtain Redis pool connection object
     */
    public JedisPool getJedisPool() { return jedisPool; }

    /**
     * Inject Redis connection pool object
     */
    public void setJedisPool(JedisPool jedisPool) { this.jedisPool = jedisPool; }
}
