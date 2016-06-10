package com.yinuo.api;

import com.yinuo.api.model.type.Authority;
import com.yinuo.api.model.vo.UserRequest;
import com.yinuo.api.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.TimeZone;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-10
 */
@SpringBootApplication
@EnableSwagger2
public class Application {
    @Value("${backend.admin.username}")
    String adminUsername;

    @Value("${backend.admin.password}")
    String adminPassword;

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(final UserService userService) {

        return arg -> {
            if (!userService.exists(adminUsername)) {
                userService.add(new UserRequest(adminUsername, adminPassword, Authority.ROLE_SYSTEM_ADMIN.name()));
            }
        };

    }

    @Bean
    public Docket yinuoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("yinuo")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/v1.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("宜诺科技API服务")
                .description("宜诺科技API服务")
                .contact(new Contact("吕超", "http://api.yinuo-tech.com", "billcc_lv@yinuo-tech.com"))
                .version("2.0")
                .build();
    }
}
