package com.innovationlou.videocsplatform.service;

import com.innovationlou.videocsplatform.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.innovationlou.videocsplatform.vo.JsonResult;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author innovationLou
 * @since 2020-06-04
 */
public interface ICourseService extends IService<Course> {

    JsonResult getAllCourses();

    JsonResult buyCourse(String token, Integer courseId);

    JsonResult getBoughtCourses(String token);

    JsonResult getOneCourseInfo(Integer courseId);

    JsonResult searchCourse(String keyWord);

    JsonResult getCoursePlayInfo(String token,Integer courseId);

    JsonResult getFreeCourses();

    JsonResult getPaidCourses();
}
