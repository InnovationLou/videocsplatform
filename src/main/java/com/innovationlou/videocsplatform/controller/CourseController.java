package com.innovationlou.videocsplatform.controller;


import com.innovationlou.videocsplatform.service.ICourseService;
import com.innovationlou.videocsplatform.vo.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author innovationLou
 * @since 2020-06-04
 */
@RestController
@CrossOrigin
@RequestMapping("/videocsplatform/course")
public class CourseController extends BaseController{

    @Resource
    ICourseService courseService;

    @ApiOperation("获取所有课程")
    @GetMapping("/test")
    public JsonResult getAllCourses(){
        //logger.info("test LOGGER");
        return courseService.getAllCourses();
    }

}

