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
