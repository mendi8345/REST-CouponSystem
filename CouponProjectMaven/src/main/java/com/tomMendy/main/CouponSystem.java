package com.tomMendy.main;

import java.sql.Connection;

import com.tomMendy.dao.CompanyDAO;
import com.tomMendy.dao.CouponDAO;
import com.tomMendy.dao.CustomerDAO;
import com.tomMendy.db.ConnectionPool;
import com.tomMendy.dbdao.CompanyDBDAO;
import com.tomMendy.dbdao.CustomerDBDAO;
import com.tomMendy.facade.AdminFacade;
import com.tomMendy.facade.CompanyFacade;
import com.tomMendy.facade.CouponClientFacade;
import com.tomMendy.facade.CustomerFacade;
import com.tomMendy.javabeans.ClientType;
import com.tomMendy.javabeans.Company;
import com.tomMendy.javabeans.Customer;
import com.tomMendy.utils.DailyTask;

/**
 * @CoponSystem starts when application load. checking the login success. if
 *              login status true, gives the required permissions
 */

public class CouponSystem {

	private ConnectionPool connectionPool;
	private Connection con;
	private CompanyDAO companyDAO;
	private CouponDAO couponDAO;
	private CustomerDAO customerDAO;

	private DailyTask dailyTask = new DailyTask();
	private static CouponSystem instance = new CouponSystem();

	public static CouponSystem getInstance() {
		return instance;
	}

	public CouponSystem() {
		try {
			this.connectionPool = ConnectionPool.getInstance();

			// this.dailyTask.start();
		} catch (Exception e) {

		}
	}

	public CompanyDAO getCompanyDAO() {
		return this.companyDAO;
	}

	public CouponDAO getCouponDAO() {
		return this.couponDAO;
	}

	public CustomerDAO getCustomerDAO() {
		return this.customerDAO;
	}

	public CouponClientFacade login(String name, String password, ClientType clientType) throws Exception {

		if (clientType == ClientType.admin) {
			if (name.equals("Admin") && password.equals("1234")) {
				AdminFacade adminFacade = new AdminFacade();

				return adminFacade;
			}
		}

		if (clientType == ClientType.company) {

			CompanyDBDAO companyDBDAO = new CompanyDBDAO();

			boolean loginStatus = companyDBDAO.login(name, password);
			System.out.println("companyDBDAO.login(name, password)" + companyDBDAO.login(name, password));

			if (loginStatus) {
				Company company = new Company();
				company.setId(companyDBDAO.GetCompIdByName(name));
				company.setCompName(name);
				company.setPassword(password);
				CompanyFacade companyFacade = new CompanyFacade(company);
				// companyFacade.setCompanyDAO(this.companyDAO);
				// companyFacade.setCouponDAO(this.couponDAO);

				return companyFacade;

			}
		}

		if (clientType == ClientType.customer) {

			CustomerDBDAO customerDBDAO = new CustomerDBDAO();
			boolean loginStatus = customerDBDAO.login(name, password);
			System.out.println("customerDBDAO.login(name, password)" + customerDBDAO.login(name, password));
			if (loginStatus) {

				Customer customer = new Customer();
				customer.setId(customerDBDAO.GetCustIdByName(name));
				customer.setCustName(name);
				customer.setPassword(password);
				CustomerFacade customerFacade = new CustomerFacade(customer);

				return customerFacade;

			}
		}

		return null;

	}

}