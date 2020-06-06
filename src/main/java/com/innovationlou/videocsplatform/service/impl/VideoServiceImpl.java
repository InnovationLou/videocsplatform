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
import com.innovationlou.videocsplatform.entity.Video;
import com.innovationlou.videocsplatform.mapper.HistoryMapper;
import com.innovationlou.videocsplatform.mapper.UserMapper;
import com.innovationlou.videocsplatform.mapper.VideoMapper;
import com.innovationlou.videocsplatform.service.IVideoService;
import com.innovationlou.videocsplatform.util.ControllerUtil;
import com.innovationlou.videocsplatform.util.JWTUtil;
import com.innovationlou.videocsplatform.vo.JsonResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 视频 服务实现类
 * </p>
 *
 * @author innovationLou
 * @since 2020-06-04
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements IVideoService {

    @Resource
    HistoryMapper historyMapper;

    @Resource
    VideoMapper videoMapper;

    @Resource
    UserMapper userMapper;

    @Override
    public JsonResult getOneVideo(Long videoId) {
        return ControllerUtil.getDataResult(videoMapper.selectById(videoId));
    }

    @Override
    @Transactional
    public JsonResult playHistory(String token) {
        String username= JWTUtil.getUsername(token);
        User user=userMapper.getUserByUsername(username);
        Integer userId=user.getId();
        List<Object> historyResult = historyMapper.getHistoryByUserId(userId);
        return ControllerUtil.getDataResult(historyResult);
    }

    @Override
    public JsonResult recordPlay(String token, Integer vId) {
        String username= JWTUtil.getUsername(token);
        User user=userMapper.getUserByUsername(username);
        Integer userId=user.getId();
        historyMapper.updateExitTimeByUserIdAndVId(userId,vId);
        return ControllerUtil.getDataResult("已退出视频\tvId="+vId);
    }
}
