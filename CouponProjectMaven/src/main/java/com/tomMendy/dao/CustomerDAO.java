package com.tomMendy.dao;

import java.util.Set;

import com.tomMendy.javabeans.Coupon;
import com.tomMendy.javabeans.Customer;

public interface CustomerDAO {

	void insertCustomer(Customer customer) throws Exception;

	void removeCustomer(Customer customer) throws Exception;

	void updateCustomer(Customer customer) throws Exception;

	Customer getCustomer(long id) throws Exception;

	Set<Customer> getAllCustomer() throws Exception;

	Set<Coupon> getCustCoupons(Customer customer) throws Exception;

	boolean login(String custName, String password) throws Exception;

	void associateCouponToCustomer(Customer customer, Coupon coupon) throws Exception;

	long GetCustIdByName(String custName) throws Exception;

}