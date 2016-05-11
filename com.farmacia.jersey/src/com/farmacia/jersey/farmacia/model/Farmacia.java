package com.farmacia.jersey.farmacia.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Farmacia {

	private int id_farmacia;
	private String direccion;
	private String titulo;
	private String descripcion;
	public Farmacia() {
		// TODO Auto-generated constructor stub
	}
	public Farmacia(int id_farmacia, String direccion, String titulo,
			String descripcion) {
		super();
		this.id_farmacia = id_farmacia;
		this.direccion = direccion;
		this.titulo = titulo;
		this.descripcion = descripcion;
	}
	public int getId_farmacia() {
		return id_farmacia;
	}
	public void setId_farmacia(int id_farmacia) {
		this.id_farmacia = id_farmacia;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
