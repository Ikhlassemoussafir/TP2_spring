package config;

import dao.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@Profile("property")
@PropertySource("classpath:app.properties")
public class PropertyDrivenConfig {

    @Value("${dao.target:daoImpl}")
    private String target;

    @Bean
    @Primary
    public IDao selectedDao() {
        System.out.println("dao.target = " + target);
        if      (target.equals("daoImpl")) return new DaoImpl();
        else if (target.equals("dao2"))    return new DaoImpl2();
        else if (target.equals("daoFile")) return new DaoFile();
        else if (target.equals("daoApi"))  return new DaoApi();
        else throw new IllegalArgumentException("DAO inconnu : " + target);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}