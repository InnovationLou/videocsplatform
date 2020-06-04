package com.innovationlou.videocsplatform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.innovationlou.videocsplatform.entity.User;
import com.innovationlou.videocsplatform.mapper.UserMapper;
import com.innovationlou.videocsplatform.service.IUserService;
import com.innovationlou.videocsplatform.util.ControllerUtil;
import com.innovationlou.videocsplatform.util.JWTUtil;
import com.innovationlou.videocsplatform.vo.JsonResult;
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

    @Override
    public JsonResult login(String username, String password) {
        User user=userMapper.getUserByUsername(username);
        if(user == null){
            return ControllerUtil.customResult(401,"用户不存在",null);
        }
        if(password.equals(user.getPassword())){
            return ControllerUtil.customResult(200,"login success", JWTUtil.sign(username, password));
        }
        else {
            return ControllerUtil.customResult(401,"login failed", null);
        }
    }

}
