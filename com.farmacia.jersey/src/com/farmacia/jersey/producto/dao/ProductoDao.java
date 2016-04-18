package com.farmacia.jersey.producto.dao;

import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import com.farmacia.jersey.producto.model.Producto;
import com.farmacia.jersey.jdbc.DbConnection;
import com.farmacia.jersey.jdbc.DbUtil;

public class ProductoDao {

    private Connection connection;
    private Statement statement;
 
    public ProductoDao() { }
 
    public List<Producto> getProductos() throws SQLException {
        String query = "SELECT * FROM productos";
        List<Producto> list = new ArrayList<Producto>();
        Producto producto = null;
        ResultSet rs = null;
        try {
            connection = DbConnection.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                producto = new Producto();
                /*Retrieve one employee details 
                and store it in employee object*/
                producto.setId(rs.getString("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));

 
                //add each employee to the list
                list.add(producto);
            }
        } finally {
            DbUtil.close(rs);
            DbUtil.close(statement);
            DbUtil.close(connection);
        }
        return list;
    }
    
    public JSONArray getProductosJson() throws SQLException {
        String query = "SELECT * FROM productos";
        List<Producto> list = new ArrayList<Producto>();
        Producto producto = null;
        ResultSet rs = null;
		JsonObjectBuilder jsonConstructor = Json.createObjectBuilder();
		//JSONObject obj = new JSONObject();
		JSONArray arra = new JSONArray();
		
        try {
            connection = DbConnection.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                producto = new Producto();
                JSONObject obj = new JSONObject();
                /*Retrieve one employee details 
                and store it in employee object*/
                producto.setId(rs.getString("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
               
                obj.put("id", rs.getString("id"));
                obj.put("nombre", rs.getString("nombre"));
                obj.put("descripcion", rs.getString("descripcion"));
                //add each employee to the list
                list.add(producto);
                
                arra.put(obj);
            }
        } finally {
            DbUtil.close(rs);
            DbUtil.close(statement);
            DbUtil.close(connection);
        }
        JsonObject objetoListo = jsonConstructor.build();
      
        return arra;
    }
    
    
    public Producto getProductos(int productoId) throws SQLException {
       // String query = "SELECT * FROM productos WHERE id LIKE '1'";
        String query = "SELECT * FROM productos WHERE id LIKE "+ productoId;

        Producto producto = null;
        ResultSet rs = null;
        try {
            connection = DbConnection.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next()) {
                producto = new Producto();
                /*Retrieve one employee details 
                and store it in employee object*/
                producto.setId(rs.getString("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));

            }
        } finally {
            DbUtil.close(rs);
            DbUtil.close(statement);
            DbUtil.close(connection);
        }
        return producto;
    }
    
    public void newProducto(String productoId, String nombre, String descripcion) throws SQLException {
        // String query = "SELECT * FROM productos WHERE id LIKE '1'";
         String query = "INSERT INTO productos(id, nombre, descripcion) VALUES ("+productoId+","+nombre+","+descripcion+")";

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
    public void deleteProducto(String productoId) throws SQLException {
        // String query = "SELECT * FROM productos WHERE id LIKE '1'";
         String query = "DELETE FROM `productos` WHERE id LIKE "+productoId;

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
	
	/*	 instance;
	private Map<String, Producto> contentProvider = new HashMap<>();
	  
	  private ProductoDao() {
	    
	    Producto producto = new Producto("Paracetamol", "para el dolor de cabeza","1");
	    contentProvider.put("1", producto);
	    producto = new Producto("Jarabe", "Para la tos","2");
	    contentProvider.put("2", producto);
	    
	  }
	  public Map<String, Producto> getModel(){
	    return contentProvider;
	  }
	  
*/
}
