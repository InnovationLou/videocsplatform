package com.innovationlou.videocsplatform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.innovationlou.videocsplatform.entity.Own;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 购买表 Mapper 接口
 * </p>
 *
 * @author innovationLou
 * @since 2020-06-04
 */
public interface OwnMapper extends BaseMapper<Own> {

    @Insert("INSERT INTO own(user_id,course_id) VALUES(${userId},${courseId})")
    Integer insertBuyRecord(@Param("userId") Integer userId,@Param("courseId") Integer courseId);

    @Select("SELECT * FROM own where user_id=${userId}")
    List<Own> selectByUserId(@Param("userId") Integer userId);

}
