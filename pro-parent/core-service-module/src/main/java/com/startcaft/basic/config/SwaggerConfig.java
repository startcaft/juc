package com.startcaft.basic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author startcaft
 * @date 2018/3/1
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {

    /**
	 * select()函数返回的一个 ApiSelectotBuilder 实例用来控制哪些接口暴露给Swagger来展现。
	 * 本例中指定扫描Controller所在的包路径来定义（除了被 @ApiIgnore 指定的请求）。
	 */
    @Bean
    public Docket createRestApi(){
        {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(this.apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.startcaft.basic.controller"))
                    .paths(PathSelectors.any())
                    .build();
        }
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/").setCachePeriod(0);
    }


    /**
     * 用来创建Api的基本信息(会在文档页面中展现)
     */
    private ApiInfo apiInfo(){
        {
            return new ApiInfoBuilder()
                    .title("基础核心系统管理服务API")
                    .description("核心服务")
                    .termsOfServiceUrl("http://www.dyt.com")
                    .contact(new Contact("startcaft","http://www.dyt.com","51418996@qq.com"))
                    .version("1.0")
                    .build();
        }
    }
}
