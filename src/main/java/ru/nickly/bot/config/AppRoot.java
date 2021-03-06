package ru.nickly.bot.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import ru.nickly.bot.onenotemodel.scheduler.OneNoteScheduled;
import ru.nickly.bot.webservice.BotService;
import ru.nickly.bot.webservice.OneNoteApiService;
import ru.nickly.bot.webservice.OneNoteAuthService;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("ru.nickly.bot")
@PropertySource("classpath:application.properties")
@EnableJpaRepositories("ru.nickly.bot.data")
public class AppRoot {

    private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
    private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";

    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";

    private static final String APPLICATION_TOKEN = "app.token";


    @Resource
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
        dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
        dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
        dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(env.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));

        entityManagerFactoryBean.setJpaProperties(hibProperties());

        return entityManagerFactoryBean;
    }

    private Properties hibProperties() {
        Properties properties = new Properties();
        properties.put(PROPERTY_NAME_HIBERNATE_DIALECT,	env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
        properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }


    private Retrofit botRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl("https://api.telegram.org/bot"+env.getRequiredProperty(APPLICATION_TOKEN)+"/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    private Retrofit oneNoteAuthRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl("https://login.live.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    private Retrofit oneNoteApiRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl("https://www.onenote.com/api/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    @Bean
    public BotService botService(){
        return botRetrofitInstance().create(BotService.class);
    }

    @Bean
    public OneNoteAuthService oneNoteAuthService(){
        return oneNoteAuthRetrofitInstance().create(OneNoteAuthService.class);
    }

    @Bean
    public OneNoteApiService oneNoteApiService(){
        return oneNoteApiRetrofitInstance().create(OneNoteApiService.class);
    }

    //Quartz beans
    @Bean
    public OneNoteScheduled getOneNoteUpdatesTask(){
        return new OneNoteScheduled();
    }

    @Bean
    public MethodInvokingJobDetailFactoryBean getOneNoteUpdatesJobFactory(){
        MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean = new MethodInvokingJobDetailFactoryBean();
        methodInvokingJobDetailFactoryBean.setTargetObject(getOneNoteUpdatesTask());
        methodInvokingJobDetailFactoryBean.setTargetMethod("getOneNoteUpdates");
        return methodInvokingJobDetailFactoryBean;
    }

    @Bean
    public SimpleTriggerFactoryBean getOneNoteUpdatesQuartzTrigger(){
        SimpleTriggerFactoryBean simpleTriggerFactoryBean = new SimpleTriggerFactoryBean();
        simpleTriggerFactoryBean.setJobDetail(getOneNoteUpdatesJobFactory().getObject());
        simpleTriggerFactoryBean.setRepeatInterval(50000);
        return simpleTriggerFactoryBean;
    }

    @Bean
    public SchedulerFactoryBean getOneNoteUpdatesSchedulerFactoryBean(){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setTriggers(getOneNoteUpdatesQuartzTrigger().getObject());
        return schedulerFactoryBean;
    }


}
