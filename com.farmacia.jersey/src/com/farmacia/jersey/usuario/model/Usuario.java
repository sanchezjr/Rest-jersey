package com.farmacia.jersey.usuario.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Usuario {

	private int id_usuario=0;
	private String nombre;
	private String dire;
	private String tlfn;
	private String email;
	private String pass;
	
	public Usuario( String nombre, String dire, String tlfn,
			String email, String pass) {
		super();
		
		this.nombre = nombre;
		this.dire = dire;
		this.tlfn = tlfn;
		this.email = email;
		this.pass = pass;
	}

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDire() {
		return dire;
	}

	public void setDire(String dire) {
		this.dire = dire;
	}

	public String getTlfn() {
		return tlfn;
	}

	public void setTlfn(String tlfn) {
		this.tlfn = tlfn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
	
}
