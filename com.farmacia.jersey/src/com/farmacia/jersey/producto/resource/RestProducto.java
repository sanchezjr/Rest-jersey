package com.farmacia.jersey.producto.resource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.farmacia.jersey.jdbc.*;
import com.farmacia.jersey.producto.dao.ProductoDao;
import com.farmacia.jersey.producto.model.*;

@Path("/productos")
public class RestProducto {


	@GET 
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Producto> getProducto() throws SQLException {
		List<Producto> courseList = new ArrayList<Producto>();
		courseList = new ProductoDao().getProductos();
		return courseList;
	}
	@GET
    @Path("/{id}")  
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Producto getProducto(@PathParam("id") int id) throws SQLException {
		Producto producto = new Producto();
		producto = new ProductoDao().getProductos(id);
		return producto;
	}
	

}