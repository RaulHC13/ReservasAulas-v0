package org.iesalandalus.programacion.reservasaulas.mvc.modelo;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;


public class Modelo {
	
	private final static int CAPACIDAD = 0;
	
	private Aula[] aulas;
	private Profesor[] profesores;
	private Reserva[] reservas;
	
	public Modelo(){
		
	}

	public Aula[] getAulas() {
		return aulas;
	}

	public Profesor[] getProfesores() {
		return profesores;
	}

	public Reserva[] getReservas() {
		return reservas;
	}

	}