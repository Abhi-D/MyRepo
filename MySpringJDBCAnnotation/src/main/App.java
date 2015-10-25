package main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import conf.DataSourceConfig;
import conf.MainConfig;
import main.CustomerDAO;
import main.Customer;

public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class,DataSourceConfig.class);
    	 
    	DriverManagerDataSource driverManagerDataSource = context.getBean("dataSource",DriverManagerDataSource.class);
        CustomerDAO customerDAO = context.getBean("JdbcCustomerDAO",JdbcCustomerDAO.class);
        Customer customer = new Customer(1, "mkyong",28);
        Customer customer2 = new Customer(2, "Abhishek",28);
        customerDAO.createTable();
        customerDAO.insert(customer);
        customerDAO.insert(customer2);
    	
        Customer customer1 = customerDAO.findByCustomerId(2);
        System.out.println(customer1);
        customer1 = customerDAO.findByCustomerId(1);
        System.out.println(customer1);
        
    }
}
