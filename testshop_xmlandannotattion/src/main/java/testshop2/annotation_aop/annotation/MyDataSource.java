package testshop2.annotation_aop.annotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:jdbc.properties")
public class MyDataSource extends DriverManagerDataSource {
    public MyDataSource(@Value("${jc.driver}") String driver,
                        @Value("${jc.url}") String url,
                        @Value("${jc.username}") String username,
                        @Value("${jc.password}") String password) {
        setDriverClassName(driver);
        setUrl(url);
        setUsername(username);
        setPassword(password);
    }
}
