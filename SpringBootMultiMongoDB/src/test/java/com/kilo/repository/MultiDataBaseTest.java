package com.kilo.repository;

import com.kilo.config.SecondaryMongoConfig;
import com.kilo.entity.UserEntity;
import com.kilo.repository.primary.PrimaryRepository;
import com.kilo.repository.secondary.SecondaryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by kilo on 2018/5/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiDataBaseTest {

    @Autowired
    private PrimaryRepository primaryRepository;


    @Autowired
    private SecondaryRepository secondaryRepository;

    @Test
    public void TestSave() {
        System.out.println("************************************************************");
        System.out.println("测试开始");
        System.out.println("************************************************************");

        this.primaryRepository.save(new UserEntity(11l, "小张", "123"));
        this.secondaryRepository.save(new UserEntity(22l, "小王", "456"));

        List<UserEntity> primaries = this.primaryRepository.findAll();
        for (UserEntity primary : primaries) {
            System.out.println(primary.toString());
        }

        List<UserEntity> secondaries = this.secondaryRepository.findAll();
        for (UserEntity secondary : secondaries) {
            System.out.println(secondary.toString());
        }


        System.out.println("************************************************************");
        System.out.println("测试完成");
        System.out.println("************************************************************");
    }


}
