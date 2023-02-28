package com.xadmin.employeemanagement1.web;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xadmin.employeemanagement1.bean.Employee;
import com.xadmin.employeemanagement1.dao.EmployeeDao;


public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeDao employeeDao;
       
    
    public EmployeeServlet() {
        super();
        
    }

	
	public void init() throws ServletException {
		employeeDao = new EmployeeDao();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		
	      try {
			switch (action) {
		case "/new":
			showNewForm(request, response);
			break;
		
	case "/insert":
		insertEmployee(request, response);
		break;
		
	case "/delete":
		deleteEmployee(request, response);
		break;
		
	case "/edit":
		showEditForm(request, response);
		break;
	
	case "/update":
		updateEmployee(request, response);
		break;
		
		default:
			listEmployee(request, response);
			break;
			}
		}catch (SQLException ex) {
				throw new ServletException(ex);
			} catch (javax.servlet.ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	
		

		private void showNewForm(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, javax.servlet.ServletException {
			RequestDispatcher dispatcher = (RequestDispatcher) request.getRequestDispatcher("employee-form.jsp");
			dispatcher.forward(request, response);
		

	}
		private void insertEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
			String name = request.getParameter("name");
			String department = request.getParameter("department");
			String designation = request.getParameter("designation");
			Employee newEmployee = new Employee(name, department, designation);
			employeeDao.insertEmployee(newEmployee);
			response.sendRedirect("list");
		}
		private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			employeeDao.deleteEmployee(id);
			response.sendRedirect("list");

		}
		private void showEditForm(HttpServletRequest request, HttpServletResponse response)throws SQLException, ServletException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			Employee existingEmployee;
			try {
			 existingEmployee = employeeDao.selectEmployee(id);
			RequestDispatcher dispatcher = (RequestDispatcher) request.getRequestDispatcher("employee-form.jsp");
			request.setAttribute("employee", existingEmployee);
			dispatcher.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		private void updateEmployee(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String department = request.getParameter("department");
			String designation = request.getParameter("designation");

			Employee employee = new Employee(id, name, department, designation);
			employeeDao.updateEmployee(employee);
			response.sendRedirect("list");
		}
		
		private void listEmployee(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException {
			try {
				List<Employee> listEmployee = employeeDao.selectAllEmployee();
				request.setAttribute("listEmployee", listEmployee);
				RequestDispatcher dispatcher = (RequestDispatcher) request.getRequestDispatcher("employee-list.jsp");
				dispatcher.forward(request, response);
			
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
			
		}
}
