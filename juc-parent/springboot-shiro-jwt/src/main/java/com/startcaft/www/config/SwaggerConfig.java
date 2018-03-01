package com.startcaft.www.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
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
 * @author startcaft
 * Created by startcaft on 2018/2/28.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {

    /**
     * 这个地方要重新注入一下资源文件，不然不会注入资源的，也没有注入requestHandlerMappping,相当于xml配置的
     *  <mvc:resources location="classpath:/META-INF/resources/" mapping="swagger-ui.html"/>
     *  <mvc:resources location="classpath:/META-INF/resources/webjars/" mapping="/webjars/**"/>
     *  不知道为什么，这也是spring boot的一个缺点（菜鸟觉得的）
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars*")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /*
	 * select()函数返回的一个 ApiSelectotBuilder 实例用来控制哪些接口暴露给Swagger来展现。
	 * 本例中指定扫描Controller所在的包路径来定义（除了被 @ApiIgnore 指定的请求）。
	 */
    @Bean
    public Docket createRestApi(){
        {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(this.apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.startcaft.www.controller"))
                    .paths(PathSelectors.any())
                    .build();
        }
    }

    /**
     *用来创建Api的基本信息(会在文档页面中展现)
     * @return
     */
    private ApiInfo apiInfo(){
    {
        return new ApiInfoBuilder()
            .title("API服务")
            .description("Springboot + Shiro + JWT 无状态的API服务器")
            .termsOfServiceUrl("http://www.dyt.com")
            .contact(new Contact("startcaft","http://www/dyt.com","51418996@qq.com"))
            .version("1.0")
            .build();
    }
    }
}
