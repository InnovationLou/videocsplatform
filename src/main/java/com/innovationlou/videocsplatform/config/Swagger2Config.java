package com.innovationlou.videocsplatform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger的注释
 *
 * @Api：修饰整个类，描述Controller的作用
 *
 * @ApiOperation：描述一个类的一个方法，或者说一个接口
 *
 * @ApiParam：单个参数描述
 *
 * @ApiModel：用对象来接收参数
 *
 * @ApiProperty：用对象接收参数时，描述对象的一个字段
 *
 * @ApiResponse：HTTP响应其中1个描述
 *
 * @ApiResponses：HTTP响应整体描述
 *
 * @ApiIgnore：使用该注解忽略这个API
 *
 * @ApiClass
 *
 * @ApiError
 *
 * @ApiErrors
 *
 * @ApiParamImplicit
 *
 * @ApiParamsImplicit
 */

@EnableSwagger2
@Configuration
public class Swagger2Config {
    @Bean
    public ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("精品课程平台api").version("1.0").build();
    }
    @Bean
    public Docket docket(){
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        ticketPar.name("Authorization").description("token")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build(); //header中的ticket参数非必填，传空也可以
        pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.innovationlou.videocsplatform.controller")).paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }
}
