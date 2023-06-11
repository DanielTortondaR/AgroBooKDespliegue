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
@Table(name = "Campanna")
public class Campanna implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5779700408065128748L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCampanna", length = 8)
    private int idCampanna;
	
	@Column(name= "Nombre", nullable = false)
    private String nombre;
	
	@Column(name= "Alacance", nullable = false)
	private int alacance;
	
	@OneToMany(targetEntity = Produccion.class, mappedBy = "campanna", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
    private Set<Produccion> producciones;
}
