package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import service.CustomerService;

@WebServlet("/doRegister")
public class DoRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public DoRegister() {
        super();
        // TODO Auto-generated constructor stub
    }
    
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		 	
		CustomerService service = (CustomerService) CustomerService.getInstance();
		Customer customer = service.register(id, password, name, gender, email);
		
		String page;
		
		if(customer == null) {
			page ="/view/error.jsp";
			request.setAttribute("id", id);
		}
		else {
			page ="/view/registerSuccess.jsp";
			request.setAttribute("customer", customer);
		}
			
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
