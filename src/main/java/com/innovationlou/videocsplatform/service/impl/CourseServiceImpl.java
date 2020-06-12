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
package com.innovationlou.videocsplatform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.innovationlou.videocsplatform.entity.Course;
import com.innovationlou.videocsplatform.entity.Own;
import com.innovationlou.videocsplatform.entity.User;
import com.innovationlou.videocsplatform.entity.Video;
import com.innovationlou.videocsplatform.mapper.CourseMapper;
import com.innovationlou.videocsplatform.mapper.OwnMapper;
import com.innovationlou.videocsplatform.mapper.UserMapper;
import com.innovationlou.videocsplatform.mapper.VideoMapper;
import com.innovationlou.videocsplatform.service.ICourseService;
import com.innovationlou.videocsplatform.util.ControllerUtil;
import com.innovationlou.videocsplatform.util.JWTUtil;
import com.innovationlou.videocsplatform.vo.JsonResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    private static final Logger logger= LogManager.getLogger(CourseServiceImpl.class);

    @Resource
    CourseMapper courseMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    OwnMapper ownMapper;

    @Resource
    VideoMapper videoMapper;

    @Override
    public JsonResult getAllCourses() {
        //Subject subject = SecurityUtils.getSubject();
        return ControllerUtil.getDataResult(courseMapper.selectAll());
    }

    @Override
    @Transactional
    public JsonResult buyCourse(String token, Integer courseId) {
        String username= JWTUtil.getUsername(token);
        User user=userMapper.getUserByUsername(username);
        try{
            Course course =courseMapper.selectById(courseId);
            Float userMoney=user.getMoney();
            Integer userId=user.getId();
            Float coursePrice=course.getCoursePrice();
            if(userMoney>=coursePrice){
                user.setMoney(userMoney-coursePrice);
                ownMapper.insertBuyRecord(userId, courseId);
                userMapper.updateById(user);
                return ControllerUtil.getSuccessResultBySelf("购买成功",course);
                //禁止重复购买,unfixed
            }
            else {
                return ControllerUtil.getFalseResultMsgBySelf("余额不足，请联系管理员充值",user);
            }

        }catch (RuntimeException e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public JsonResult getBoughtCourses(String token) {
        String username= JWTUtil.getUsername(token);
        User user=userMapper.getUserByUsername(username);
        Integer userId=user.getId();
        List<Own> courseList = ownMapper.selectByUserId(userId);
        return ControllerUtil.getDataResult(courseList);
    }

    @Override
    public JsonResult getOneCourseInfo(Integer courseId) {
        return ControllerUtil.getDataResult(courseMapper.selectById(courseId));
    }

    @Override
    public JsonResult searchCourse(String keyWord) {
        return ControllerUtil.getDataResult(courseMapper.selectLikeKey(keyWord));
    }

    @Override
    @Transactional
    public JsonResult getCoursePlayInfo(String token,Integer courseId) {
        String username= JWTUtil.getUsername(token);
        User user=userMapper.getUserByUsername(username);
        Course course=courseMapper.selectById(courseId);
        List<Own> owns=ownMapper.selectByUserId(user.getId());
        ArrayList<Video> videos= (ArrayList<Video>) videoMapper.selectVideoByCourseId(courseId);
        HashMap result=new HashMap();
        result.put("courseInfo",course);

        for (Own o :
                owns) {
            if(o.getCourseId().equals(courseId)){
                //该用户已购买课程，返回包含播放链接的课程信息
                result.put("videoInfo",videos);
                return ControllerUtil.getDataResult(result);
            }
        }
        //用户未购买课程,清空播放信息
        for (Video v:
                videos) {
            v.setPath("");
        }
        result.put("videoInfo",videos);
        return ControllerUtil.getDataResult(result);
    }

    @Override
    public JsonResult getFreeCourses() {
        return ControllerUtil.getDataResult(courseMapper.selectFreeCourses());
    }

    @Override
    public JsonResult getPaidCourses() {
        return ControllerUtil.getDataResult(courseMapper.selectPaidCourses());
    }
}
