package com.xadmin.employeemanagement1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.employeemanagement1.bean.Employee;
public class EmployeeDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/employee?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword ="rootpasswordgiven";
	private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO users" + "  (name, department, designation) VALUES "
			+ " (?, ?, ?);";


	private static final String SELECT_EMPLOYEE_BY_ID = "select id,name,department,designation from users where id =?";
	private static final String SELECT_ALL_EMPLOYEE = "select * from employee";
	private static final String DELETE_EMPLOYEE_SQL = "delete from employee where id = ?;";
	private static final String UPDATE_EMPLOYEE_SQL = "update users set name = ?,department= ?, designation =? where id = ?;";
	
	public EmployeeDao() {
		
	}
	
		
		protected Connection getConnection()
		{
			Connection connection = null;
			
			try {
				Class.forName("jdbcDriver");
				connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return connection;
		}
	
		
		
		//insert employee
		
		public void insertEmployee(Employee employee)throws SQLException {
			System.out.println(INSERT_EMPLOYEE_SQL);
			
			try (Connection connection = getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_SQL)) {
				preparedStatement.setString(1, employee.getName());
				preparedStatement.setString(2, employee.getDepartment());
				preparedStatement.setString(3, employee.getDesignation());
				System.out.println(preparedStatement);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				printSQLException(e);
			}
		}


		
		
		//select employee by id
		public  Employee selectEmployee(int id)
		{
			
			Employee employee = null;
			
			try (Connection connection = getConnection();
				
					PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);) {
				preparedStatement.setInt(1, id);
				System.out.println(preparedStatement);
			
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					String name = rs.getString("name");
					String department = rs.getString("department");
					String designation = rs.getString("desingation");
					employee = new Employee(id, name, department, designation);
				}
			} catch (SQLException e) {
				printSQLException(e);
			}
			return employee;
		}
		
	
		//select all employee
		
		public List<Employee> selectAllEmployee()
		{
			
			List<Employee> employee = new ArrayList<>();
			
			try (Connection connection = getConnection();

					
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEE);) {
				System.out.println(preparedStatement);
			
				ResultSet rs = preparedStatement.executeQuery();

		
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String department = rs.getString("department");
					String designation = rs.getString("designation");
					
					employee.add(new Employee(id, name, department, designation));
				}
			} catch (SQLException e) {
				printSQLException(e);
			}
			return employee;
		}
		//update employee
		public boolean updateEmployee(Employee employee) throws SQLException {
			boolean rowUpdated;
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEE_SQL);) {
				System.out.println("updated Employee:"+statement);
				statement.setString(1, employee.getName());
				statement.setString(2, employee.getDepartment());
				statement.setString(3, employee.getDesignation());
				statement.setInt(4, employee.getId());

				rowUpdated = statement.executeUpdate() > 0;
			}
			return rowUpdated;
		}
		//delete employee
		public boolean deleteEmployee(int id) throws SQLException {
			boolean rowDeleted;
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEE_SQL);) {
				statement.setInt(1, id);
				rowDeleted = statement.executeUpdate() > 0;
			}
			return rowDeleted;
		}
		
		
		private void printSQLException(SQLException ex) {
			for (Throwable e : ex) {
				if (e instanceof SQLException) {
					e.printStackTrace(System.err);
					System.err.println("SQLState: " + ((SQLException) e).getSQLState());
					System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
					System.err.println("Message: " + e.getMessage());
					Throwable t = ex.getCause();
					while (t != null) {
						System.out.println("Cause: " + t);
						t = t.getCause();
					}
				}
			}
		}
		
	
	}
	


