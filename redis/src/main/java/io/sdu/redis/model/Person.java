package io.sdu.redis.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RedisHash("persons")
public @Data @AllArgsConstructor @NoArgsConstructor class Person {
  @Id private Long id;
  private String name;
}
