package com.farmacia.jersey.producto.resource;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONArray;
import org.json.JSONObject;

import com.farmacia.jersey.jdbc.*;

import com.farmacia.jersey.farmacia.model.*;
import com.google.gson.Gson;

@Path("/farmacia")
public class RestFarmacia {


	@GET 
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Farmacia> getFarmacias() throws SQLException {
		List<Farmacia> courseList = new ArrayList<Farmacia>();
		courseList = new FarmaciaDao().getFarmacias();
		return courseList;

	}

}