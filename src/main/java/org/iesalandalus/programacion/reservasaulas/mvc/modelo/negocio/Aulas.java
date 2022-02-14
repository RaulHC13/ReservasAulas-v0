package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;

public class Aulas {
	
	private int capacidad;
	private int tamano;
	private Aula[] coleccionAulas;
	
	
	public Aulas(int capacidad) {
		
		if (capacidad <= 0 ) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		this.capacidad = capacidad;
		this.tamano = 0;
		coleccionAulas = new Aula[capacidad];
	}
	public Aula[] get() {
		
		return copiaProfundaAulas();
	}
	private Aula[] copiaProfundaAulas() {//Se crea una copia profuna creando un nuevo array y copiando los valor
										//tambien se puede utilizar foreach
		Aula[] copiaProfunda = new Aula[capacidad];
		
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (coleccionAulas[i] != null) {
				copiaProfunda[i] = new Aula(coleccionAulas[i]);
			}
		}
		return coleccionAulas;
	}
	public void insertar(Aula aula) throws OperationNotSupportedException {
		
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede insertar un aula nula.");
		}
		if (capacidadSuperada(tamano)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más aulas.");
		}
		int indice = buscarIndice(aula);
		
		if (tamanoSuperado(indice)) {
			coleccionAulas[tamano] = new Aula(aula);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún aula con ese nombre.");
		}
		tamano++;//Al insertar hay que aumentar el tamaño.
	}
	public Aula buscar(Aula aula) {
		
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		}
		int indice = buscarIndice(aula);
		
		if (tamanoSuperado(indice)) {
			return null;
		} else {
			return new Aula(coleccionAulas[indice]);
		}
	}
	public void borrar(Aula aula) throws OperationNotSupportedException {
		
		int indice;
		
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede borrar un aula nula.");
		}
		indice = buscarIndice(aula);
		
		if (tamanoSuperado(indice)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún aula con ese nombre.");
		}
		desplazarUnaPosicionHaciaIzquierda(indice);
		tamano--;//Al borrar se reduce el tamaño.
	}
	private int buscarIndice(Aula aula) {
		
		boolean buscar = false;
		int indice = tamano + 1;
		
		for (int i = 0; i < tamano&&!buscar; i++) {
			if (coleccionAulas[i].equals(aula)) {
				buscar = true;
				indice = i;
			}
		}
		return indice;
	}
	private boolean tamanoSuperado(int superaTamano) {
		boolean tamanoSuperado = false;
		
		if (superaTamano >= tamano) {
			 tamanoSuperado = true;
		} else if (superaTamano < tamano) {
			 tamanoSuperado = false;
		}
		return tamanoSuperado;
	}
	private boolean capacidadSuperada(int superaCapacidad) {
		boolean capacidadSuperada = false;;
		
		if (superaCapacidad >= capacidad) {
			capacidadSuperada = true;
		} else if (superaCapacidad < capacidad) {
			capacidadSuperada = false;
		}
		return capacidadSuperada;
	}
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		
		int i;
		
		for (i = indice; i < coleccionAulas.length - 1; i++) {
			coleccionAulas[i] = coleccionAulas[i+1];
		}
		coleccionAulas[i] = null;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public int getTamano() {
		return tamano;
	}
	public String[] representar() {
		
		String[] representar = new String[tamano];/*Se crea un array de string en el que se copian
		los valores del array coleccion en cada iteracion */
		
		for (int i = 0; !tamanoSuperado(i) ;i++) {
			
			representar[i] = coleccionAulas[i].toString();
		}
		return representar;	
	}
}