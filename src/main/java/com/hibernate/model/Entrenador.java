package com.hibernate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Entrenador")
public class Entrenador {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idEntrenador")
	private int idEntrenador;
	
	@Column(name = "Nombre")
	private String nomEntrenador;
	
	@Column(name = "NumClientes")
	private int numClientes;
	
	

	public Entrenador() {
		super();
	}

	public Entrenador(String nomEntrenador, int numClientes) {
		super();
		this.nomEntrenador = nomEntrenador;
		this.numClientes = numClientes;
	}
		public int getIdEntrenador() {
			return idEntrenador;
		}

		public void setIdEntrenador(int idEntrenador) {
			this.idEntrenador = idEntrenador;
		}

		public String getNomEntrenador() {
			return nomEntrenador;
		}

		public void setNomEntrenador(String nomEntrenador) {
			this.nomEntrenador = nomEntrenador;
		}
		public int getNumClientes() {
			return numClientes;
		}

		public void setNumClientes(int numClientes) {
			this.numClientes = numClientes;
		}
	
	
}
