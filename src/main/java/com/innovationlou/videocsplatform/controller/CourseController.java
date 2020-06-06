/**
 * Copyright [2020] [innovation Lou]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.innovationlou.videocsplatform.controller;

import com.innovationlou.videocsplatform.service.ICourseService;
import com.innovationlou.videocsplatform.vo.JsonResult;
import io.swagger.annotations.ApiOperation;
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
    @PostMapping("/buy")
    @RequiresAuthentication
    public JsonResult buyCourse(@RequestHeader("Authorization") String token,
                                Integer courseId){
        return courseService.buyCourse(token,courseId);
    }

    @ApiOperation("获取已购买课程")
    @GetMapping("/bought")
    @RequiresAuthentication
    public JsonResult getBoughtCourses(@RequestHeader("Authorization") String token){
        return courseService.getBoughtCourses(token);
    }

    @ApiOperation("查询某个课程详细信息")
    @GetMapping("/{courseId}")
    public JsonResult getOneCourseInfo(@PathVariable Integer courseId){
        return courseService.getOneCourseInfo(courseId);
    }

    @ApiOperation("某个课程信息包含分P播放信息")
    @GetMapping("/course")
    @RequiresAuthentication
    public JsonResult getCoursePlayInfo(@RequestHeader("Authorization") String token,
                                        Integer courseId){
        return courseService.getCoursePlayInfo(token,courseId);
    }

    @ApiOperation("课程搜索")
    @GetMapping("/search/{keyWord}")
    public JsonResult searchCourse(@PathVariable String keyWord){
        return courseService.searchCourse(keyWord);
    }


}

