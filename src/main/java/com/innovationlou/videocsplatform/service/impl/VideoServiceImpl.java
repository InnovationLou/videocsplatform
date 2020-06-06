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
import com.innovationlou.videocsplatform.entity.History;
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
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    @Transactional
    public JsonResult getOneVideo(String token,Long videoId) {
        String username= JWTUtil.getUsername(token);
        User user=userMapper.getUserByUsername(username);
        Integer userId=user.getId();
        History history = historyMapper.selectByUserIdAndVId(userId, videoId);
        if(history == null){
            history=new History();
            history.setUserId(userId);
            history.setVId(videoId);
            history.setExitTime(new Date());
            historyMapper.insert(history);
        }
        history.setExitTime(new Date());
        historyMapper.updateById(history);
        return ControllerUtil.getDataResult(videoMapper.selectById(videoId));
    }

    @Override
    @Transactional
    public JsonResult playHistory(String token) {
        String username= JWTUtil.getUsername(token);
        User user=userMapper.getUserByUsername(username);
        Integer userId=user.getId();
        List<Map<String,Object>> historyResult = historyMapper.getHistoryByUserId(userId);
        return ControllerUtil.getDataResult(historyResult);
    }

    @Override
    @Transactional
    public JsonResult recordPlay(String token, Long vId) {
        String username= JWTUtil.getUsername(token);
        User user=userMapper.getUserByUsername(username);
        Integer userId=user.getId();
        //java.sql.Date time=new java.sql.Date(System.currentTimeMillis());
        Timestamp time=new Timestamp(new Date().getTime());
        historyMapper.updateExitTimeByUserIdAndVId(userId,vId,time);
        return ControllerUtil.getDataResult("已退出视频\tvId="+vId);
    }
}
