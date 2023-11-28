package com.jpaSpring.test.service;

import java.util.List;

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

}
