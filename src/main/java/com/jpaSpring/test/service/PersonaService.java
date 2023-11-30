package com.jpaSpring.test.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jpaSpring.test.model.Mascota;
import com.jpaSpring.test.model.Persona;
import com.jpaSpring.test.repository.PersonaRepository;

@Service
public class PersonaService implements IPersonaService {

	@Autowired
	private PersonaRepository personaRepository;
	@Autowired
	private IMascotaService mascotaService;

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
	public Persona actualizarDatosParciales(Long id, Map<String, ?> camposActualizables) {
		// Obtener la persona actual
		Persona personaActual = this.findPersona(id);

		if (personaActual == null) {
			return personaActual;
		}

		// Actualizar solo los campos permitidos
		if (camposActualizables.containsKey("nombre")) {
			personaActual.setNombre((String) camposActualizables.get("nombre"));
		}
		if (camposActualizables.containsKey("apellido")) {
			personaActual.setApellido((String) camposActualizables.get("apellido"));
		}
		if (camposActualizables.containsKey("edad")) {
			personaActual.setEdad((int) camposActualizables.get("edad"));
		}
		if (camposActualizables.containsKey("unaMascota")) {
			List<Map<String, Integer>> listaDeMascotasArrayListMap = ((List<Map<String, Integer>>) camposActualizables.get("unaMascota"));
			List<Long> listaDeIds = new ArrayList<>();
			// Extraer los valores de "id_mascota" y agregarlos a listaDeIds
			for (Map<String, Integer> mascota : listaDeMascotasArrayListMap) {
				// Asumiendo que cada objeto tiene una única entrada con clave "id_mascota"
				Long id1 = mascota.get("id_mascota").longValue();
				if (id1 != null) {
					listaDeIds.add(id1);
				}
			}
			List<Mascota> mascotas = new ArrayList<>();
			for (Long idm : listaDeIds) {
				mascotas.add(this.mascotaService.findMascota(idm));
			}

			personaActual.setUnaMascota(mascotas);
		}

		// Guardar la persona actualizada
		this.savePersona(personaActual);
		return personaActual;
		
	}



}
