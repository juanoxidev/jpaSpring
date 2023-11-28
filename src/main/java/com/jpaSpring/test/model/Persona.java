package com.jpaSpring.test.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Persona {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String nombre;
	private String apellido;
	private int edad;
	@OneToOne // join column name= nombre que queremos que tenga la fk, referencedColumnName = nombre del atributo de la clase con la que relacionamos como PK
	// Es importante tener en cuenta que la anotación @JoinColumn SOLO SE PUEDE USAR del lado de la clase que contendrá la FK
	@JoinColumn(name = "id_mascota", referencedColumnName = "id_mascota")
	private Mascota unaMascota;

}
