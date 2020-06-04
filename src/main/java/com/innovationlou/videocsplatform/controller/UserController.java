package com.innovationlou.videocsplatform.controller;


import com.innovationlou.videocsplatform.service.ICourseService;
import com.innovationlou.videocsplatform.service.IUserService;
import com.innovationlou.videocsplatform.vo.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    ICourseService courseService;

    @Resource
    IUserService userService;

    @ApiOperation("登录接口，登陆成功后将token放入headers即可 key:Authorization")
    @PostMapping("/login")
    public JsonResult login(@RequestParam("username") String username,
                            @RequestParam("password") String password){
        return userService.login(username,password);
    }

    @ApiOperation("注册")
    @PutMapping("/reg")
    public JsonResult register(){
        return null;
    }
}

