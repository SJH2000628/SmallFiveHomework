package io.sjh.jcartstoreback.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ProductScheduler {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Scheduled(cron = "0 0 01 * * ?")
//    @Scheduled(fixedDelay = 30*1000)
    public void removeHotProduct(){
        String key = "HotProducts";
        Boolean delete = redisTemplate.delete(key);

    }
}
