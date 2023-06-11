/**
 * 
 */
package com.dtr.agroBook.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "Fincas")
public class Finca implements Serializable{
	 
	
	private static final long serialVersionUID = 8566272963815377000L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idFinca", length = 8)
    private int idFinca;
    
    
    @Column(name= "Nombre", length = 20, nullable = false)
    private String name;
    
    
    @Column(name= "FechaRegistro", nullable = false)
    private LocalDate dateRegister;
    
    
    @ManyToOne(targetEntity = Explotacion.class)
    @JoinColumn(name = "Explotacion", nullable = false, referencedColumnName = "id")
    @JsonBackReference("rel1")
    private Explotacion explotacion;
    
    
    @OneToMany(mappedBy = "finca", targetEntity = Plantacion.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("rel2")
    private Set<Plantacion> vidaFinca;
    
    
    @OneToMany(mappedBy = "finca", targetEntity = Trabajo.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("rel5")
    private Set<Trabajo> vidaLaboralFinca;
    
    @OneToMany(targetEntity = Produccion.class, mappedBy = "finca", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("rel8")
    private Set<Produccion> producciones;
}
