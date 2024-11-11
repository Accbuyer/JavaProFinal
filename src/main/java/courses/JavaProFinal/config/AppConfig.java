package courses.JavaProFinal.config;

import lombok.Getter;
import lombok.Setter;
import org.flywaydb.core.Flyway;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("spring.datasource")
@Getter
@Setter
public class AppConfig {

    private String url;
    private String username;
    private String password;



    @Bean
    public Flyway flyway() {
        Flyway flyway = Flyway.configure()
                .dataSource(url, username, password)
                .load();
        flyway.migrate();
        return flyway;
    }
}
