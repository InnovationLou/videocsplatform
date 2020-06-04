package com.innovationlou.videocsplatform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 课程
 * </p>
 *
 * @author innovationLou
 * @since 2020-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Course对象", description="课程")
public class Course implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "课程名")
    private String courseName;

    @ApiModelProperty(value = "课程描述")
    private String courseDesc;

    @ApiModelProperty(value = "课程价格")
    private Float coursePrice;

    @ApiModelProperty(value = "课程类型（科技，人文，自然...）")
    private String courseType;


}
