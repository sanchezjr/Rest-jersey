package com.farmacia.jersey.producto.dao;

import java.util.HashMap;
import java.util.Map;

import com.farmacia.jersey.producto.model.Producto;


public enum ProductoDao {
	 instance;

	  
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
	  

}
