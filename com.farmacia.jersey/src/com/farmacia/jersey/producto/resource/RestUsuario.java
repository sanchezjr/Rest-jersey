package com.farmacia.jersey.producto.resource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
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
import com.farmacia.jersey.producto.model.ProductoDao;
import com.farmacia.jersey.usuario.model.Usuario;
import com.farmacia.jersey.usuario.model.UsuarioDao;
import com.farmacia.jersey.farmacia.model.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/usuario")
public class RestUsuario {


	@GET 
	@Path("/{usermail}")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Usuario> getUsuario(@PathParam("username") String username) throws SQLException {
		List<Usuario> listaUsuarios= new ArrayList<Usuario>();
		listaUsuarios = new UsuarioDao().getUsuario(String username);
		return listaUsuarios;
	}
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Usuario> getUsuarios() throws SQLException {
		List<Usuario> listaUsuarios= new ArrayList<Usuario>();
		listaUsuarios = new UsuarioDao().getUsuarios();
		return listaUsuarios;

	}
	@POST
	@Produces(MediaType.TEXT_HTML)	
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newUsuario(
	    @FormParam("nombre") String nombre,
	    @FormParam("direccion") String direccion,
	    @FormParam("tlfn") String tlfn,
	    @FormParam("email") String email,
	    @FormParam("pass") String pass,
	 	@Context HttpServletResponse servletResponse) throws SQLException, IOException {
		  UsuarioDao usuario = new UsuarioDao();
		  usuario.newUsuario(nombre, direccion, tlfn, email, pass);
	 
	 	 // servletResponse.sendRedirect("../crear_product.html");
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String newProducto(String obj) throws SQLException, IOException {

		 String nombre = "";
		 String direccion = "";  
		 String tlfn = ""; 
		 String email = ""; 
		 String pass = ""; 
		 
		 JsonParser jsonParser = new JsonParser();

		 JsonObject jsonObj = new JsonObject();
		 jsonObj = (JsonObject) jsonParser.parse(obj);
		 
		  nombre = jsonObj.get("nombre").toString();
		  direccion = jsonObj.get("direccion").toString();
		  tlfn = jsonObj.get("telefono").toString();
		  email = jsonObj.get("email").toString();
		  pass = jsonObj.get("pass").toString();
		  
		 String response = (nombre+direccion+tlfn+email+pass);
		 UsuarioDao usuario = new UsuarioDao();
		 nombre = nombre.replace("\"", "");
		 direccion = direccion.replace("\"", "");
		 tlfn = tlfn.replace("\"", "");
		 email = email.replace("\"", "");
		 pass = pass.replace("\"", "");
		  usuario.newUsuario(nombre, direccion, tlfn, email, pass);
		  
		  return response ;
	/// System.out.print(user);
	 	 // servletResponse.sendRedirect("../crear_product.html");
	 
	 
		// return HTTP response 200 in case of success
	//	return "ok";
	 }
    @POST
    @Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON) 
    // Query parameters are parameters: http://localhost/<appln-folder-name>/login/dologin?username=abc&password=xyz
    public String loguearse(String obj){
    	
    	String nombre = ""; 
		String pass = ""; 
		 
		JsonParser jsonParser = new JsonParser();
		JsonObject jsonObj = new JsonObject();
		jsonObj = (JsonObject) jsonParser.parse(obj);
		 
		nombre = jsonObj.get("nombre").toString();
		pass = jsonObj.get("pass").toString();
		
		nombre = nombre.replace("\"", "");
		pass = pass.replace("\"", "");
		
		System.out.println(nombre);
    	System.out.println(pass);
    	
		  
        String response = "";
        
        if(Utilidades.checkCredenciales(nombre, pass)){
            response = Utilidades.constructJSON("login",true);
        }else{
            response = Utilidades.constructJSON("login", false, "Incorrect Email or Password");
        }
    return response;        
    }
    @GET
    @Path("/login2")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON) 
    // Query parameters are parameters: http://localhost/<appln-folder-name>/login/dologin?username=abc&password=xyz
    public String loguearse2(@QueryParam("email") String user, @QueryParam("pass") String pass){
    	
    	System.out.println(user);
    	System.out.println(pass);
    	
        String response = "";
        
        if(Utilidades.checkCredenciales(user, pass)){
            response = Utilidades.constructJSON("login",true);
        }else{
            response = Utilidades.constructJSON("login", false, "Incorrect Email or Password");
        }
    return response;        
    }
     


}