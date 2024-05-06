package com.hibernate.model;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cliente")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idcliente")
	private int idCliente;
	
	@Column(name = "Nombre")
	private String nomCliente;
	
	@Column(name = "Apellidos")
	private String apeCliente;
	
	@Column(name = "Fecha Nacimiento")
	private String fechaNac;
	
	@Column(name = "Lesiones")
	private String lesiones;
	
	@Column(name = "Objetivo")
	private String objetivo;
	
	@Column(name = "idEntrenador")
	private int idEntrenador;
	
	
	@ManyToMany    //(cascade = CascadeType.ALL)
	@JoinTable(
			name="ejercicio_cliente",  //Se crea automaticamente...
			joinColumns = @JoinColumn(name = "idCliente"),
		    inverseJoinColumns = @JoinColumn(name = "idEjercicio")
			  )
	private List<Ejercicio> ejercicios=new ArrayList<Ejercicio>();
	


	public Cliente() {
		super();
	}
	
	public Cliente(String nomCliente, String apeCliente,String fechaNac,String lesiones,String objetivo,int idEntrenador) {
		super();
		this.nomCliente = nomCliente;
		this.apeCliente = apeCliente;
		this.fechaNac = fechaNac;
		this.lesiones = lesiones;
		this.objetivo = objetivo;
		this.idEntrenador = idEntrenador;
	
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomCliente() {
		return nomCliente;
	}

	public void setNomCliente(String nomCliente) {
		this.nomCliente = nomCliente;
	}

	public String getApeCliente() {
		return apeCliente;
	}

	public void setApeCliente(String apeCliente) {
		this.apeCliente = apeCliente;
	}

	public String getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getLesiones() {
		return lesiones;
	}

	public void setLesiones(String lesiones) {
		this.lesiones = lesiones;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public int getIdEntrenador() {
		return idEntrenador;
	}

	public void setIdEntrenador(int idEntrenador) {
		this.idEntrenador = idEntrenador;
	}

	public List<Ejercicio> getEjercicios() {
		return ejercicios;
	}

	public void setEjercicios(List<Ejercicio> ejercicios) {
		this.ejercicios = ejercicios;
	}
	
	public void anyadirEjercicio(Ejercicio ej) {
		this.ejercicios.add(ej);
		ej.getClientes().add(this);
	}
	
	public void quitarProfesion(Ejercicio ej) {
		this.ejercicios.remove(ej);
		ej.getClientes().remove(this);
	}
	
}
