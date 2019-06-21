package testshop2;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
@Configuration
@PropertySource("classpath:jdbc.properties")
public class MyDataSource {
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.user}")
    private String user;
    @Value("${jdbc.password}")
    private String password;
    @Value("${c3.maxPoolSize}")
    private Integer maxPoolSize;
    @Value("${c3.minPoolSize}")
    private Integer minPoolSize;
    @Value("${c3.maxIdleTime}")
    private Integer maxIdleTime;
    @Bean("ds")
    public DataSource dataSource(){
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        dataSource.setJdbcUrl(url);
        try {
            dataSource.setDriverClass(driver);
        } catch (PropertyVetoException e) {
            System.out.println("获取驱动失败!");
        }
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setMaxIdleTime(maxIdleTime);
        dataSource.setMaxPoolSize(maxPoolSize);
        dataSource.setMinPoolSize(minPoolSize);
        return dataSource;
    }

}
