package service;

import java.util.HashMap;
import java.util.Map;

import model.Customer;

public class CustomerService {
	
	private static final CustomerService instance = new CustomerService();
	
	private Map<String, Customer> customers;
	
	private CustomerService() {
		customers = new HashMap<String, Customer>();
		System.out.println("In CustomerService Constructor");

	}
	
	public static CustomerService getInstance() {
		return instance; //singletone
	}

	public void addCustomer(Customer customer) {
		customers.put(customer.getId(), customer);
	}
	 
	//있는 아이디인지 검사
	public Customer findCustomer(String id) {
		if(id != null)
			return(customers.get(id.toLowerCase()));
		else
			return null;
	}

	public Customer login(String id, String password) {
		Customer customer = findCustomer(id);

		if(customer != null) {
			if( password.equals(customer.getPassword())  ) {
				System.out.println("login success");
				return customer;
			}
					
			else {
				System.out.println("login failed");
				return null;
			}
		}	
		return null;
	}
	
	public Customer register(String id, String password, String name, String gender, String email) {
		addCustomer(new Customer(id, password, name, gender, email));
		Customer customer = findCustomer(id);
	
		if(customer != null) {
				System.out.println("register success");
				return customer;
			}
					
			else {
				System.out.println("register failed");
				return null;
			}
	}
}
