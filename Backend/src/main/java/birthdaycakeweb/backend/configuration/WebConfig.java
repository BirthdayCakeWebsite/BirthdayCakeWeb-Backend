package birthdaycakeweb.backend.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${app.allowed.origins:}")
    private String allowedOrigins; // lấy từ ENV APP_ALLOWED_ORIGINS

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String[] origins = StringUtils.tokenizeToStringArray(allowedOrigins, ",");
        registry.addMapping("/**")
                .allowedOrigins(origins != null ? origins : new String[0])
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS","PATCH")
                .allowedHeaders("Content-Type","Authorization","Accept","Origin","X-Requested-With")
                .allowCredentials(false)  // GitHub Pages + fetch không cần cookie
                .maxAge(3600);
    }
}