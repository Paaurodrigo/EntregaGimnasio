package com.hibernate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	public Ejercicio() {
		super();
	}
	
	public Ejercicio(String nomEjercicio, double peso,int series,int reps,String dificultad) {
		super();
		this.nomEjercicio = nomEjercicio;
		this.peso = peso;
		this.series = series;
		this.reps = reps;
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



	
}
