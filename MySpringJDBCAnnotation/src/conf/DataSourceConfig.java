package conf;


import org.springframework.beans.factory.annotation.*;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource({"resources/db.properties"})
public class DataSourceConfig {
	
	@Value(value="${jdbc.driverClassName}")
	private String jdbcDriverClassName;
	
	@Value(value="${jdbc.url}")
	private String jdbcUrl;
	
	@Value(value="${jdbc.username}")
	private String jdbcUserName;
	
	@Value(value="${jdbc.password}")
	private String jdbcPassword;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyResolver(){
		PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();
		return properties;
	}
	
	@Bean(name="dataSource")
	public DriverManagerDataSource dataSource(){
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		/*driverManagerDataSource.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
		driverManagerDataSource.setUrl("jdbc:derby:M://G Drive//Abhishek Dutta//My Courses//Spring Certified Developer//Practices//Derby;create=true");
		driverManagerDataSource.setUsername("admin");
		driverManagerDataSource.setPassword("admin");*/
		driverManagerDataSource.setDriverClassName(jdbcDriverClassName);
		driverManagerDataSource.setUrl(jdbcUrl);
		driverManagerDataSource.setUsername(jdbcUserName);
		driverManagerDataSource.setPassword(jdbcPassword);
		return driverManagerDataSource;
	}
}
