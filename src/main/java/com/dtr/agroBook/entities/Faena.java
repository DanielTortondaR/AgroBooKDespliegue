/**
 * 
 */
package com.dtr.agroBook.entities;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Daniel Tortonda Ruiz
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Faenas")
public class Faena implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4746428129387066588L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFaena")
    private int idFaena;
	
	@Column(name= "Nombre", nullable = false)
    private String nombre;
	
	@Column(name= "Descripcion", nullable = true, columnDefinition = "TINYTEXT")
    private String descripcion;
	
	@Column(name= "Alacance", nullable = true)
	private int alacance;
	
	@OneToMany(mappedBy = "faena", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
    private Set<Trabajo> listaTrabajos;
}
