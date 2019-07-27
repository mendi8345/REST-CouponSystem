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
import com.tomMendy.facade.AdminFacade;
import com.tomMendy.javabeans.Company;
import com.tomMendy.javabeans.Customer;

@Path("admin")
public class AdminService {
	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;
	Gson gson = new Gson();

	private AdminFacade getFacade() throws Exception {
		System.out.println("*************************************");

		try {
			System.out.println("*************************************");
			AdminFacade admin = null;
			admin = (AdminFacade) this.request.getSession(false).getAttribute("facade");
			return admin;
		} catch (Exception e) {
			return null;
		}
	}

	// Create a new company in the db
	@POST
	@Path("createCompany")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createCompany(String company) throws Exception {

		Gson gson = new Gson();
		Company companyFromJson = gson.fromJson(company, Company.class);

		AdminFacade adminFacade = getFacade();
		System.out.println(companyFromJson.toString());
		try {
			System.out.println("bbbbbbbbbbbb");
			adminFacade.createCompany(companyFromJson);
			System.out.println("jjjjjjjjjjjjjjjj");

			String res = "SUCCEDD TO CREATE NEW COMPANY " + companyFromJson;
			String reString = new Gson().toJson(res);

			return Response.status(Response.Status.OK).entity(reString).build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	// REMOVE a Company
	@DELETE
	@Path("/removeCompany/{companyId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeCompany(@PathParam("companyId") long id) throws Exception {

		AdminFacade adminFacade = getFacade();
		if (adminFacade != null) {
			Company company = null;
			System.out.println(id);
			company = adminFacade.getCompany(id);
			System.out.println(company.toString());
			if (company != null) {
				adminFacade.removeCompany(company);
				String res = "REMOVE COMPANY " + company.getCompName() + "SUCCESSFULLY";
				String clientRes = new Gson().toJson(res);
				return Response.status(Response.Status.OK).entity(clientRes).build();
			} else {
				String res = "There is no such company ";
				String clientRes = new Gson().toJson(res);
				return Response.status(Response.Status.BAD_REQUEST).entity(clientRes).build();
			}
		} else {
			String unauthorized = "Unauthorized!";
			String clientRes = new Gson().toJson(unauthorized);
			return Response.status(Response.Status.UNAUTHORIZED).entity(clientRes).build();
		}
	}

	// UPDATE a company
	@POST
	@Path("updateCompany")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCompany(String companyJson) throws Exception {

		AdminFacade adminFacade = getFacade();

		if (adminFacade != null) {
			Gson gson = new Gson();

			Company companyFromJson = gson.fromJson(companyJson, Company.class);

			System.out.println("Debug .. " + companyFromJson.getId() + "   " + companyFromJson.getCompName() + "  "
					+ companyFromJson.getEmail() + "  " + companyFromJson.getPassword());

			if ((companyFromJson.getId() > 0) && (companyFromJson != null)) {

				Company company = adminFacade.getCompany(companyFromJson.getId());
				company.toString();

				company.setPassword(companyFromJson.getPassword());
				company.setEmail(companyFromJson.getEmail());
				adminFacade.updateCompany(company);

				String res = "SUCCEED TO UPDATE COMPANY  " + company.getCompName();
				String clientRes = new Gson().toJson(res);
				return Response.status(Response.Status.OK).entity(clientRes).build();

			}
		}
		String res = "There is no company with the id that inserted, please try again! ";
		String clientRes = new Gson().toJson(res);
		return Response.status(Response.Status.BAD_REQUEST).entity(clientRes).build();

	}

	@GET
	@Path("getCompany/{companyId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCompany(@PathParam("companyId") long id) throws Exception {

		AdminFacade adminFacade = getFacade();
		if (adminFacade != null) {
			Company company = adminFacade.getCompany(id);
			System.out.println(company);
			if (company != null) {
				String clientRes = new Gson().toJson(company);
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
	@Path("getAllCompanies")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCompanies() throws Exception {

		AdminFacade adminFacade = getFacade();
		if (adminFacade != null) {
			Collection<Company> companies = adminFacade.getAllCompany();
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

	@POST
	@Path("createCustomer")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createCustomer(String customer) throws Exception {

		Gson gson = new Gson();
		Customer customerFromJson = gson.fromJson(customer, Customer.class);

		AdminFacade adminFacade = getFacade();
		System.out.println(customerFromJson.toString());
		try {
			System.out.println("bbbbbbbbbbbb");
			adminFacade.insertCustomer(customerFromJson);
			System.out.println("jjjjjjjjjjjjjjjj");

			String res = "SUCCEDD TO CREATE NEW CUSTOMER " + customerFromJson;
			String reString = new Gson().toJson(res);

			return Response.status(Response.Status.OK).entity(reString).build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	// REMOVE Customer
	@DELETE
	@Path("/removeCustomer/{customerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeCustomer(@PathParam("customerId") long id) throws Exception {

		AdminFacade adminFacade = getFacade();
		if (adminFacade != null) {
			Customer customer = null;
			System.out.println(id);
			customer = adminFacade.getCustomer(id);
			System.out.println(customer.toString());
			if (customer != null) {
				adminFacade.removeCustomer(customer);
				String res = "REMOVE CUSTOMER " + customer.getCustName() + "SUCCESSFULLY";
				String clientRes = new Gson().toJson(res);
				return Response.status(Response.Status.OK).entity(clientRes).build();
			} else {
				String res = "There is no such customer ";
				String clientRes = new Gson().toJson(res);
				return Response.status(Response.Status.BAD_REQUEST).entity(clientRes).build();
			}
		} else {
			String unauthorized = "Unauthorized!";
			String clientRes = new Gson().toJson(unauthorized);
			return Response.status(Response.Status.UNAUTHORIZED).entity(clientRes).build();
		}
	}

	// UPDATE a customer
	@POST
	@Path("updateCustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCustomer(String customerJson) throws Exception {

		AdminFacade adminFacade = getFacade();

		if (adminFacade != null) {
			Gson gson = new Gson();

			Customer customerFromJson = gson.fromJson(customerJson, Customer.class);

			System.out.println("Debug .. " + customerFromJson.getId() + "   " + customerFromJson.getCustName() + "  "
					+ customerFromJson.getPassword());

			if ((customerFromJson.getId() > 0) && (customerFromJson != null)) {

				Customer customer = adminFacade.getCustomer(customerFromJson.getId());
				System.out.println("HFFFHFGFHGFG" + customer);
				customer.setPassword(customerFromJson.getPassword());
				adminFacade.updateCustomer(customer);

				String res = "SUCCEED TO UPDATE Customer  " + customer.getCustName();
				String clientRes = new Gson().toJson(res);
				return Response.status(Response.Status.OK).entity(clientRes).build();

			}
		}
		String res = "There is no customer with the id that inserted, please try again! ";
		String clientRes = new Gson().toJson(res);
		return Response.status(Response.Status.BAD_REQUEST).entity(clientRes).build();

	}

	@GET
	@Path("getCustomer/{customerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCustomer(@PathParam("customerId") long id) throws Exception {

		AdminFacade adminFacade = getFacade();
		if (adminFacade != null) {
			Customer customer = adminFacade.getCustomer(id);
			System.out.println(customer);
			if (customer != null) {
				String clientRes = new Gson().toJson(customer);
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
	@Path("getAllCustomers")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCustomers() throws Exception {

		AdminFacade adminFacade = getFacade();
		if (adminFacade != null) {
			Collection<Customer> customers = adminFacade.getAllCustomer();
			if (customers != null) {
				String clientRes = new Gson().toJson(customers);
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

	// @GET
	// @Path("getAllCustomers")
	// @Produces(MediaType.APPLICATION_JSON)
	// public String getAllCustomers() throws Exception {
	//
	// // Using this method =
	// // http://localhost:8080/RestCouponSystem/rest/admin/getAllCustomers
	//
	// AdminFacade admin = getFacade();
	//
	// // Provide a List of all the Customers from the Table in the DataBase
	//
	// try {
	// Collection<Customer> customers = admin.getAllCustomer();
	//
	// return new Gson().toJson(customers);
	// } catch (Exception e) {
	// }
	// return null;
	// }

}