/**
 * Copyright [2020] [innovation Lou]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.innovationlou.videocsplatform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.innovationlou.videocsplatform.entity.User;
import com.innovationlou.videocsplatform.mapper.UserMapper;
import com.innovationlou.videocsplatform.service.IUserService;
import com.innovationlou.videocsplatform.util.ControllerUtil;
import com.innovationlou.videocsplatform.util.JWTUtil;
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
        Float randomMoney=RandomUtils.nextFloat(500,1000);
        User user=userMapper.getUserByUsername(username);
        if(user == null){
            Integer result = userMapper.insertNewUser(username, password, email, randomMoney);
            if(result == 1){
                return ControllerUtil.getDataResult("注册成功,用户名:"+username);
            }
        }else {
            return ControllerUtil.getFalseResultMsgBySelf("用户已存在！");
        }

        return ControllerUtil.getFalseResultMsgBySelf("注册出现异常，请重新注册");
    }

    @Override
    public JsonResult info(String token) {
        String username= JWTUtil.getUsername(token);
        User user=userMapper.getUserByUsername(username);
        //设置密码为空保护隐私
        user.setPassword("");
        return ControllerUtil.getDataResult(user);
    }

}
