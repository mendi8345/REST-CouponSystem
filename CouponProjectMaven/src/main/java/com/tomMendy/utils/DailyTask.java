package com.tomMendy.utils;

import java.sql.Connection;
import java.util.HashSet;
import java.util.Set;

import com.tomMendy.db.ConnectionPool;
import com.tomMendy.dbdao.CouponDBDAO;
import com.tomMendy.facade.CompanyFacade;
import com.tomMendy.javabeans.Company;
import com.tomMendy.javabeans.Coupon;

public class DailyTask implements Runnable {
	Company company;
	private Thread thread;
	ConnectionPool connectionPool;
	Connection con;
	private boolean isRunning = true;

	CompanyFacade companyFacade = new CompanyFacade(this.company);
	CouponDBDAO couponDBDAO = new CouponDBDAO();
	Coupon coupon = new Coupon();

	@Override
	public void run() {
		while (this.isRunning) {
			try {
				Thread.sleep(5000);
				Set<Coupon> allCoupons = new HashSet<Coupon>();
				allCoupons = this.couponDBDAO.getAllCoupons();

				for (Coupon c : allCoupons) {
					if (c.getEndDate().equals(DateUtils.GetCurrentDate())
							|| this.coupon.getEndDate().before(DateUtils.GetCurrentDate())) {
						System.out.println("Expired coupon deletion task was successful With Coupon " + c.getTitle());
						this.couponDBDAO.removeCoupon(c);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				ConnectionPool.getInstance().closeAllConnections();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stopTask();

		}

	}

	public void start() throws Exception {
		this.thread = new Thread(this);
		this.thread.start();
	}

	public void stopTask() {
		this.isRunning = false;
	}
}