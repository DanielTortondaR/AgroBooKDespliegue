package com.dtr.agroBook.entities;
/**
 * @author Daniel Tortonda Ruiz
 *
 */
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


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Cultivos")
public class Cultivo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8466018291078089226L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idCultivos")
    private int idCultivos;
    
    
    @Column(name= "Cultivo", length = 45, nullable = false)
    private String nombreCultivo;
    
    
    @Column(name= "Variedad", length = 45, nullable = false)
    private String variedad;
    
    
    @Column(name= "Anual", nullable = false, columnDefinition = "TINYINT default 0" )
    private boolean anual;
    
    
    @Column(name= "Alcance", nullable = false)
    private int alcance;
    
    
    @Column(name= "Detalles", nullable = true, columnDefinition = "TINYTEXT")
    private String detalles;
    

    @OneToMany(mappedBy = "idPlantacion", targetEntity = Plantacion.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Plantacion> historicoCultivo;
}
