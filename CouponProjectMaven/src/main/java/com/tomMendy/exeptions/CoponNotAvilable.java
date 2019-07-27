package com.tomMendy.exeptions;

import com.tomMendy.javabeans.Coupon;

public class CoponNotAvilable extends Exception {
	private Coupon coupon;

	public CoponNotAvilable(Coupon coupon) {
		this.coupon = coupon;
	}

	@Override
	public String getMessage() {
		return "Coupon " + this.coupon.getTitle() + " not avilable !";
	}
}