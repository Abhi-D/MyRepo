package main;

import java.util.List;

import main.Customer;

public interface CustomerDAO 
{
	public void insert(Customer customer);
	public Customer findByCustomerId(int custId);
	public void createTable();
	public List<Customer> selectAll(String tableName);
}




