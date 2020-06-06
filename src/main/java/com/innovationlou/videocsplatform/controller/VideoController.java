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


import com.innovationlou.videocsplatform.service.IVideoService;
import com.innovationlou.videocsplatform.vo.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 视频 前端控制器
 * </p>
 *
 * @author innovationLou
 * @since 2020-06-04
 */
@RestController
@CrossOrigin
@RequestMapping("/videocsplatform/video")
public class VideoController extends BaseController{

    private static final Logger logger= LogManager.getLogger(VideoController.class);

    @Resource
    IVideoService videoService;

    @ApiOperation("获取视频信息，在线播放视频")
    @GetMapping("/{videoId}")
    @RequiresAuthentication
    public JsonResult getOneVideo(@RequestHeader("Authorization") String token,
                                  @PathVariable Long videoId){
        return videoService.getOneVideo(token,videoId);
    }

    @ApiOperation("获取播放记录")
    @GetMapping("/history")
    @RequiresAuthentication
    public JsonResult playHistory(@RequestHeader("Authorization") String token){
        return videoService.playHistory(token);
    }

    @ApiOperation("播放退出")
    @PutMapping("/record")
    @RequiresAuthentication
    public JsonResult recordPlay(@RequestHeader("Authorization") String token,
                                 Long vId){
        return videoService.recordPlay(token,vId);
    }
}

