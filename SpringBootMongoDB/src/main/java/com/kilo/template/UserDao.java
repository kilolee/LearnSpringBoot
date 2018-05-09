package com.kilo.template;

import com.kilo.entity.UserEntity;
import com.mongodb.WriteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

/**
 * Created by kilo on 2018/5/9.
 */
@Component
public class UserDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 保存对象
     *
     * @param user
     */
    public void saveUser(UserEntity user) {
        mongoTemplate.save(user);
    }

    /**
     * 根据用户名称查询对象
     *
     * @param userName
     * @return
     */
    public UserEntity findUserByUserName(String userName) {
        Query query = new Query(Criteria.where("userName").is(userName));
        UserEntity user = mongoTemplate.findOne(query, UserEntity.class);
        return user;
    }


    /**
     * 更新对象
     *
     * @param user
     * @return
     */
    public int updateUser(UserEntity user) {
        Query query = new Query(Criteria.where("id").is(user.getId()));
        Update update = new Update().set("userName", user.getUserName()).set("passWord", user.getPassWord());
        UpdateResult result = mongoTemplate.updateFirst(query, update, UserEntity.class);
        if (result != null) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 删除对象
     *
     * @param id
     */
    public void deleteUserById(Long id) {
        Query query = Query.query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, UserEntity.class);
    }


}
