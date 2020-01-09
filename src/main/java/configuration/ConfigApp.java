package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Class ConfigAppTest set up all the configurations, makes connection with database, manages transactions and defines
 * strategy for sending emails
 * @author Polina Shcherbinina
 * @version 1.1
 */
@Configuration
@ComponentScan(basePackages = {"controller", "service"})
@EnableWebMvc
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableJpaRepositories(basePackages = "repository")
@EnableScheduling
public class ConfigApp implements WebMvcConfigurer {

    /**
     * Method binds a JPA EntityManager from the specified factory to the thread, potentially allowing for one
     * thread-bound EntityManager per factory. This transaction manager is appropriate for applications that use a single
     * JPA EntityManagerFactory for transactional data access. It helps to open and close transactions
     * @param emfb EntityManagerFactory provides an efficient way to construct multiple EntityManager instances for a
     * database.
     * @return JPATransactionalManager that is responsible for transactional data access
     */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emfb) {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(emfb);
        manager.setDataSource(dataSource());
        return manager;
    }


    /**
     * Method sets up a location where data that is being used originates from
     * @return DataSource is the location where data that is being used originates from.
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource source = new DriverManagerDataSource();

        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        source.setUrl("jdbc:mysql://localhost:3306/ad_catalog?serverTimezone=UTC&useLegacyDatetimeCode=false");
        source.setUsername("root");
        source.setPassword("123456");
        return source;
    }


    @Bean
    /**
     * Method allows to plug in vendor-specific behavior into Spring's EntityManagerFactory creators. Serves as single
     * configuration point for all vendor-specific properties
     * @return JPAVendorAdapter that allows to plug in vendor-specific behavior into Spring's EntityManagerFactory
     * creators
     */
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect");
        adapter.setGenerateDdl(true);
        return adapter;
    }


    /**
     * Method  produces a container-managed EntityManagerFactory
     * @param adapter JPAVendorAdapter
     * @param dataSource DataSource
     * @return LocalContainerEntityManagerFactoryBean that supports links to an existing JDBC DataSource, supports both
     * local and global transactions
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(JpaVendorAdapter adapter,
                                                                           DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(dataSource);
        emfb.setJpaVendorAdapter(adapter);
        emfb.setPackagesToScan("domain");
        return emfb;
    }


    /**
     * Method gets PersistenceAnnotationBeanPostProcessor that processes PersistenceUnit and PersistenceContext
     * annotations, for injection of the corresponding JPA resources EntityManagerFactory and EntityManager
     * @return PersistenceAnnotationBeanPostProcessor
     */
    @Bean
    public PersistenceAnnotationBeanPostProcessor processor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }


    /**
     * Method allows default servlet to serve the resource
     * @param configurer DefaultServletHandlerConfigurer is used to serve static resources
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


    /**
     * Method defines a strategy for sending simple mails
     * @return JavaMailSender that used in conjunction with the MimeMessageHelper class for convenient creation of
     * JavaMail MimeMessages, including attachments
     */
    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();

        sender.setHost("smtp.gmail.com");
        sender.setPort(587);
        sender.setUsername("Valevskii.a@gmail.com");
        sender.setPassword("Suntour123");

        Properties properties = sender.getJavaMailProperties();

        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");

        return sender;
    }
}
