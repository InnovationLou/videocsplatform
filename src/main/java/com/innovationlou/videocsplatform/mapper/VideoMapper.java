package com.innovationlou.videocsplatform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.innovationlou.videocsplatform.entity.Video;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 视频 Mapper 接口
 * </p>
 *
 * @author innovationLou
 * @since 2020-06-04
 */
public interface VideoMapper extends BaseMapper<Video> {

    @Select("SELECT * FROM video WHERE course_id =${courseId} order by course_P ASC")
    List<Video> selectVideoByCourseId(@Param("courseId") Integer courseId);

}
