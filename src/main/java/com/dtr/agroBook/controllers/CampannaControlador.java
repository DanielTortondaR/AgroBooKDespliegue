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

import com.dtr.agroBook.entities.Campanna;
import com.dtr.agroBook.exceptions.ExceptionModel;
import com.dtr.agroBook.services.CampannaServicios;

/**
 * @author Daniel Tortonda Ruiz
 *
 */
@RestController
@CrossOrigin(origins="https://agrobook-f959a.web.app")
@RequestMapping("/campanna")
public class CampannaControlador {

	@Autowired
	private CampannaServicios servicios;
	
	
	
	@GetMapping("/all") 
	public ResponseEntity<Optional<List<Campanna>>> listaCampannas() throws ExceptionModel {
		
		return ResponseEntity.ok(servicios.listaCampannasAll());
	}
	
	
	@GetMapping("/propietario/{explotacion}") 
	public ResponseEntity<Optional<List<Campanna>>> datosExplotacionByProp(@PathVariable int explotacion) throws ExceptionModel {
		
		return ResponseEntity.ok(servicios.listaCampannasPropietario(explotacion));
	}
	
	
	@GetMapping("/finca/{explotacion}") 
	public ResponseEntity<Optional<List<Campanna>>> datosExplotacionByFinca(@PathVariable int finca) throws ExceptionModel {
		
		return ResponseEntity.ok(servicios.listaCampannasFinca(finca));
	}
	
	
	@GetMapping("/data/id/{idCampanna}") 
	public ResponseEntity<Optional<Campanna>> datosExplotacionById(@PathVariable int idCampanna) throws ExceptionModel {
		
		return ResponseEntity.ok(servicios.datosCampannaById(idCampanna));
	}

	
	@PostMapping("/add/to/{prop}")
    public ResponseEntity<Optional<Campanna>> addNewCampanna(@RequestBody Campanna newCampanna,@PathVariable int prop){
        
        return ResponseEntity.ok(servicios.addNewCampanna(newCampanna, prop )); 
    }
	
	
	@PatchMapping("/modicar")
    public ResponseEntity<Optional<Campanna>> modCampanna(@RequestBody Campanna newCampanna){
        
        return ResponseEntity.ok(servicios.modCampanna(newCampanna)); 
    }
	
	
	@DeleteMapping("/drop/{idCampanna}")
	public ResponseEntity<String> dropExplotacion(@PathVariable int idCampanna){
        
        return ResponseEntity.ok(servicios.dropCampanna(idCampanna)); 
    }
}
