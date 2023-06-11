/**
 * 
 */
package com.dtr.agroBook.entities;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * @author Daniel Tortonda Ruiz
 *
 */
public class Campannas implements Serializable {

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
	
	@Column(name= "FechaInicio", nullable = false)
    private LocalDate fechaInicio;
	
	@Column(name= "Alacance", nullable = true)
	private int alacance;
}
