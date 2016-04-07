package com.farmacia.jersey.producto.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.farmacia.jersey.producto.dao.ProductoDao;
import com.farmacia.jersey.producto.model.Producto;

//Will map the resource to the URL productos
@Path("/productos")
public class ProductosResource {

	// Allows to insert contextual objects into the class,
	  // e.g. ServletContext, Request, Response, UriInfo
	  @Context
	  UriInfo uriInfo;
	  @Context
	  Request request;

	  // Return the list of productos to the user in the browser
	  @GET
	  @Produces(MediaType.TEXT_XML)
	  public List<Producto> getProductosBrowser() {
		    List<Producto> productos = new ArrayList<Producto>();
		    productos.addAll(ProductoDao.instance.getModel().values());
		    return productos;
		  }
	  // Return the list of productos for applications
	  @GET
	  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	  public List<Producto> getProductos() {
	    List<Producto> productos = new ArrayList<Producto>();
	    productos.addAll(ProductoDao.instance.getModel().values());
	    return productos;
	  }

	  // retuns the number of productos
	  // Use http://localhost:8080/com.vogella.jersey.producto/rest/productos/count
	  // to get the total number of records
	  @GET
	  @Path("count")
	  @Produces(MediaType.TEXT_PLAIN)
	  public String getCount() {
	    int count = ProductoDao.instance.getModel().size();
	    return String.valueOf(count);
	  }

	  @POST
	  @Produces(MediaType.TEXT_HTML)
	  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	  public void newProducto(@FormParam("id") String id,
	      @FormParam("nombre") String nombre,
	      @FormParam("descripcion") String descripcion,
	      @Context HttpServletResponse servletResponse) throws IOException {
	    Producto producto = new Producto(nombre,descripcion,id);
	    if (descripcion != null) {
	      producto.setDescripcion(descripcion);
	    }
	    ProductoDao.instance.getModel().put(id, producto);

	    servletResponse.sendRedirect("../crear_product.html");
	  }

	  // Defines that the next path parameter after productos is
	  // treated as a parameter and passed to the ProductoResources
	  // Allows to type http://localhost:8080/com.vogella.jersey.producto/rest/productos/1
	  // 1 will be treaded as parameter producto and passed to ProductoResource
	  @Path("{producto}")
	  public ProductoResource getProducto(@PathParam("producto") String id) {
	    return new ProductoResource(uriInfo, request, id);
	  }

	}
