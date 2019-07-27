package com.tomMendy.services;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.tomMendy.facade.CompanyFacade;
import com.tomMendy.javabeans.Coupon;
import com.tomMendy.utils.DateUtils;

@Path("company")
public class CompanyService {
	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;

	private CompanyFacade getFacade() {
		System.out.println("*************************************");

		try {
			System.out.println("*************************************");
			CompanyFacade companyFacade = null;
			companyFacade = (CompanyFacade) this.request.getSession(false).getAttribute("facade");
			return companyFacade;
		} catch (Exception e) {
			return null;
		}
	}

	@POST
	@Path("createCoupon")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCoupon(String coupon) throws Exception {
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
		CompanyFacade companyFacade = getFacade();
		System.out.println(couponFromJson.toString());
		try {
			System.out.println("2222222");
			companyFacade.createCoupon(couponFromJson);
			System.out.println("2222222");

			String res = "SUCCEDD TO CREATE NEW COUPON " + couponFromJson;
			String reString = new Gson().toJson(res);

			return Response.status(Response.Status.OK).entity(reString).build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@DELETE
	@Path("/removeCoupon/{couponId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeCoupon(@PathParam("couponId") long id) throws Exception {

		CompanyFacade companyFacade = getFacade();
		if (companyFacade != null) {
			Coupon coupon = null;
			System.out.println(id);
			coupon = companyFacade.getCoupon(id);
			System.out.println(coupon.toString());
			if (coupon != null) {
				companyFacade.removeCoupon(coupon);
				String res = "REMOVE COUPON " + coupon.getTitle() + "SUCCESSFULLY";
				String clientRes = new Gson().toJson(res);
				return Response.status(Response.Status.OK).entity(clientRes).build();
			} else {
				String res = "There is no such coupon ";
				String clientRes = new Gson().toJson(res);
				return Response.status(Response.Status.BAD_REQUEST).entity(clientRes).build();
			}
		} else {
			String unauthorized = "Unauthorized!";
			String clientRes = new Gson().toJson(unauthorized);
			return Response.status(Response.Status.UNAUTHORIZED).entity(clientRes).build();
		}
	}

	// UPDATE a coupon
	@POST
	@Path("updateCoupon")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCoupon(String couponJson) throws Exception {

		CompanyFacade companyFacade = getFacade();

		if (companyFacade != null) {
			Gson gson = new Gson();

			Coupon couponFromJson = gson.fromJson(couponJson, Coupon.class);

			System.out.println("Debug .. " + couponFromJson.getId() + "   " + couponFromJson.getTitle() + "  "
					+ couponFromJson.getAmount() + "  " + couponFromJson.getPrice());

			if ((couponFromJson.getId() > 0) && (couponFromJson != null)) {

				Coupon coupon = companyFacade.getCoupon(couponFromJson.getId());
				coupon.toString();
				coupon.setTitle(couponFromJson.getTitle());
				coupon.setAmount(couponFromJson.getAmount());
				coupon.setPrice(couponFromJson.getPrice());
				coupon.setImage(couponFromJson.getImage());

				companyFacade.updateCoupon(coupon);

				String res = "SUCCEED TO UPDATE COUPON  " + coupon.getTitle();
				String clientRes = new Gson().toJson(res);
				return Response.status(Response.Status.OK).entity(clientRes).build();

			}
		}
		String res = "There is no coupon with the id that inserted, please try again! ";
		String clientRes = new Gson().toJson(res);
		return Response.status(Response.Status.BAD_REQUEST).entity(clientRes).build();

	}

	@GET
	@Path("getCoupon/{couponId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCoupon(@PathParam("couponId") long id) throws Exception {

		CompanyFacade companyFacade = getFacade();
		if (companyFacade != null) {
			Coupon coupon = companyFacade.getCoupon(id);
			System.out.println(coupon);
			if (coupon != null) {
				String clientRes = new Gson().toJson(coupon);
				return Response.status(Response.Status.OK).entity(clientRes).build();
			} else {
				String res = "There is no companies in DB";
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
	@Path("getCompanyCoupons")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCompenyCoupons() throws Exception {

		CompanyFacade companyFacade = getFacade();
		if (companyFacade != null) {
			Collection<Coupon> companies = companyFacade.getCompCoupons();
			if (companies != null) {
				String clientRes = new Gson().toJson(companies);
				return Response.status(Response.Status.OK).entity(clientRes).build();
			} else {
				String res = "There is no companies in DB";
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
