/**
 * 
 */
package com.dtr.agroBook.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dtr.agroBook.entities.Faena;
import com.dtr.agroBook.exceptions.ExceptionModel;
import com.dtr.agroBook.services.FaenaServicios;

/**
 * @author DanielTortonda Ruiz
 */

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/faena")
public class FaenaControlador {

	@Autowired
	private FaenaServicios servicios;
	
	
	@GetMapping("/all") 
	public ResponseEntity<Optional<List<Faena>>> listaFaenas() throws ExceptionModel {
		
		return ResponseEntity.ok(servicios.listaFaenasAll());
	}
	
	
	@GetMapping("/propietario/{explotacion}") 
	public ResponseEntity<Optional<List<Faena>>> datosFaenaByProp(@PathVariable int explotacion) throws ExceptionModel {
		
		return ResponseEntity.ok(servicios.listaFaenasPropietario(explotacion));
	}
	
	
	@GetMapping("/data/id/{idFaena}") 
	public ResponseEntity<Optional<Faena>> datosFaenaById(@PathVariable int idFaena) throws ExceptionModel {
		
		return ResponseEntity.ok(servicios.datosFaenaById(idFaena));
	}
	
	@GetMapping("/data/trabajo/{idTrabajo}") 
	public ResponseEntity<Optional<Faena>> datosFaenaBytrabajo(@PathVariable int idTrabajo) throws ExceptionModel {
		
		return ResponseEntity.ok(servicios.faenaPorTrabajo(idTrabajo));
	}

	
	@PostMapping("/add")
    public ResponseEntity<Optional<Faena>> addNewFaena(@RequestBody Faena newFaena){
        
        return ResponseEntity.ok(servicios.addNewFaena(newFaena)); 
    }
	
	
	@PatchMapping("/modicar")
    public ResponseEntity<Optional<Faena>> modFaena(@RequestBody Faena newFaena){
        
        return ResponseEntity.ok(servicios.modFaena(newFaena)); 
    }
	
	
	@DeleteMapping("/drop/{idFaena}")
	public ResponseEntity<String> dropFaena(@PathVariable int idFaena){
        
        return ResponseEntity.ok(servicios.dropFaena(idFaena)); 
    }
}
