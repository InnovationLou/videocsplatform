package com.innovationlou.videocsplatform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.innovationlou.videocsplatform.entity.Course;
import com.innovationlou.videocsplatform.mapper.CourseMapper;
import com.innovationlou.videocsplatform.service.ICourseService;
import com.innovationlou.videocsplatform.util.ControllerUtil;
import com.innovationlou.videocsplatform.vo.JsonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author innovationLou
 * @since 2020-06-04
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Resource
    CourseMapper courseMapper;

    @Override
    public JsonResult getAllCourses() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return ControllerUtil.getDataResult(courseMapper.selectAll());
        } else {
            return ControllerUtil.getDataResult("未登录");
        }
        //return ControllerUtil.getDataResult(courseMapper.selectAll());
    }
}
