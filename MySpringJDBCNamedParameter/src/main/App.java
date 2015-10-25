package main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import main.CustomerDAO;
import main.Customer;

public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context = new ClassPathXmlApplicationContext("conf/Spring-Module.xml");
    	 
        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
        Customer customer = new Customer(1, "mkyong",28);
        Customer customer2 = new Customer(2, "Abhishek",28);
        //customerDAO.createTable();
        Customer customer3 = new Customer(5, "Brad Pitt",28);
        //customerDAO.insert(customer3);
        //customerDAO.insert(customer2);
        System.out.println(customerDAO.selectAll("CUSTOMER2"));
        //System.out.println(customerDAO.selectAll("DEPARTMENT"));
        /*customerDAO.insert(customer);
        customerDAO.insert(customer2);
    	
        Customer customer1 = customerDAO.findByCustomerId(2);
        System.out.println(customer1);
        customer1 = customerDAO.findByCustomerId(1);
        System.out.println(customer1);*/
        
    }
}
