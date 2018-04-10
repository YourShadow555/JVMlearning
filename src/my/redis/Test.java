package my.redis;

import redis.clients.jedis.Jedis;

public class Test {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("112.74.47.58",6379);
        System.out.println(jedis.get("lei"));
    }
}
