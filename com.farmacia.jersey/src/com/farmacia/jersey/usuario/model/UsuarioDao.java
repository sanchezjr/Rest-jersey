package com.farmacia.jersey.usuario.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.farmacia.jersey.usuario.model.Usuario;
import com.farmacia.jersey.jdbc.DbConnection;
import com.farmacia.jersey.jdbc.DbUtil;

public class UsuarioDao {
	
	 private Connection connection;
	    private Statement statement;
	 
	    public UsuarioDao() { }
	    
	    public Usuario getUsuario(String usuario) throws SQLException {
	        String query = "SELECT * FROM usuarios where email LIKE '"+usuario+"'";
	       
	        Usuario user = null;
	        ResultSet rs = null;
	        try {
	            connection = DbConnection.getConnection();
	            statement = connection.createStatement();
	            rs = statement.executeQuery(query);
	            while (rs.next()) {
	            	user = new Usuario();
	                /*Retrieve one employee details 
	                and store it in employee object*/
	            	user.setId_usuario(rs.getInt("id_usuario"));
	            	user.setNombre(rs.getString("nombre"));
	            	user.setDire(rs.getString("direccion"));
	            	user.setTlfn(rs.getString("telefono"));
	            	user.setEmail(rs.getString("email"));

	            }
	        } finally {
	            DbUtil.close(rs);
	            DbUtil.close(statement);
	            DbUtil.close(connection);
	        }
	        return user;
	    }
	    
	    
	    public List<Usuario> getUsuarios() throws SQLException {
	        String query = "SELECT * FROM usuarios";
	        List<Usuario> list = new ArrayList<Usuario>();
	        Usuario usuario = null;
	        ResultSet rs = null;
	        try {
	            connection = DbConnection.getConnection();
	            statement = connection.createStatement();
	            rs = statement.executeQuery(query);
	            while (rs.next()) {
	                usuario = new Usuario();
	                /*Retrieve one employee details 
	                and store it in employee object*/
	                usuario.setId_usuario(rs.getInt("id_usuario"));
	                usuario.setNombre(rs.getString("nombre"));
	                usuario.setDire(rs.getString("direccion"));
	                usuario.setTlfn(rs.getString("telefono"));
	                usuario.setEmail(rs.getString("email"));
	                usuario.setPass(rs.getString("pass"));
	 
	                //add each employee to the list
	                list.add(usuario);
	            }
	        } finally {
	            DbUtil.close(rs);
	            DbUtil.close(statement);
	            DbUtil.close(connection);
	        }
	        return list;
	    }
	    
	    public void newUsuario(String nombre, String direccion, String telef, String email, String pass) throws SQLException {
	        // String query = "SELECT * FROM productos WHERE id LIKE '1'";
	         String query = "INSERT INTO usuarios(nombre, direccion, telefono, email, pass) VALUES ('"+nombre+"','"+direccion+"','"+telef+"','"+email+"','"+pass+"')";

	       //  Producto producto = null;
	         ResultSet rs = null;
	         try {
	             connection = DbConnection.getConnection();
	             statement = connection.createStatement();
	             statement.executeUpdate(query);

	         } finally {
	             DbUtil.close(rs);
	             DbUtil.close(statement);
	             DbUtil.close(connection);
	         }
	     }

	    public boolean login(String nombre, String pass) throws Exception {
	    	boolean registrado = false;
	        String query = "SELECT * FROM usuarios WHERE nombre = '" + nombre
                    + "' AND pass=" + "'" + pass + "'";

	        ResultSet rs = null;
	        try {
	            connection = DbConnection.getConnection();
	            statement = connection.createStatement();
	            rs = statement.executeQuery(query);
	            while (rs.next()) {
	            	/*la identificacion es correcta*/
	            	registrado = true;
	            }
	        } finally {
	            DbUtil.close(rs);
	            DbUtil.close(statement);
	            DbUtil.close(connection);
	        }
	        return registrado;
	    }
}
