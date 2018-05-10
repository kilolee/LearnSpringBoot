package com.kilo.repository.secondary;

import com.kilo.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by kilo on 2018/5/10.
 */
public interface SecondaryRepository extends MongoRepository<UserEntity,Long> {
}
