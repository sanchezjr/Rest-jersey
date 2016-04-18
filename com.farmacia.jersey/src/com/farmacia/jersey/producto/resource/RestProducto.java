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
import com.farmacia.jersey.producto.dao.ProductoDao;
import com.farmacia.jersey.producto.model.*;
import com.google.gson.Gson;

@Path("/productos")
public class RestProducto {


	@GET 
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Producto> getProducto() throws SQLException {
		List<Producto> courseList = new ArrayList<Producto>();
		courseList = new ProductoDao().getProductos();
		return courseList;
	}
	
	@Path("/product")
	@GET 
	@Produces("application/json")
	public String getProduct() throws SQLException {
		JSONArray productosList = new JSONArray();
		productosList = new ProductoDao().getProductosJson();
	//	System.out.println(productosList);
		JSONObject obj = new JSONObject();
		JSONArray list = new JSONArray();
		/*for(int i=0; i< courseList.size();i++){
           System.out.println(courseList.get(i));
            list.put(courseList.get(i));
		}*/
		JSONObject res = new JSONObject();
		res.put("productos", productosList);
		System.out.println(res);
		
		Gson gson = new Gson();
	   // System.out.println(gson.toJson(res));
	   // return gson.toJson(res);

		
		//productosList.toString();
		return res.toString();
	}
	
	@GET
    @Path("/{id}")  
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Producto getProducto(@PathParam("id") int id) throws SQLException {
		Producto producto = new Producto();
		producto = new ProductoDao().getProductos(id);
		return producto;
	}
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newProducto(@FormParam("id") String id,
	    @FormParam("nombre") String nombre,
	    @FormParam("descripcion") String descripcion,
	 	@Context HttpServletResponse servletResponse) throws SQLException, IOException {
		  ProductoDao producto = new ProductoDao();
		  producto.newProducto(id, nombre, descripcion);
	 
	 	  servletResponse.sendRedirect("../crear_product.html");
	 }
	
	@DELETE
    @Path("/{id}")  
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Producto deleteProducto(@PathParam("id") int id) throws SQLException {
		Producto producto = new Producto();
		producto = new ProductoDao().getProductos(id);
		return producto;
	}

}