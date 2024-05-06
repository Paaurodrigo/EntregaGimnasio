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
@Table(name = "Ejercicio")
public class Ejercicio {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idjercicio")
	private int idEjercicio;
	
	@Column(name = "Nombre")
	private String nomEjercicio;
	
	@Column(name = "Peso")
	private double peso;	

	@Column(name = "Series")
	private int series;

	@Column(name = "Repeticiones")
	private int reps;
	
	@Column(name = "Descanso")
	private String descanso;
	
	@Column(name = "Dificultad")
	private String dificultad;
	
	@ManyToMany   //(cascade = CascadeType.ALL)
	@JoinTable(
			name = "ejercicio_cliente", //Se crea automaticamente...
			joinColumns = @JoinColumn(name = "idEjercicio"),
            inverseJoinColumns = @JoinColumn(name = "idCliente")
			  )
	private List<Cliente> clientes=new ArrayList<Cliente>();
	
	
	
	public Ejercicio() {
		super();
	}
	
	public Ejercicio(String nomEjercicio, double peso,int series,int reps,String descanso,String dificultad) {
		super();
		this.nomEjercicio = nomEjercicio;
		this.peso = peso;
		this.series = series;
		this.reps = reps;
		this.descanso=descanso;
		this.dificultad = dificultad;
	}

	public int getIdEjercicio() {
		return idEjercicio;
	}

	public void setIdEjercicio(int idEjercicio) {
		this.idEjercicio = idEjercicio;
	}

	public String getNomEjercicio() {
		return nomEjercicio;
	}

	public void setNomEjercicio(String nomEjercicio) {
		this.nomEjercicio = nomEjercicio;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public int getSeries() {
		return series;
	}

	public void setSeries(int series) {
		this.series = series;
	}

	public int getReps() {
		return reps;
	}

	public void setReps(int reps) {
		this.reps = reps;
	}

	public String getDescanso() {
		return descanso;
	}

	public void setDescanso(String descanso) {
		this.descanso = descanso;
	}

	public String getDificultad() {
		return dificultad;
	}

	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}
	
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	public void anyadirCliente(Cliente cl) {
		this.clientes.add(cl);
		cl.getEjercicios().add(this);
	}
	
	public void quitarCliente(Cliente cl) {
		this.clientes.remove(cl);
		cl.getEjercicios().remove(this);
	}


	
}
