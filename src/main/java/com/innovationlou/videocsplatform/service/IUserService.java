package com.innovationlou.videocsplatform.service;

import com.innovationlou.videocsplatform.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.innovationlou.videocsplatform.vo.JsonResult;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author innovationLou
 * @since 2020-06-04
 */
public interface IUserService extends IService<User> {
    User getUserByName(String username);

    JsonResult login(String username, String password);

}
