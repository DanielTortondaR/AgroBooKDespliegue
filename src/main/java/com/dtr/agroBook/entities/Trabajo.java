/**
 * 
 */
package com.dtr.agroBook.entities;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "Trabajos")
public class Trabajo implements Serializable {


	private static final long serialVersionUID = 4144878209973413528L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTrabajo")
    private int idTrabajo;
	
	
	@Column(name= "fecha", nullable = false)
    private LocalDate fecha;
	
	
	@Column(name = "anotaciones", nullable = true, columnDefinition = "TINYTEXT")
	private String anotaciones;
	
	
	@ManyToOne( targetEntity = Finca.class )
    @JoinColumn(name = "finca", nullable = false, referencedColumnName = "idFinca")
    @JsonBackReference("rel5")
	private Finca finca;
    
	
	@ManyToOne( targetEntity = Faena.class )
    @JoinColumn(name = "faena", nullable = false, referencedColumnName = "idFaena")
	private Faena faena;
    
	
}
