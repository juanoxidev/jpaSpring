package com.jpaSpring.test.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Entity
public class Mascota {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id_mascota;
	private String nombre;
	private String especie;
	private String raza;
	private String color;


}
