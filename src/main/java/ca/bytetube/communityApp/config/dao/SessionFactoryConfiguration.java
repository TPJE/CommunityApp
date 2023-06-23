package ca.bytetube.communityApp.config.dao;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

public class SessionFactoryConfiguration {
    // mybatis-config.xml Path
    private static String mybatisConfigFile;

    @Value("${mybatis_config_file}")
    public void setMybatisConfigFile(String mybatisConfigFile) {
        SessionFactoryConfiguration.mybatisConfigFile = mybatisConfigFile;
    }

    // mybatis mapper Path
    private static String mapperPath;

    @Value("${type_alias_package}")
    private String typeAliasPackage;

    @Autowired
    private DataSource dataSource;

    /**
     * Create sqlSessionFactoryBean instance and set configuration, mapper reference path, set datasource's data source
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // Set mybatis configuration scan path
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisConfigFile));

        // Add mapper scan path
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperPath;
        sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(packageSearchPath));;

        // Set dataSource
        sqlSessionFactoryBean.setDataSource(dataSource);

        // Set typeAlias package scan path
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasPackage);

        return sqlSessionFactoryBean;
    }

}
