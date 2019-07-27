package com.tomMendy.main;

import java.sql.Connection;
import java.sql.DriverManager;

import com.tomMendy.db.Database;

public class Test {

	public static void main(String[] args) throws Exception {

		// Create a connection to the database:
		Connection con = DriverManager.getConnection(Database.getDBUrl());
		Database.dropTables(con);
		System.out.println();
		// ConnectionPool connectionPool = ConnectionPool.getInstance();
		Database.createTables(con);

		// DailyTask dailyTask = new DailyTask();
		// CouponSystem couponSystem = CouponSystem.getInstance();
		//
		// Company company1 = new Company(1, " super pharm", "4211",
		// "superpharm@java.com");
		// Company company2 = new Company(2, "dominos", "2345", "dominos@java.com");
		// Company company3 = new Company(3, "rami levi", "6789", "ramilevi@java.com");
		//
		// Coupon coupon1 = new Coupon(1, "falafel", DateUtils.GetCurrentDate(),
		// DateUtils.GetEndDate(), 100, "electric",
		// CouponType.food, 5.0, "image1");
		// Coupon coupon2 = new Coupon(2, "pizza", DateUtils.GetCurrentDate(),
		// DateUtils.GetEndDate(), 10, "food",
		// CouponType.electric, 8.0, "image2");
		//
		// Customer customer1 = new Customer(1, "tom", "1234");
		// Customer customer2 = new Customer(2, "mendy", "3456");
		// Customer customer3 = new Customer(3, "kobi", "6789");
		//
		// /**
		// * @AdminTest:
		// */
		//
		// System.out.println("*************************************");
		// System.out.println();
		// System.out.println("// **Admin Test**//");
		// System.out.println();
		// System.out.println("// **Checking Adamin's login **//");
		// AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234",
		// ClientType.admin);
		// System.out.println("// ** Adamin's login succesed **//");
		// System.out.println();
		// System.out.println("\"// ** Admim creates companies **//\"");
		// adminFacade.createCompany(company1); // success//
		// adminFacade.createCompany(company2); // success
		// adminFacade.createCompany(company3); // success
		//
		// System.out.println();
		// System.out.println("\"// ** Admim creates customers **//\"");
		//
		// adminFacade.insertCustomer(customer1); // success
		// adminFacade.insertCustomer(customer2); // success
		// adminFacade.insertCustomer(customer3); // success
		// /**
		// * @CompanyTest:
		// */
		//
		// System.out.println("*************************************");
		// System.out.println();
		// System.out.println("// **Company Test**//");
		// System.out.println();
		//
		// System.out.println("// **Checking a company login **//");
		// CompanyFacade companyFacade1 = (CompanyFacade)
		// couponSystem.login(company1.getCompName(),
		// company1.getPassword(), ClientType.company);
		// CompanyFacade companyFacade2 = (CompanyFacade) couponSystem.login("dominos",
		// "2345", ClientType.company);
		// System.out.println("// ** company login succesed **//");
		// System.out.println();
		// companyFacade1.createCoupon(coupon1); // succes
		// companyFacade2.createCoupon(coupon2); // success
		//
		// // System.out.println(companyFacade1.getCompCoupons()); // success
		// // System.out.println(companyFacade1.getCouponsByType(CouponType.food)); //
		// // success
		//
		// /**
		// * @CustomerTest:
		// */
		//
		// System.out.println("*************************************");
		// System.out.println();
		// System.out.println("// **Customer Test**//");
		// System.out.println();
		//
		// System.out.println("// **Checking a customer login **//");
		// //
		//
		// CustomerFacade customerFacade1 = (CustomerFacade) couponSystem.login("tom",
		// "1234", ClientType.customer);
		// CustomerFacade customerFacade2 = (CustomerFacade) couponSystem.login("mendy",
		// "3456", ClientType.customer);
		//
		// System.out.println("// ** Adamin's login succesed **//");
		// System.out.println();
		// customerFacade1.purchaseCoupon(coupon1); // success
		// customerFacade2.purchaseCoupon(coupon2); // success
		// System.out.println("88888888888888" +
		// customerFacade1.getAllPurchasedCoupon()); // success
		// System.out.println("kjkjkjkjk" + customerDBDAO.getCustCoupons(customer1));
		// // System.out.println(customer2.getAllPurchasedCoupons()); // success
		//
		// /**
		// *
		// * Remove@Test:
		// */
		// System.out.println();
		// System.out.println("// ** Remove@Test **//");
		// System.out.println();
		// // adminFacade.removeCompany(company1);
		// // adminFacade.removeCustomer(customer1);
	}

}