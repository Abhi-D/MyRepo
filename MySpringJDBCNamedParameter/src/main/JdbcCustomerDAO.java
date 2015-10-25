package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;

import main.CustomerDAO;
import main.Customer;

public class JdbcCustomerDAO implements CustomerDAO
{
	private DataSource dataSource;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public void insert(Customer customer){
		
		String sql = "INSERT INTO CUSTOMER2 " +
				"(CUST_ID, NAME, AGE) VALUES (:cust_id, :name, :age)";
		Connection conn = null;
		SqlParameterSource parametersMap = new MapSqlParameterSource("cust_id",customer.getCustId()).addValue("name", customer.getName()).addValue("age", customer.getAge());
		
		try {
			namedParameterJdbcTemplate.update(sql,parametersMap);
		}  finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	public Customer findByCustomerId(int custId){
		
		String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, custId);
			Customer customer = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				customer = new Customer(
						rs.getInt("CUST_ID"),
						rs.getString("NAME"), 
						rs.getInt("Age")
				);
			}
			rs.close();
			ps.close();
			return customer;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}

	public void createTable() {
		String sql = "CREATE TABLE CUSTOMER2 (CUST_ID INT,NAME VARCHAR(20), AGE INT)";
		String sql2 = "CREATE TABLE DEPARTMENT (DEPT_ID INT,NAME VARCHAR(20))";
		try {
			namedParameterJdbcTemplate.getJdbcOperations().execute(sql);
		}finally{
			
		}		
	}

	public List<Customer> selectAll(String tableName) {
		List<Customer> customers = new ArrayList<Customer>();
		String sql = "SELECT * FROM "+tableName;
		SqlParameterSource params = new MapSqlParameterSource();
		try {
			customers = namedParameterJdbcTemplate.query(sql, params,new RowMapper<Customer>(){

				public Customer mapRow(ResultSet arg0, int arg1) throws SQLException {
					Customer c1 = new Customer(arg0.getInt("CUST_ID"),arg0.getString("NAME"),arg0.getInt("AGE"));
					
					return c1;
				}
				
			});
		}finally{
			
		}	
		return customers;
		
	}
}




