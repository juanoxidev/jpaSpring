package com.jpaSpring.test.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpaSpring.test.model.Mascota;
import com.jpaSpring.test.model.Persona;
import com.jpaSpring.test.repository.MascotaRepository;
import com.jpaSpring.test.repository.PersonaRepository;

@Service
public class PersonaService implements IPersonaService {

	@Autowired
	private PersonaRepository personaRepository;
	@Autowired
	private MascotaRepository mascotaRepository;

	@Override
	public List<Persona> getPersonas() {
		List<Persona> listaPersonas = this.personaRepository.findAll();
		return listaPersonas;
	}

	@Override
	public void savePersona(Persona persona) {
		// save ademas de guardar modifica
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

	@Override
	public void editPersona(Long idOriginal, String nuevoNombre, String nuevoApellido, int nuevaEdad) {
		Persona personaAEditar = this.findPersona(idOriginal);
		personaAEditar.setNombre(nuevoNombre);
		personaAEditar.setApellido(nuevoApellido);
		personaAEditar.setEdad(nuevaEdad);
		this.savePersona(personaAEditar);
		
	}

	@Override
	public void editPersona(Persona persona) {
		// save ademas de guardar modifica 
		this.savePersona(persona);
		
	}

	@Override
	public void actualizarListaDeMascotas(Persona personaActual, Collection<Mascota> mascotas) {
	 String [] valores = (String[]) mascotas.toArray();
	 System.out.println(valores);
		this.savePersona(personaActual);
	}


		
}
