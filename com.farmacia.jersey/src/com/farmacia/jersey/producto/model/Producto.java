package com.farmacia.jersey.producto.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement	

public class Producto {
	
	
	private String nombre;
	private String descripcion;
	private String id;
	
	public Producto() {
		// TODO Auto-generated constructor stub
	}

	
	public Producto(String nombre, String descripcion, String id) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
}
