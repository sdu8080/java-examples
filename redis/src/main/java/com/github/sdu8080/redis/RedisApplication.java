package com.github.sdu8080.redis;

import java.util.Arrays;

import com.github.sdu8080.redis.model.Person;
import com.github.sdu8080.redis.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import lombok.extern.slf4j.Slf4j;

/**
 * example to use Redis as both cacheManager and repository
 * @author sdu
 *
 */
@SpringBootApplication
@EnableRedisRepositories
public @Slf4j class RedisApplication {

  @Autowired
  ApplicationContext context;

  @Autowired
  PersonRepository repo;

  public static void main(String[] args) {
    SpringApplication.run(RedisApplication.class, args);
  }
  
  
  @Bean
  public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
    RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);

    // Number of seconds before expiration. Defaults to unlimited (0)
    cacheManager.setDefaultExpiration(30);
    return cacheManager;
  }


  
  @Bean
  public CommandLineRunner init(@Qualifier("stringRedisTemplate") StringRedisTemplate template, 
      @Qualifier("cacheManager") CacheManager cacheManager) {

    return new CommandLineRunner() {
      public void run(String... strings) throws Exception {

        Arrays.asList(context.getBeanDefinitionNames()).stream().forEach(s -> {
          log.info("bean:{}", s);
        });

        template.opsForValue().set("key1", "value1");

        String value = template.opsForValue().get("key1");

        log.info(value);


        repo.save(new Person(100L, "Sean Du"));
        log.info("p:{}", repo.findOne(100L));
        
        Cache c = cacheManager.getCache("cache");
        c.put("key1", "my value");
        
        log.info("cache value:{}", c.get("key1").get());
        
        Thread.sleep(3000);
        
        log.info("cache value:{}", c.get("key1"));
        
      }
    };
  }
}
