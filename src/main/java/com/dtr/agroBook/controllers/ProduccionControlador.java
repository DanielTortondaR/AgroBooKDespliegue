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

import com.dtr.agroBook.entities.Produccion;
import com.dtr.agroBook.exceptions.ExceptionModel;
import com.dtr.agroBook.services.ProduccionServicios;

/**
 * @author Daniel Tortonda Ruiz
 *
 */

@RestController
@CrossOrigin(origins="https://agrobook-f959a.web.app")
@RequestMapping("/produccion")
public class ProduccionControlador {

	@Autowired
	private ProduccionServicios servicios;
	
	
	
	@GetMapping("/all") 
	public ResponseEntity<Optional<List<Produccion>>> listaProducciones() throws ExceptionModel {
		
		return ResponseEntity.ok(servicios.listaProduccionesAll());
	}
	
	
	@GetMapping("/finca/{idfinca}") 
	public ResponseEntity<Optional<List<Produccion>>> produccionesPorFinca(@PathVariable int idfinca) throws ExceptionModel {
		
		return ResponseEntity.ok(servicios.produccionesPorFinca(idfinca));
	}
	
	
	@GetMapping("/propietario/{explotacion}") 
	public ResponseEntity<Optional<List<Produccion>>> datosExplotacionByProp(@PathVariable int explotacion) throws ExceptionModel {
		
		return ResponseEntity.ok(servicios.listaProduccionesPorPropietario(explotacion));
	}
	
	
	@GetMapping("/data/id/{idProduccion}") 
	public ResponseEntity<Optional<Produccion>> datosProduccionById(@PathVariable int idProduccion) throws ExceptionModel {
		
		return ResponseEntity.ok(servicios.datosProduccionById(idProduccion));
	}

	
	@PostMapping("/add")
    public ResponseEntity<Optional<Produccion>> addNewProduccion(@RequestBody Produccion newProduccion){
        
        return ResponseEntity.ok(servicios.addNewProduccion(newProduccion)); 
    }
	
	
	
	@PatchMapping("/modicar")
    public ResponseEntity<Optional<Produccion>> modProduccion(@RequestBody Produccion newProduccion){
        
        return ResponseEntity.ok(servicios.modProduccion(newProduccion)); 
    }
	
	
	@DeleteMapping("/drop/{idProduccion}")
	public ResponseEntity<String> dropProduccion(@PathVariable int idProduccion){
        
        return ResponseEntity.ok(servicios.dropProduccion(idProduccion)); 
    }
}

