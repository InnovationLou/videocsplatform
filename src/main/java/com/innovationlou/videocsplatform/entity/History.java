package com.innovationlou.videocsplatform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 播放记录
 * </p>
 *
 * @author innovationLou
 * @since 2020-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="History对象", description="播放记录")
public class History implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private Long vId;

    @ApiModelProperty(value = "用户退出视频时的时间")
    private Date exitTime;


}
