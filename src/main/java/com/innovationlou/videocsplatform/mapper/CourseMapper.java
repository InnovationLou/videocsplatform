package com.innovationlou.videocsplatform.mapper;

import com.innovationlou.videocsplatform.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author innovationLou
 * @since 2020-06-04
 */
public interface CourseMapper extends BaseMapper<Course> {

    @Select("select * from course")
    List<Course> selectAll();

    @Select("select * from course where course_name like '%${key}%' OR course_desc like '%${key}%'")
    List<Course> selectLikeKey(@Param("key") String key);
}
