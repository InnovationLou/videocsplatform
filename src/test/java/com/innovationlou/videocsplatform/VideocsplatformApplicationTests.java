package com.innovationlou.videocsplatform;

import com.innovationlou.videocsplatform.entity.User;
import com.innovationlou.videocsplatform.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class VideocsplatformApplicationTests {

    private static final Logger logger= LoggerFactory.getLogger(VideocsplatformApplication.class);

    @Resource
    UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void testGetUser(){
        User user=userMapper.getUserByUsername("user01");
        logger.info(user.toString());
    }
}
