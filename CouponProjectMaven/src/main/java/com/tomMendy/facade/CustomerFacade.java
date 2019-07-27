package com.tomMendy.facade;

import java.util.Set;

import com.tomMendy.dao.CouponDAO;
import com.tomMendy.dao.CustomerDAO;
import com.tomMendy.dbdao.CouponDBDAO;
import com.tomMendy.dbdao.CustomerDBDAO;
import com.tomMendy.exeptions.CoponNotAvilable;
import com.tomMendy.javabeans.ClientType;
import com.tomMendy.javabeans.Coupon;
import com.tomMendy.javabeans.CouponType;
import com.tomMendy.javabeans.Customer;

public class CustomerFacade implements CouponClientFacade {

	private CustomerDAO customerDAO = new CustomerDBDAO();
	private CouponDAO couponDAO = new CouponDBDAO();

	private Customer customer;

	public CustomerFacade(Customer customer) {
		this.customer = customer;
	}

	public void purchaseCoupon(Coupon coupon) throws Exception {
		if (this.couponDAO.getAllCoupons() != null) {

			Coupon couponData = this.couponDAO.getCoupon(coupon.getId());
			System.out.println(couponData.getAmount());
			if (couponData == null) {
				throw new CoponNotAvilable(couponData);
			}
			if (couponData.getAmount() <= 0) {

				throw new CoponNotAvilable(couponData);
			}
			// not purchased already
			if (getAllPurchasedCoupon().contains(couponData)) {
				throw new CoponNotAvilable(couponData);
			}
			// purchase
			this.customerDAO.associateCouponToCustomer(this.customer, couponData);
			System.out.println(this.customer.getCustName() + " purchase " + couponData);

			// decrease amount
		}
	}

	public Set<Coupon> getAllPurchasedCoupon() throws Exception {
		return this.customerDAO.getCustCoupons(this.customer);
	}

	public Set<Coupon> getAllPurchasedCouponByType(CouponType couponType) throws Exception {
		Set<Coupon> allCoupons = this.customerDAO.getCustCoupons(this.customer);
		for (Coupon c : allCoupons) {
			if (c.getCouponType() != couponType) {
				allCoupons.remove(c);
			}
		}
		return allCoupons;
	}

	public Set<Coupon> getAllPurchasedCouponByPrice(double price) throws Exception {
		Set<Coupon> allCoupons = this.customerDAO.getCustCoupons(this.customer);

		for (Coupon c : allCoupons) {
			if (c.getPrice() > price) {
				allCoupons.remove(c);
			}
		}
		return allCoupons;
	}

	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		// TODO Auto-generated method stub
		return null;
	}

}