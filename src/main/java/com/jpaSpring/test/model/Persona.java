package com.jpaSpring.test.model;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

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

//	@OneToOne // join column name= nombre que queremos que tenga la fk, referencedColumnName = nombre del atributo de la clase con la que relacionamos como PK
//	// Es importante tener en cuenta que la anotación @JoinColumn SOLO SE PUEDE USAR del lado de la clase que contendrá la FK
//	@JoinColumn(name = "id_mascota", referencedColumnName = "id_mascota")

	
	/* en el @OneToMany la relacion se establece del lado del 1 en JAVA, 
	 * en la base de datos es al reves, la relacion se esablece del lado de la N, jpa se encarga de mapear todo por detras. 
	 *En @OneToMany unidireccional HIbernate crea una tabla intermedia para indicar la relacion entre una tabla y la otra.
	 *@JoinTable nos permite colocar el nombre que queramos para la tabla intermedia con el atributo name
	 En el atributo joinColumns ponemos los datos del 1 indicamos la anotacion @JoinColumn (name del campo = "FK_PERSONA", atributo nullable = false
	 En el atributo inverseJoinColumns ponemos los datos de la N indicamos la anotacion @JoinColumn (name del campo = "FK_MASCOTA", atributo nullable = false)		
	 */
	@OneToMany
	@JoinTable ( name = "duenioxmascota", joinColumns= @JoinColumn(name = "FK_PERSONA", nullable = false), inverseJoinColumns = @JoinColumn (name = "FK_MASCOTA", nullable = false))
	private List<Mascota> unaMascota;

		
	}

