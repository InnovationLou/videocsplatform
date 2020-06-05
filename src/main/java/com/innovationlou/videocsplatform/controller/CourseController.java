package com.innovationlou.videocsplatform.controller;


import com.innovationlou.videocsplatform.service.ICourseService;
import com.innovationlou.videocsplatform.vo.JsonResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.*;

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

    private static final Logger logger= LogManager.getLogger(CourseController.class);

    @Resource
    ICourseService courseService;

    @ApiOperation("获取所有课程")
    @GetMapping("/all")
    public JsonResult getAllCourses(){
        //logger.info("test LOGGER");
        return courseService.getAllCourses();
    }

    @ApiOperation("购买课程")
    @PutMapping("/buy")
    @RequiresAuthentication
    public JsonResult buyCourse(@RequestHeader("Authorization") String token,
                                @ApiParam("课程id") Integer courseId){
        return null;
    }

    @ApiOperation("获取已购买课程")
    @GetMapping("/bought")
    @RequiresAuthentication
    public JsonResult getBoughtCourses(@RequestHeader("Authorization") String token){
        return null;
    }

    @ApiOperation("查询某个课程详细信息")
    @GetMapping("/{courseId}")
    public JsonResult getOneCourseInfo(@PathVariable Integer courseId){
        return null;
    }

    @ApiOperation("课程搜索")
    @GetMapping("/search/{keyWord}")
    public JsonResult searchCourse(@PathVariable String keyWord){
        return null;
    }


}

