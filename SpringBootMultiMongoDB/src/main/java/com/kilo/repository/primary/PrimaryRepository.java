package com.kilo.repository.primary;

import com.kilo.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by kilo on 2018/5/10.
 */
public interface PrimaryRepository extends MongoRepository<UserEntity,Long>{
}
