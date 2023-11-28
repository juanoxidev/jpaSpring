package com.jpaSpring.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpaSpring.test.model.Mascota;
import com.jpaSpring.test.service.IMascotaService;

@RestController
@RequestMapping("/mascotas") 
public class MascotaController {
	
@Autowired
private IMascotaService mascotaService;

@PostMapping("/crear")
public ResponseEntity<?> createPersona(@RequestBody Mascota mascota) {
	this.mascotaService.saveMascota(mascota);
	return new ResponseEntity<String>("Mascota creada con exito", HttpStatus.OK);
}


}
