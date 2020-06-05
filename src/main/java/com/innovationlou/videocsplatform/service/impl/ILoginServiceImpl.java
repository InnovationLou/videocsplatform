package com.innovationlou.videocsplatform.service.impl;

import com.innovationlou.videocsplatform.entity.User;
import com.innovationlou.videocsplatform.mapper.UserMapper;
import com.innovationlou.videocsplatform.service.ILoginService;
import com.innovationlou.videocsplatform.util.ControllerUtil;
import com.innovationlou.videocsplatform.util.JWTUtil;
import com.innovationlou.videocsplatform.vo.JsonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ILoginServiceImpl implements ILoginService {

    @Resource
    UserMapper userMapper;

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
