package courses.JavaProFinal.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.Setter;
import org.flywaydb.core.Flyway;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties("spring.datasource")
@Getter
@Setter
public class AppConfig {

    private String url;
    private String username;
    private String password;

    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public Flyway flyway() {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource())
                .load();
        flyway.migrate();
        return flyway;
    }
}
