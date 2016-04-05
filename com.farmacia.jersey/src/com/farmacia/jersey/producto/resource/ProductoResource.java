package com.farmacia.jersey.producto.resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import dao.ProductoDao;
import model.Producto;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Producto;


public class ProductoResource {

	  @Context
	  UriInfo uriInfo;
	  @Context
	  Request request;
	  String id;
	  public ProductoResource(UriInfo uriInfo, Request request, String id) {
	    this.uriInfo = uriInfo;
	    this.request = request;
	    this.id = id;
	  }
	  //Application integration     
	  @GET
	  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	  public Producto getProducto() {
	    Producto producto = ProductoDao.instance.getModel().get(id);
	    if(producto==null)
	      throw new RuntimeException("Get: Producto with " + id +  " not found");
	    return producto;
	  }
	  
	  // for the browser
	  @GET
	  @Produces(MediaType.TEXT_XML)
	  public Producto getProductoHTML() {
	    Producto producto = ProductoDao.instance.getModel().get(id);
	    if(producto==null)
	      throw new RuntimeException("Get: Producto with " + id +  " not found");
	    return producto;
	  }
	  
	  @PUT
	  @Consumes(MediaType.APPLICATION_XML)
	  public Response putProducto(JAXBElement<Producto> producto) {
	    Producto c = producto.getValue();
	    return putAndGetResponse(c);
	  }
	  
	  @DELETE
	  public void deleteProducto() {
	    Producto c = ProductoDao.instance.getModel().remove(id);
	    if(c==null)
	      throw new RuntimeException("Delete: Producto with " + id +  " not found");
	  }
	  
	  private Response putAndGetResponse(Producto producto) {
	    Response res;
	    if(ProductoDao.instance.getModel().containsKey(producto.getId())) {
	      res = Response.noContent().build();
	    } else {
	      res = Response.created(uriInfo.getAbsolutePath()).build();
	    }
	    ProductoDao.instance.getModel().put(producto.getId(), producto);
	    return res;
	  }
	  
	  

	} 
	  
	