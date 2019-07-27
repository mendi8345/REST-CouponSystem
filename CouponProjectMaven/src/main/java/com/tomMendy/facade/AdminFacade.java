package com.tomMendy.facade;

import java.util.Set;

import com.tomMendy.dao.CompanyDAO;
import com.tomMendy.dao.CouponDAO;
import com.tomMendy.dao.CustomerDAO;
import com.tomMendy.dbdao.CompanyDBDAO;
import com.tomMendy.dbdao.CouponDBDAO;
import com.tomMendy.dbdao.CustomerDBDAO;
import com.tomMendy.javabeans.ClientType;
import com.tomMendy.javabeans.Company;
import com.tomMendy.javabeans.Coupon;
import com.tomMendy.javabeans.Customer;

public class AdminFacade implements CouponClientFacade {

	private CouponDAO couponDAO = new CouponDBDAO();
	private CustomerDAO customerDAO = new CustomerDBDAO();
	private CompanyDAO companyDAO = new CompanyDBDAO();

	private Coupon coupon;
	private Company company;
	private Customer customer;

	private String name = "admin";
	private String password = "1234";

	public AdminFacade() {

	}

	public String gotIt2() {

		System.out.println("kjjjjk");
		return "got it ";
	}

	public void createCompany(Company company) throws Exception {
		System.out.println("AdminFacade AdminFacade");
		this.companyDAO.insertCompany(company);
	}

	public void removeCompany(Company company) throws Exception {
		this.companyDAO.removeCompany(company);
	}

	public void updateCompany(Company company) throws Exception {

		this.companyDAO.updateCompany(company);
	}

	public Company getCompany(long id) throws Exception {
		System.out.println("in getCompany(long id)");
		return this.companyDAO.getCompany(id);
	}

	public Set<Company> getAllCompany() throws Exception {
		// CompanyDBDAO companyDAO=new CompanyDBDAO();
		return this.companyDAO.getAllCompany();
	}

	public void insertCustomer(Customer customer) throws Exception {
		this.customerDAO.insertCustomer(customer);
	}

	public void removeCustomer(Customer customer) throws Exception {
		this.customerDAO.removeCustomer(customer);
	}

	public void updateCustomer(Customer customer) throws Exception {
		this.customerDAO.updateCustomer(customer);

	}

	public Customer getCustomer(long id) throws Exception {
		return this.customerDAO.getCustomer(id);
	}

	public Set<Customer> getAllCustomer() throws Exception {
		// ProductDBDAO comDAO=new ProductDBDAO();
		return this.customerDAO.getAllCustomer();
	}

	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {

		return null;

	}
}