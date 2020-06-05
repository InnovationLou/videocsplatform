package com.innovationlou.videocsplatform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.innovationlou.videocsplatform.entity.User;
import com.innovationlou.videocsplatform.mapper.UserMapper;
import com.innovationlou.videocsplatform.service.IUserService;
import com.innovationlou.videocsplatform.util.ControllerUtil;
import com.innovationlou.videocsplatform.vo.JsonResult;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public JsonResult register(String username, String password, String email) {
        Float randomMoney=RandomUtils.nextFloat(50,1000);
        Integer result = userMapper.insertNewUser(username, password, email, randomMoney);
        if(result == 1){
            return ControllerUtil.getDataResult("注册成功,用户名:"+username);
        }
        return ControllerUtil.getFalseResultMsgBySelf("注册出现异常，请重新注册");
    }

}
