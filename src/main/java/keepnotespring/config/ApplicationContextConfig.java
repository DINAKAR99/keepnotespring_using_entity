package keepnotespring.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import keepnotespring.model.Note;

/*This class will contain the application-context for the application. 
 * Define the following annotations:
 * @Configuration - Annotating a class with the @Configuration indicates that the 
 *                  class can be used by the Spring IoC container as a source of 
 *                  bean definitions
 * @EnableTransactionManagement - Enables Spring's annotation-driven transaction management capability.
 *                  
 * */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "keepnotespring.*")
public class ApplicationContextConfig {

	/*
	 * Define the bean for DataSource. In our application, we are using MySQL as the
	 * dataSource. To create the DataSource bean, we need to know: 1. Driver class
	 * name 2. Database URL 3. UserName 4. Password
	 */
	@Bean
	public DriverManagerDataSource getdataSource() {
		DriverManagerDataSource d1 = new DriverManagerDataSource();

		d1.setDriverClassName("org.postgresql.Driver");
		d1.setPassword("dinakar1.");
		d1.setUsername("postgres");
		d1.setUrl("jdbc:postgresql://localhost:5433/keepnote");

		return d1;

	}

	/*
	 * Use this configuration while submitting solution in hobbes.
	 * dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	 * dataSource.setUrl("jdbc:mysql://" + System.getenv("MYSQL_HOST") + ":3306/" +
	 * System.getenv("MYSQL_DATABASE")
	 * +"?verifyServerCertificate=false&useSSL=false&requireSSL=false");
	 * dataSource.setUsername(System.getenv("MYSQL_USER"));
	 * dataSource.setPassword(System.getenv("MYSQL_PASSWORD"));
	 */

	/*
	 * Define the bean for SessionFactory. Hibernate SessionFactory is the factory
	 * class through which we get sessions and perform database operations.
	 */
	@Bean
	public LocalSessionFactoryBean getfactory() {
		LocalSessionFactoryBean l1 = new LocalSessionFactoryBean();

		l1.setDataSource(getdataSource());
		Properties p = new Properties();
		p.put("hibernate.hbm2ddl.auto", "update");
		p.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		p.put("hibernate.format_sql", "true");
		p.put("hibernate.show_sql", "true");
		l1.setHibernateProperties(p);

		l1.setAnnotatedClasses(Note.class);
		// l1.setAnnotatedPackages(null);
		return l1;

	}

	/*
	 * Define the bean for Transaction Manager. HibernateTransactionManager handles
	 * transaction in Spring. The application that uses single hibernate session
	 * factory for database transaction has good choice to use
	 * HibernateTransactionManager. HibernateTransactionManager can work with plain
	 * JDBC too. HibernateTransactionManager allows bulk update and bulk insert and
	 * ensures data integrity.
	 */
	@Bean
	public HibernateTransactionManager getTransManager() {

		return new HibernateTransactionManager(getfactory().getObject());

	}
}
