package com.dtr.agroBook.entities;
/**
 * @author Daniel Tortonda Ruiz
 *
 */
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
@Table(name = "Plantaciones")
public class Plantacion implements Serializable {
	
	private static final long serialVersionUID = 8044305329231460204L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idPlantacion")
    private int idPlantacion;
    
	
    @ManyToOne(targetEntity = Finca.class)
    @JoinColumn(name = "Finca", nullable = true, referencedColumnName = "idFinca")
    @JsonBackReference("rel2")
    private Finca finca;
    
    
    @Column(name= "FechaPlantacion", nullable = false, columnDefinition = "DATE")
    private LocalDate fechaPlantacion;
    
    
    @Column(name= "FechaArranque", nullable = true, columnDefinition = "DATE")
    private LocalDate fechaArranque;
    
    
    @ManyToOne(targetEntity = Cultivo.class)
    @JoinColumn(name = "Cultivo", nullable = false, referencedColumnName = "idCultivos")
    private Cultivo cultivo;
    
}
