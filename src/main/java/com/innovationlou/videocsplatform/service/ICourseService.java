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
}
