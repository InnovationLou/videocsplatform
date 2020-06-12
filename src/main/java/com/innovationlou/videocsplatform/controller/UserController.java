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
package com.innovationlou.videocsplatform.controller;


import com.innovationlou.videocsplatform.service.ILoginService;
import com.innovationlou.videocsplatform.service.IUserService;
import com.innovationlou.videocsplatform.vo.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author innovationLou
 * @since 2020-06-04
 */
@RestController
@CrossOrigin
@RequestMapping("/videocsplatform/user")
public class UserController extends BaseController{

    private static final Logger logger= LogManager.getLogger(UserController.class);

    @Resource
    IUserService userService;

    @Resource
    ILoginService loginService;

    @ApiOperation("登录接口，登陆成功后将token放入headers即可 key:Authorization")
    @PostMapping("/login")
    @Transactional
    public JsonResult login(@RequestParam("username") String username,
                            @RequestParam("password") String password){
        logger.info("用户登录:"+username+";登录时间:"+new Date());
        return loginService.login(username,password);
    }

    /**
     * 账户余额由管理员后台充值
     * @param username
     * @param password
     * @param email
     * @return
     */
    @ApiOperation("注册")
    @PutMapping("/reg")
    public JsonResult register(@RequestParam("username") String username,@RequestParam("password")String password,
                               @RequestParam("email") String email){
        return userService.register(username,password,email);
    }

    @ApiOperation("获取个人信息")
    @GetMapping("/{id}")
    @RequiresAuthentication
    public JsonResult info(@RequestHeader("Authorization") String token){
        return userService.info(token);
    }

}

