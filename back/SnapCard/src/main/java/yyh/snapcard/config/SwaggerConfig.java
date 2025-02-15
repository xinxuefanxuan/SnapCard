/*
 * @Author: xinxuefanxuan 2809832579@qq.com
 * @Date: 2025-02-07 22:56:44
 * @LastEditors: xinxuefanxuan 2809832579@qq.com
 * @LastEditTime: 2025-02-09 17:06:56
 * @FilePath: \graduate\back\SnapCard\src\main\java\yyh\snapcard\config\SwaggerConfig.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package yyh.snapcard.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("基于OCR的移动名片管理系统")
                        .description("API接口文档")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("YYH")
                                .email("2809832579@qq.com")));
    }
}
