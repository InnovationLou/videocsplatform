package com.innovationlou.videocsplatform.service;

import com.innovationlou.videocsplatform.vo.JsonResult;

public interface ILoginService {
    JsonResult login(String username, String password);
}
