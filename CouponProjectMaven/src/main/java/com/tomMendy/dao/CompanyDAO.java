package com.tomMendy.dao;

import java.util.Set;

import com.tomMendy.javabeans.Company;
import com.tomMendy.javabeans.Coupon;

public interface CompanyDAO {

	void insertCompany(Company company) throws Exception;

	void removeCompany(Company company) throws Exception;

	void updateCompany(Company company) throws Exception;

	Company getCompany(long id) throws Exception;

	Set<Company> getAllCompany() throws Exception;

	Set<Coupon> getCompCoupons(Company company) throws Exception;

	boolean login(String compName, String password) throws Exception;

	long GetCompIdByName(String compName) throws Exception;

}