package ca.bytetube.communityApp.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * Related to trasactionManager in spring-service
 * Implement TransactionManagementConfigurer because of opening annotation-driven
 */
@Configuration
// Enable transaction support by using the annotation @EnableTransactionManagement
// Just add the annotation @Transactional to the Service method
@EnableTransactionManagement
public class TransactionManagementConfiguration implements TransactionManagementConfigurer {

    @Autowired
    // Inject the dataSource in DataSourceConfiguration and get it through craeteDataSource()
    private DataSource dataSource;

    @Override
    /**
     * Return the implementation of PlatformTransactionManager in order to do transaction management
     */
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
