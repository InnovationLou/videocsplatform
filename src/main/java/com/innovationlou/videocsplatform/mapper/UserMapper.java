package com.innovationlou.videocsplatform.mapper;

import com.innovationlou.videocsplatform.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author innovationLou
 * @since 2020-06-04
 */
@CacheNamespace
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where username= '${username}'")
    User getUserByUsername(@Param("username") String username);

    @Insert("INSERT INTO `user`(`username`,`password`,`email`,`money`) VALUES('${username}','${password}','${email}','${money}');")
    Integer insertNewUser(@Param("username") String username,@Param("password") String password,
                          @Param("email") String email,@Param("money") Float money);
}
