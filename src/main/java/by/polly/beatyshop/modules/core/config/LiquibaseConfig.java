package by.polly.beatyshop.modules.core.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class LiquibaseConfig {

    @Autowired
    private SpringLiquibase liquibase;

    @Bean
    @DependsOn("entityManagerFactory")
    public SpringLiquibase liquibaseInitializer() {
        return liquibase;
    }
}
