package com.jpaSpring.test.service;

import java.util.List;

import com.jpaSpring.test.model.Mascota;
import com.jpaSpring.test.model.Persona;

public interface IMascotaService {
	// metodo para traer todas las mascotas
	public List<Mascota> mascota();

	// metodo para dar de alta una persona
	public void saveMascota(Mascota mascota);

	// metodo para borrar una mascota
	public void deleteMascota(Long id);

	// metodo para encontrar una mascota
	public Mascota findMascota(Long id);
	
	/* Metodo edit en JPA hibernate para relizar una modificacion
	 * debemos recibir desde nuestro controller 
	 * los nuevos datos del objeto que queremos modificar y su id original. 
	 * A partir de esto buscamos el objeto mediante find, modificamos sus atributos
	 * por los nuevos (mediando sets) y luego lo volveremos a guardar mediante save
	 */
	public void editMascota(Long idOriginal, String nuevoNombre, String nuevaEspecie, String nuevaRaza, String nuevoColor);

}
