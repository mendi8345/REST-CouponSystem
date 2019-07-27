package com.tomMendy.facade;

import java.sql.Connection;
import java.util.Set;

import com.tomMendy.dao.CompanyDAO;
import com.tomMendy.dao.CouponDAO;
import com.tomMendy.dbdao.CompanyDBDAO;
import com.tomMendy.dbdao.CouponDBDAO;
import com.tomMendy.javabeans.ClientType;
import com.tomMendy.javabeans.Company;
import com.tomMendy.javabeans.Coupon;
import com.tomMendy.javabeans.CouponType;

public class CompanyFacade implements CouponClientFacade {

	Connection con;
	private CompanyDAO companyDAO = new CompanyDBDAO();
	private CouponDAO couponDAO = new CouponDBDAO();

	private Company company;
	private Coupon coupon;

	public Company getCompany() {
		return this.company;
	}

	public CompanyFacade(Company company) {
		this.company = company;
	}

	public void setCompanyDAO(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}

	public void setCouponDAO(CouponDAO couponDAO) {
		this.couponDAO = couponDAO;
	}

	public void createCoupon(Coupon coupon) throws Exception {
		this.couponDAO.insertCoupon(this.company, coupon);

	}

	public void removeCoupon(Coupon coupon) throws Exception {
		this.couponDAO.removeCoupon(coupon);

	}

	public void updateCoupon(Coupon coupon) throws Exception {
		this.couponDAO.updateCoupon(coupon);
	}

	public Coupon getCoupon(long id) throws Exception {
		return this.couponDAO.getCoupon(id);

	}

	public Set<Coupon> getCompCoupons() throws Exception {
		return this.companyDAO.getCompCoupons(this.company);

	}

	public Set<Coupon> getCouponsByType(CouponType couponType) throws Exception {
		Set<Coupon> coupons = this.getCompCoupons();

		for (Coupon c : coupons) {
			if (c.getCouponType() != couponType) {
				coupons.remove(c);
			}
		}
		return coupons;

	}

	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		// TODO Auto-generated method stub
		return null;
	}
}