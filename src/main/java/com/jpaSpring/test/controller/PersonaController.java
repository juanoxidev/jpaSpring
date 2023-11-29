package com.jpaSpring.test.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpaSpring.test.model.Mascota;
import com.jpaSpring.test.model.Persona;
import com.jpaSpring.test.service.IMascotaService;
import com.jpaSpring.test.service.IPersonaService;

@RestController
@RequestMapping("/api/personas") // url base
public class PersonaController {

	@Autowired
	private IMascotaService mascotaService;
	@Autowired
	private IPersonaService personaService;

	@GetMapping("/traer")
	public List<Persona> getPersonas() {
		return this.personaService.getPersonas();
	}

	@PostMapping("/crear")
	public ResponseEntity<?> createPersona(@RequestBody Persona persona) {
		this.personaService.savePersona(persona);
		return new ResponseEntity<String>("Persona creada con exito", HttpStatus.OK);
	}

	@DeleteMapping("/borrar/{id}")
	public ResponseEntity<?> deletePersona(@PathVariable Long id) {
		this.personaService.deletePersona(id);
		return new ResponseEntity<String>("Persona eliminada con exito", HttpStatus.OK);
	}

	/*
	 * Tiene la posibilidad de incluir algunas indicaciones para los parametros que
	 * se reciben en las solicitudes. Required nos permite identificar si un
	 * parametro que recibimos es obligatorio (true) o no (false). Como no siempre
	 * vamos a editar todos los parametros, ponemos todos como no requeridos.
	 * Mediante name identificamos a cada uno de los parametros para poder
	 * especificar su obligatoriedad o no.
	 */

	@PutMapping("/editar/{id}")
	public ResponseEntity<?> editPersona(@PathVariable Long id,
			@RequestParam(required = false, name = "nombre") String nuevoNombre,
			@RequestParam(required = false, name = "apellido") String nuevoApellido,
			@RequestParam(required = false, name = "edad") int nuevaEdad) {
		this.personaService.editPersona(id, nuevoNombre, nuevoApellido, nuevaEdad);
		Persona persona = this.personaService.findPersona(id);
		return new ResponseEntity<Persona>(persona, HttpStatus.OK);
	}

	/*
	 * PUT se utiliza cuando se proporciona una representación completa y se espera
	 * que el servidor reemplace completamente el recurso. PATCH, por otro lado, se
	 * utiliza cuando solo se proporcionan los campos que deben actualizarse,
	 * permitiendo actualizaciones parciales sin tener que enviar la representación
	 * completa del recurso.
	 * 
	 */
	// editarCompleto
	@PutMapping("/editar")
	public ResponseEntity<?> editPersona(@RequestBody Persona persona) {
		this.personaService.editPersona(persona);
		return new ResponseEntity<Persona>(this.personaService.findPersona(persona.getId()), HttpStatus.OK);
	}

	// editarParcial
	@PatchMapping("editarParcial/{id}")
	public ResponseEntity<Persona> actualizarParcialmentePersona(@PathVariable Long id,
			@RequestBody Map<String, Object> camposActualizables) {

		System.out.println(camposActualizables.values());
		// Obtener la persona actual
		Persona personaActual = personaService.findPersona(id);

		if (personaActual == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
			List<Map<String, Integer>> listaDeMascotasArrayListMap = ((List<Map<String, Integer>>) camposActualizables
					.get("unaMascota"));
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
		personaService.savePersona(personaActual);

		return new ResponseEntity<>(personaActual, HttpStatus.OK);
	}

}
