package com.farmacia.jersey.producto.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Producto {

	private String nombre;
	private String descripcion;
	private String id;
	private int precio;
	private int stock;
	
	public Producto() {
		// TODO Auto-generated constructor stub
	}
	public Producto(String nombre, String descripcion, String id, int precio, int stock) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.id = id;
		this.precio = precio;
		this.stock = stock;
	}
	
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
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
