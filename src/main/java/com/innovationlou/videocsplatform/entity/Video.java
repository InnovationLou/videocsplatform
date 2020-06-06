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
 * 视频
 * </p>
 *
 * @author innovationLou
 * @since 2020-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Video对象", description="视频")
public class Video implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "视频标题")
    private String vTitle;

    @ApiModelProperty(value = "视频大小")
    private String vSize;

    @ApiModelProperty(value = "视频文件后缀")
    private String vType;

    @ApiModelProperty(value = "路径")
    private String path;

    private Integer courseId;

    @ApiModelProperty(value = "视频分p数")
    private Integer courseP;

    @ApiModelProperty(value = "上传时间")
    private Date uploadTime;


}
