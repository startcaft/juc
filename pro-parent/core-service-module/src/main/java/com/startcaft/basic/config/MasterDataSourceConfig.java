package com.startcaft.basic.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author startcaft
 * Created by startcaft on 2018/3/1.
 * 数据源，以及Mybaits配置
 */
@Configuration
@MapperScan(basePackages = MasterDataSourceConfig.PACKAGE,sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDataSourceConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(MasterDataSourceConfig.class);
    /**
     * 主数据库Mapper接口的精确包名
     */
    static final String PACKAGE = "com.startcaft.basic.dao.master";
    /**
     * 主数据库Mapper映射文件所在位置
     */
    static final String MAPPER_LOCATION = "classpath:mapper/master/*.xml";
    /**
     * Mybatis全局配置文件
     */
    static final String MYBATIS_CONFIG_FILE = "mybatis_config.xml";

    /*JDBC连接配置*/
    @Value("${master.datasource.url}")
    private String url;
    @Value("${master.datasource.username}")
    private String user;
    @Value("${master.datasource.password}")
    private String password;
    @Value("${master.datasource.driverClassName}")
    private String driverClass;

    /*数据源配置*/
    @Value("${master.datasource.initialSize}")
    private Integer initialSize;
    @Value("${master.datasource.minIdle}")
    private Integer minIdle;
    @Value("${master.datasource.maxActive}")
    private Integer maxActive;
    @Value("${master.datasource.maxWait}")
    private Integer maxWait;
    @Value("${master.datasource.timeBetweenEvictionRunsMillis}")
    private Integer timeBetweenEvictionRunsMillis;
    @Value("${master.datasource.minEvictableIdleTimeMillis}")
    private Integer minEvictableIdleTimeMillis;
    @Value("${master.datasource.validationQuery}")
    private String validationQuery;
    @Value("${master.datasource.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${master.datasource.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${master.datasource.testOnReturn}")
    private boolean testOnReturn;
    @Value("${master.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${master.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private Integer maxPoolPreparedStatementPerConnectionSize;
    @Value("${master.datasource.filters}")
    private String filters;
    @Value("${master.datasource.connectionProperties}")
    private String connectionProperties;


    // master数据源
    @Bean("masterDataSource")
    @Primary
    public DataSource masterDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            dataSource.setFilters(filters);
        }
        catch (SQLException e) {
            LOGGER.error("master druid dataSource configuration initialization filter", e);
        }
        dataSource.setConnectionProperties(connectionProperties);

        return dataSource;
    }

    // master事务管理器
    @Bean(name = "masterTransactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager(@Qualifier("masterDataSource") DataSource masterDataSource) {
        return new DataSourceTransactionManager(masterDataSource);
    }

    // masterSqlSessionFactory
    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG_FILE));
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MasterDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
