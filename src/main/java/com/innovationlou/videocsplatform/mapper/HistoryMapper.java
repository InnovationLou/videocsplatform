package com.innovationlou.videocsplatform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.innovationlou.videocsplatform.entity.History;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 播放记录 Mapper 接口
 * </p>
 *
 * @author innovationLou
 * @since 2020-06-04
 */
public interface HistoryMapper extends BaseMapper<History> {

    @Select("SELECT * from history where user_id=${userId}")
    List<History> selectByUserId(@Param("userId") Integer userId);

    @Select("SELECT a.id,a.user_id,a.v_id,a.exit_time,b.v_title from history as a INNER JOIN video as b ON\n" +
            "a.v_id=b.id\n" +
            "where a.user_id=${userId} ORDER BY a.exit_time DESC")
    List<Map<String,Object>> getHistoryByUserId(@Param("userId") Integer userId);

    @Update("UPDATE history set exit_time='${time}' where user_id=${userId} AND v_id=${vId}")
    Integer updateExitTimeByUserIdAndVId(@Param("userId") Integer userId, @Param("vId") Long vId, @Param("time")Date time);

    @Select("SELECT * FROM history WHERE user_id=${userId} AND v_id=${vId}")
    History selectByUserIdAndVId(@Param("userId")Integer userId,@Param("vId")Long vId);
}
