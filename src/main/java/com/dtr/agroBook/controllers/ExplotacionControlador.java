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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dtr.agroBook.entities.Explotacion;
import com.dtr.agroBook.exceptions.ExceptionModel;
import com.dtr.agroBook.services.ExplotacionServicios;

/**
 * @author Daniel Torotnda Ruiz
 */

@RestController
@CrossOrigin(origins="http://localhost:4200", allowCredentials = "true")
@RequestMapping("/explotacion")
public class ExplotacionControlador {

	
	@Autowired
	private ExplotacionServicios servicios;
	
	
	
	
	@GetMapping("/all")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Optional<List<Explotacion>>> listaExplotaciones() throws ExceptionModel {
		
		return ResponseEntity.ok(servicios.listaExplotaciones());
	}
	
	
	@GetMapping("/data/id/{idExplotacion}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Optional<Explotacion>> datosExplotacionById(@PathVariable int idExplotacion) throws ExceptionModel {
		
		return ResponseEntity.ok(servicios.datosExplotacionById(idExplotacion));
	}
	
	
	@GetMapping("/data/nombre/{nombreExplotacion}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Optional<Explotacion>> datosExplotacionByName(@PathVariable String nombreExplotacion) throws ExceptionModel {
		
		return ResponseEntity.ok(servicios.datosExplotacionByName(nombreExplotacion));
	}
	
	
	@GetMapping("/porNombre/{nombre}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Optional<List<Explotacion>>> listaExplotacionesByName(@PathVariable String nombre) throws ExceptionModel {
		
		return ResponseEntity.ok(servicios.listaExplotacionesByName(nombre));
	}
	
		
	@PostMapping("/log")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Optional<Explotacion>> loggin(@RequestParam String nombre, @RequestParam String key) throws ExceptionModel {
		
		return ResponseEntity.ok(servicios.loggin(nombre, key));
	}
	
	
	@PostMapping("/add")
	@CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Optional<Explotacion>> addNewExplotacion(@RequestBody Explotacion newExplotacion){
        
        return ResponseEntity.ok(servicios.addNewExplotacion(newExplotacion)); 
    }
	
	
	@PatchMapping("/modificar")
	@CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Optional<Explotacion>> modExplotacion(@RequestBody Explotacion newExplotacion){
        
        return ResponseEntity.ok(servicios.modExplotacion(newExplotacion)); 
    }
	
	
	@DeleteMapping("/drop/{idExplotacion}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<String> dropExplotacion(@PathVariable int idExplotacion){
        
        return ResponseEntity.ok(servicios.dropExplotacion(idExplotacion)); 
    }
}
