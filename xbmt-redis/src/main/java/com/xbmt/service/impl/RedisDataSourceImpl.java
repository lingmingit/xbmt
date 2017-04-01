package com.xbmt.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import com.xbmt.service.RedisDataSource;


/**
 * redis 集群操作接口实现类
 * @author LingMin 
 * @date 2016-08-25<br>
 * @version 1.0<br>
 */
@Service("redisDataSource")
public class RedisDataSourceImpl implements RedisDataSource {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    /***
     * 获取 redis的某一个客户端
     */
    public ShardedJedis getRedisClient() {
        try {
            ShardedJedis shardJedis = shardedJedisPool.getResource();
            return shardJedis;
        } catch (Exception e) {
            log.error("getRedisClent error", e);
        }
        return null;
    }

    public void returnResource(ShardedJedis shardedJedis) {
        shardedJedisPool.close();
        //shardedJedisPool.returnResource(shardedJedis);
    }

    public void returnResource(ShardedJedis shardedJedis, boolean broken) {
        if (broken) {
            shardedJedisPool.close();
            //shardedJedisPool.returnBrokenResource(shardedJedis);
        } else {
            shardedJedisPool.close();
            //shardedJedisPool.returnResource(shardedJedis);
        }
    }
}
