/**
 * 
 */
package com.dtr.agroBook.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name = "Explotaciones")
public class Explotacion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -114670499327670142L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    
    
    @Column(name= "DNI", columnDefinition = "varchar(12)", nullable = false)
    private String dni;
    
    
    @Column(name= "Nombre", columnDefinition = "varchar(20)", nullable = false, unique = true)
    private String name;
    
    
    @Column(name= "Titular_Nombre", columnDefinition = "varchar(15)", nullable = false)
    private String nameTit;
    
    
    @Column(name= "Titular_Apellidos", columnDefinition = "varchar(40)", nullable = false)
    private String surnames;
    
    
    @Column(name= "Nacimiento", nullable = false)
    private LocalDate date;
    
    
    @Column(name= "FechaRegistro", nullable = false)
    private LocalDate dateRegister;
    
    
    @Column(name= "Localidad", length = 45, nullable = false)
    private String location;
    
    
    @Column(name= "Direccion", length = 45, nullable = false)
    private String address;
    
    
    @Column(name= "Provincia", length = 20, nullable = false)
    private String provincia;
    
 
    @Column(name= "CP", length = 5, nullable = false)
    private String cp;
    
    
    @Column(name= "Telefono", length = 12)
    private String telephone;
    
    
    @Column(name= "Email", length = 45)
    private String email;
    
    
    @Column(name= "Password", length = 255, nullable = false)
    private String password;
    
    @Column(name= "Admin", columnDefinition = "tinyint(1)", nullable = false)
    private boolean admin;
    
    @Column(name= "Blocked", columnDefinition = "tinyint(1)", nullable = false)
    private boolean blocked;
    
    @OneToMany(mappedBy = "explotacion", targetEntity = Finca.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("rel1")
    private Set<Finca> capital;
}
