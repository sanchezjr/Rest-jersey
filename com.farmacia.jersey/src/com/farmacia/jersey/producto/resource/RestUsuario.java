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
	public Usuario getUsuario(@PathParam("usermail") String username) throws SQLException {
		Usuario usuario= new Usuario();
		usuario = new UsuarioDao().getUsuario(username);
		System.out.println(username);
		return usuario;
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
    
   
    
    @PUT 
    @Path("update/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})	
    public String updateUsuarioById(String user) {
    	
		
    	String message; 
    	message = "we";
    	JsonParser jsonParser = new JsonParser();
		JsonObject jsonObj = new JsonObject();
		jsonObj = (JsonObject) jsonParser.parse(user);
    	
    	
    	String nombre = jsonObj.get("nombre").toString();
		String dire = jsonObj.get("direccion").toString();
		String tlfn = jsonObj.get("telefono").toString();
		String email = jsonObj.get("email").toString();
		String pass = jsonObj.get("pass").toString();
		String id = jsonObj.get("id").toString();
		
		
		System.out.println(nombre);
    	System.out.println(dire);
    	System.out.println(tlfn);
    	System.out.println(email);
    	System.out.println(pass);
    	System.out.println(id);
    	int id_nuevo=0;
    	UsuarioDao usuario = new UsuarioDao();
    	try {
			usuario.updateUsuarioById(1,nombre,dire,tlfn,email,pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//System.out.println(user.getEmail());
    	//System.out.println(telefono);
    	//System.out.println(email);
    	//System.out.println(pass);
    /*	int status; 
    	if(podcastWasUpdated(podcast)){
    		status = 200; //OK
    		message = "Podcast has been updated";
    	} else if(podcastCanBeCreated(podcast)){
    		podcastDao.createPodcast(podcast);
    		status = 201; //Created 
    		message = "The podcast you provided has been added to the database";
    	} else {
    		status = 406; //Not acceptable
    		message = "The information you provided is not sufficient to perform either an UPDATE or "
    				+ " an INSERTION of the new podcast resource <br/>"
    				+ " If you want to UPDATE please make sure you provide an existent <strong>id</strong> <br/>"
    				+ " If you want to insert a new podcast please provide at least a <strong>title</strong> and the <strong>feed</strong> for the podcast resource";
    	}
     
    	return Response.status(status).entity(message).build();		
    	*/
    	return message;	

    }
    
}