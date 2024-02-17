package io.github.sdu8080.redis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;


@AllArgsConstructor
@NoArgsConstructor
@Data
@RedisHash("persons")
public class Person {
  @Id
  private Long id;
  private String name;
}
