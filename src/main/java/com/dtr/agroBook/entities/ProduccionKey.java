package com.dtr.agroBook.entities;

import java.io.Serializable;

import jakarta.persistence.Column;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProduccionKey implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -1158280523247177656L;

	@Column(name = "idFinca")
    private int idFinca;
    
    @Column(name = "idCampanna")
    private int idCampanna;
}
