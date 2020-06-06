package com.innovationlou.videocsplatform.service;

import com.innovationlou.videocsplatform.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import com.innovationlou.videocsplatform.vo.JsonResult;

/**
 * <p>
 * 视频 服务类
 * </p>
 *
 * @author innovationLou
 * @since 2020-06-04
 */
public interface IVideoService extends IService<Video> {

    JsonResult getOneVideo(String token,Long videoId);

    JsonResult playHistory(String token);

    JsonResult recordPlay(String token, Long vId);
}
