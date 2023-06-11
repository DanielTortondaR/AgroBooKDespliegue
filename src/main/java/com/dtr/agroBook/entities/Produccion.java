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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "producciones")
public class Produccion implements Serializable{

	private static final long serialVersionUID = 9103390847920481979L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idProduccion")
    private int idProduccion;
	
	@Column(name= "fecha", nullable = false)
    private LocalDate fechaProduccion;
	
	@ManyToOne(targetEntity = Finca.class)
    @JoinColumn(name = "finca", nullable = false, referencedColumnName = "idFinca")
    @JsonBackReference("rel8")
    private Finca finca;
    
    @ManyToOne(targetEntity = Campanna.class)
    @JoinColumn(name = "campanna", nullable = false, referencedColumnName="idCampanna")
    private Campanna campanna;
    
    @Column(name= "Cantidad", nullable = true, columnDefinition = "DECIMAL(8,2) default 0")
    private float cantidad;
    
    
    
}
