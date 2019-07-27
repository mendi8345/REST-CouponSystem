package com.tomMendy.services;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.tomMendy.facade.CustomerFacade;
import com.tomMendy.javabeans.Coupon;
import com.tomMendy.javabeans.CouponType;
import com.tomMendy.utils.DateUtils;

@Path("customer")
public class CustomerService {
	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;

	private CustomerFacade getFacade() {
		System.out.println("*************************************");

		try {
			System.out.println("*************************************");
			CustomerFacade customerFacade = null;
			customerFacade = (CustomerFacade) this.request.getSession(false).getAttribute("facade");
			return customerFacade;
		} catch (Exception e) {
			return null;
		}
	}

	@POST
	@Path("purchaseCoupon")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response purchaseCoupon(String coupon) throws Exception {
		System.out.println("0000000000");

		Gson gson = new Gson();
		// DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		// String convertedDate = sdf.format(DateUtils.GetCurrentDate());
		// Date date = sdf.parse(sdf.format(DateUtils.GetCurrentDate()));

		Coupon couponFromJson = gson.fromJson(coupon, Coupon.class);
		couponFromJson.setStartDate(DateUtils.GetCurrentDate());
		couponFromJson.setEndDate(DateUtils.GetEndDate());
		System.out.println("couponFromJson " + couponFromJson.getCouponType());
		System.out.println("11111111");
		CustomerFacade customerFacade = getFacade();
		System.out.println(couponFromJson.toString());
		try {
			System.out.println("2222222");
			customerFacade.purchaseCoupon(couponFromJson);
			System.out.println("2222222");

			String res = "SUCCEDD TO purchase a NEW COUPON " + couponFromJson;
			String reString = new Gson().toJson(res);

			return Response.status(Response.Status.OK).entity(reString).build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@GET
	@Path("getAllPurchasedCoupon")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCompenyCoupons() throws Exception {

		CustomerFacade customerFacade = getFacade();
		if (customerFacade != null) {
			Collection<Coupon> companies = customerFacade.getAllPurchasedCoupon();
			if (companies != null) {
				String clientRes = new Gson().toJson(companies);
				return Response.status(Response.Status.OK).entity(clientRes).build();
			} else {
				String res = "There is no customers in DB";
				String clientRes = new Gson().toJson(res);
				return Response.status(Response.Status.OK).entity(clientRes).build();
			}
		} else {
			String unauthorized = "Unauthorized!";
			String clientRes = new Gson().toJson(unauthorized);
			return Response.status(Response.Status.UNAUTHORIZED).entity(clientRes).build();
		}
	}

	@GET
	@Path("getAllPurchasedCouponByPrice/{price}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllPurchasedCouponByPrice(@PathParam("price") double price) throws Exception {

		CustomerFacade customerFacade = getFacade();
		if (customerFacade != null) {
			Collection<Coupon> customers = customerFacade.getAllPurchasedCouponByPrice(price);
			if (customers != null) {
				String clientRes = new Gson().toJson(customers);
				return Response.status(Response.Status.OK).entity(clientRes).build();
			} else {
				String res = "There is no customers in DB";
				String clientRes = new Gson().toJson(res);
				return Response.status(Response.Status.OK).entity(clientRes).build();
			}
		} else {
			String unauthorized = "Unauthorized!";
			String clientRes = new Gson().toJson(unauthorized);
			return Response.status(Response.Status.UNAUTHORIZED).entity(clientRes).build();
		}
	}

	@GET
	@Path("getAllPurchasedCouponByType/{couponType}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllPurchasedCouponByPrice(@PathParam("couponType") String type) throws Exception {

		CustomerFacade customerFacade = getFacade();

		if (customerFacade != null) {
			CouponType couponType = CouponType.valueOf(type);
			Collection<Coupon> companies = customerFacade.getAllPurchasedCouponByType(couponType);
			if (companies != null) {
				String clientRes = new Gson().toJson(companies);
				return Response.status(Response.Status.OK).entity(clientRes).build();
			} else {
				String res = "There is no customers in DB";
				String clientRes = new Gson().toJson(res);
				return Response.status(Response.Status.OK).entity(clientRes).build();
			}
		} else {
			String unauthorized = "Unauthorized!";
			String clientRes = new Gson().toJson(unauthorized);
			return Response.status(Response.Status.UNAUTHORIZED).entity(clientRes).build();
		}
	}
}
