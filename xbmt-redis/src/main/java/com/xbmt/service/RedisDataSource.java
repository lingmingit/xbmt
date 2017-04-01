package com.xbmt.service;

import redis.clients.jedis.ShardedJedis;


/**
 * redis 集群操作接口类
 * @author LingMin 
 * @date 2016-08-25<br>
 * @version 1.0<br>
 */
public interface RedisDataSource {

	public abstract ShardedJedis getRedisClient();
    public void returnResource(ShardedJedis shardedJedis);
    public void returnResource(ShardedJedis shardedJedis,boolean broken);
}
