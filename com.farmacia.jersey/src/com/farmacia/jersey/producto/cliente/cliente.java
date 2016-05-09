package com.farmacia.jersey.producto.cliente;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.farmacia.jersey.producto.model.Producto;;

public class cliente {

	 public static void main(String[] args) {


		    ClientConfig config = new ClientConfig();
		    Client client = ClientBuilder.newClient(config);
		    WebTarget service = client.target(getBaseURI());

		    // create one producto
		    Producto producto = new Producto("3", "producto 1", "Descripcion 1",2,2);
		    Response response = service.path("rest").path("productos").path(producto.getId()).request(MediaType.APPLICATION_XML).put(Entity.entity(producto,MediaType.APPLICATION_XML),Response.class);
		    System.out.println("hello");

		    // Return code should be 201 == created resource
		    System.out.println(response.getStatus());

		    // Get the Productos
		    System.out.println(service.path("rest").path("productos").request().accept(MediaType.TEXT_XML).get(String.class));

//		    // Get JSON for application
//		    System.out.println(service.path("rest").path("productos").request().accept(MediaType.APPLICATION_JSON).get(String.class));

		    // Get XML for application
		    System.out.println(service.path("rest").path("productos").request().accept(MediaType.APPLICATION_XML).get(String.class));

		    //Get Producto with id 1
		    Response checkDelete = service.path("rest").path("productos/1").request().accept(MediaType.APPLICATION_XML).get();

		    //Delete Producto with id 1
		    service.path("rest").path("productos/1").request().delete();

		    //Get get all Productos id 1 should be deleted
		    System.out.println(service.path("rest").path("productos").request().accept(MediaType.APPLICATION_XML).get(String.class));

		    //Create a Producto
		    Form form =new Form();
		    form.param("id", "4");
		    form.param("nombre","Demonstration of the client lib for forms");
		    form.param("descripcion","descripcion 1	");
		    response = service.path("rest").path("productos").request().post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED),Response.class);
		    System.out.println("Form response " + response.getStatus());

		    //Get all the productos, id 4 should have been created
		    System.out.println(service.path("rest").path("productos").request().accept(MediaType.APPLICATION_XML).get(String.class));

		  }

		  private static URI getBaseURI() {
		    return UriBuilder.fromUri("http://localhost:8081/com.farmacia.jersey").build();
		  }

}
