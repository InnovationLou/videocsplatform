package com.innovationlou.videocsplatform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.innovationlou.videocsplatform.entity.User;
import com.innovationlou.videocsplatform.mapper.UserMapper;
import com.innovationlou.videocsplatform.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author innovationLou
 * @since 2020-06-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User getUserByName(String username) {
        return userMapper.getUserByUsername(username);
    }

}
