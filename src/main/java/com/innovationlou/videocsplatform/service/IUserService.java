package com.innovationlou.videocsplatform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.innovationlou.videocsplatform.entity.User;

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

}
