package com.tomMendy.facade;

import com.tomMendy.javabeans.ClientType;

public interface CouponClientFacade {

	public CouponClientFacade login(String name, String password, ClientType clientType);

}
