package com.innovationlou.videocsplatform.service.impl;

import com.innovationlou.videocsplatform.entity.Video;
import com.innovationlou.videocsplatform.mapper.VideoMapper;
import com.innovationlou.videocsplatform.service.IVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
