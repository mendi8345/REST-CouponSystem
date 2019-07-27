package com.tomMendy.services;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.tomMendy.facade.CouponClientFacade;
import com.tomMendy.javabeans.ClientType;
import com.tomMendy.main.CouponSystem;

@Path("/login")
public class LoginServlet {

	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;

	CouponSystem couponSystem = null;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.APPLICATION_JSON)
	public Response login(@QueryParam("name") String name, @QueryParam("password") String password,
			@QueryParam("type") String type) throws Exception {

		Gson gson = new Gson();

		// Getting system instance(For debug)
		this.couponSystem = CouponSystem.getInstance();

		// getting the data from the register form as JSON and parsing to Pojo
		ClientType clientType = ClientType.valueOf(type);

		// Login to the system (Checking throw DB)
		CouponClientFacade facade = this.couponSystem.login(name, password, clientType);

		// Checking whether there is a open session
		HttpSession session = this.request.getSession(false);
		if (session != null) {
			session.invalidate(); // killing the session if exist
		}
		session = this.request.getSession(true); // create a new session for a new client

		// Prints for debugs !
		// System.out.println(facade );
		// System.out.println(session.getId() + " * " +
		// session.getMaxInactiveInterval());

		if (facade != null) {
			System.out.println("Facade debug - OK");

			// updating the session with the login facade
			session.setAttribute("facade", facade);

			// response.addCookie(cookie);
			Cookie cookie = new Cookie("Set-Cookie", "JSESSIONID=" + this.request.getSession().getId()
					+ ";path=/RestCouponSystem/; HttpOnly; domain=/localhost; secure=false;");
			cookie.setComment(type);
			String goodResponse = new Gson().toJson(cookie);

			switch (clientType) {
			case admin:
				return Response.status(Response.Status.OK).entity(goodResponse).build();

			case company:
				return Response.status(Response.Status.OK).entity(goodResponse).build();

			case customer:
				return Response.status(Response.Status.OK).entity(goodResponse).build();

			}
		}
		String responseToJson = "Failed to auth, please try again. ";
		String badResponse = new Gson().toJson(responseToJson);
		return Response.status(Response.Status.UNAUTHORIZED).entity(badResponse).build();
	}

}