package com.jpaSpring.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpaSpring.test.model.Persona;
import com.jpaSpring.test.repository.PersonaRepository;

@Service
public class PersonaService implements IPersonaService {

	@Autowired
	private PersonaRepository personaRepository;

	@Override
	public List<Persona> getPersonas() {
		List<Persona> listaPersonas = this.personaRepository.findAll();
		return listaPersonas;
	}

	@Override
	public void savePersona(Persona persona) {
		this.personaRepository.save(persona);

	}

	@Override
	public void deletePersona(Long id) {
		this.personaRepository.deleteById(id);

	}

	@Override
	public Persona findPersona(Long id) {
		// si no encuentro a la persona, devuelvo null en orElse.
		return this.personaRepository.findById(id).orElse(null);
	}
}
