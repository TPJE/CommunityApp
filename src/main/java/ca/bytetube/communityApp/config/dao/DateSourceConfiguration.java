package ca.bytetube.communityApp.config.dao;

import ca.bytetube.communityApp.util.DESUtil;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyVetoException;

/**
 * Config datasource into ioc container
 */
@Configuration("ca.bytetube.communityApp.dao")
public class DateSourceConfiguration {
    @Value("${jdbc.driver}")
    private String jdbcDriver;

    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String jdbcUserName;

    @Value("${jdbc.password}")
    private String jdbcPassword;

    /**
     * Generate the bean dataSource related to spring-dao.xml
     */
    @Bean(name = "dataSrouce")
    public ComboPooledDataSource createDataSource() throws PropertyVetoException {
        // Generate datasource instance
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        // Config the info by the @Value from config file:
        // Drive
        dataSource.setDriverClass(jdbcDriver);

        // Url
        dataSource.setJdbcUrl(jdbcUrl);

        // Username
        dataSource.setUser(DESUtil.getDecryptString(jdbcUserName));

        // Password
        dataSource.setPassword(DESUtil.getDecryptString(jdbcPassword));

        // Config private properties of the c3p0 connection pool
        // Maximum threads of connection pool
        dataSource.setMaxPoolSize(30);

        // Minimum threads of connection pool
        dataSource.setMinPoolSize(10);
        dataSource.setInitialPoolSize(10);

        // Manually commit after closing
        dataSource.setAutoCommitOnClose(false);

        // Set timeout
        dataSource.setCheckoutTimeout(10000);

        // Set retry times if connect failed
        dataSource.setAcquireRetryAttempts(2);

        return dataSource;
    }
}
