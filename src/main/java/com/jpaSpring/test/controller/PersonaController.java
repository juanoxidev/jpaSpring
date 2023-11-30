package com.jpaSpring.test.controller;


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
import com.jpaSpring.test.model.Persona;
import com.jpaSpring.test.service.IPersonaService;

@RestController
@RequestMapping("/api/personas") // url base
public class PersonaController {


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
	public ResponseEntity<?> actualizarParcialmentePersona(@PathVariable Long id,
			@RequestBody Map<String, ?> camposActualizables) {
		Persona persona = this.personaService.actualizarDatosParciales(id,camposActualizables);
		if (persona != null) {
			return new ResponseEntity<Persona>(this.personaService.findPersona(id), HttpStatus.OK);	
		} else {
			return new ResponseEntity<String>("Este id no esta asociado a ninguna persona", HttpStatus.OK);	
		}
		 
	}

}
