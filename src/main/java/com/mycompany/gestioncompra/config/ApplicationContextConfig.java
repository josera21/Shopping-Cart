/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestioncompra.config;


import com.mycompany.gestioncompra.dao.CategoriaDAO;
import com.mycompany.gestioncompra.dao.OrdenDAO;
import com.mycompany.gestioncompra.dao.ProductoDAO;
import com.mycompany.gestioncompra.dao.UsuarioDAO;
import com.mycompany.gestioncompra.dao.impl.CategoriaDAOImpl;
import com.mycompany.gestioncompra.dao.impl.OrdenDAOImpl;
import com.mycompany.gestioncompra.dao.impl.ProductoDAOImpl;
import com.mycompany.gestioncompra.dao.impl.UsuarioDAOImpl;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 *
 * @author josera
 */
@Configuration
@ComponentScan("com.mycompany.gestioncompra.*")
@EnableTransactionManagement
// Load to Environment.
@PropertySource("classpath:ds-hibernate-cfg.properties")
public class ApplicationContextConfig {
     // The Environment class serves as the property holder
    // and stores all the properties loaded by the @PropertySource
    @Autowired
    private Environment env;
 
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
        // Load property in message/validator.properties
        rb.setBasenames(new String[] { "messages/validator" });
        return rb;
    }
    
    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        
        System.out.print("** PASE VIEWRESOLVER **");
        
        return viewResolver;
    }
    
    // Config for Upload.
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
         
        // Set Max Size...
        // commonsMultipartResolver.setMaxUploadSize(...);
         System.out.print("** PASE MULTIPARTRESOLVER **");
        return commonsMultipartResolver;
    }
    
    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
 
        // See: ds-hibernate-cfg.properties
        dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
        dataSource.setUrl(env.getProperty("ds.url"));
        dataSource.setUsername(env.getProperty("ds.username"));
        dataSource.setPassword(env.getProperty("ds.password"));
        System.out.print("** PASE DATASOURCE **");
        System.out.println("## getDataSource: " + dataSource);
         
        return dataSource;
    }
 
    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
        Properties properties = new Properties();
 
        // See: ds-hibernate-cfg.properties
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.put("current_session_context_class", env.getProperty("current_session_context_class"));
         
 
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
         
        // Package contain entity classes
        factoryBean.setPackagesToScan(new String[] { "com.mycompany.gestioncompra.dto" });
        factoryBean.setDataSource(dataSource);
        factoryBean.setHibernateProperties(properties);
        factoryBean.afterPropertiesSet();
        //
        
        System.out.print("** PASE SESSIONFACTORY **");
        
        SessionFactory sf = factoryBean.getObject();
        System.out.println("## getSessionFactory: " + sf);
        return sf;
    }
 
    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        System.out.print("** PASE TRANSACTION MANAGER **");
        return transactionManager;
    }
 
    @Bean(name = "accountDAO")
    public UsuarioDAO getApplicantDAO() {
        return new UsuarioDAOImpl();
    }
 
    @Bean(name = "productDAO")
    public ProductoDAO getProductDAO() {
        return new ProductoDAOImpl();
    }
 
    @Bean(name = "orderDAO")
    public OrdenDAO getOrderDAO() {
        return new OrdenDAOImpl();
    }
     
    @Bean(name = "accountDAO")
    public UsuarioDAO getAccountDAO()  {
        return new UsuarioDAOImpl();
    }
    
    @Bean(name = "categoryDAO")
    public CategoriaDAO getCategoryDAO() {
        return new CategoriaDAOImpl(); 
    }
}
