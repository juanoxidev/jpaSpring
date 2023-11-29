package com.jpaSpring.test.service;

import java.util.List;
import java.util.Map;

import com.jpaSpring.test.model.Persona;

public interface IPersonaService {

	// metodo para traer todas las personas
	public List<Persona> getPersonas();

	// metodo para dar de alta una persona
	public void savePersona(Persona persona);

	// metodo para borrar una persona
	public void deletePersona(Long id);

	// metodo para encontrar una persona
	public Persona findPersona(Long id);

	/* Metodo edit en JPA hibernate para relizar una modificacion
	 * debemos recibir desde nuestro controller 
	 * los nuevos datos del objeto que queremos modificar y su id original. 
	 * A partir de esto buscamos el objeto mediante find, modificamos sus atributos
	 * por los nuevos (mediando sets) y luego lo volveremos a guardar mediante save
	 */
	public void editPersona(Long idOriginal, String nuevoNombre, String nuevoApellido, int nuevaEdad);


	public void editPersona(Persona persona);

	public void actualizarListaDeMascotas(Persona personaActual,
			List<Map<String, Integer>> listaDeMascotasArrayListMap);

}
