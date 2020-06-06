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
