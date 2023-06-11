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

import com.dtr.agroBook.entities.Trabajo;
import com.dtr.agroBook.exceptions.ExceptionModel;
import com.dtr.agroBook.services.TrabajoServicio;

/**
 * @author Daniel Tortonda Ruiz
 *
 */
@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/trabajo")
public class TrabajoControlador {

	@Autowired
	private TrabajoServicio servicios;
	
	
	@GetMapping("/all") 
	public ResponseEntity<Optional<List<Trabajo>>> listaTrabajos() throws ExceptionModel {
		
		return ResponseEntity.ok(servicios.listaTrabajosAll());
	}
	
	
	@GetMapping("/finca/{idfinca}") 
	public ResponseEntity<Optional<List<Trabajo>>> trabajoPorFinca(@PathVariable int idfinca) throws ExceptionModel {
		
		return ResponseEntity.ok(servicios.trabajosPorFinca(idfinca));
	}
	
	
	@GetMapping("/propietario/{explotacion}") 
	public ResponseEntity<Optional<List<Trabajo>>> datosExplotacionByProp(@PathVariable int explotacion) throws ExceptionModel {
		
		return ResponseEntity.ok(servicios.listaTrabajosPorPropietario(explotacion));
	}
	
	
	@GetMapping("/data/id/{idTrabajo}") 
	public ResponseEntity<Optional<Trabajo>> datosTrabajoById(@PathVariable int idTrabajo) throws ExceptionModel {
		
		return ResponseEntity.ok(servicios.datosTrabajoById(idTrabajo));
	}

	
	@PostMapping("/add")
    public ResponseEntity<Optional<Trabajo>> addNewTrabajo(@RequestBody Trabajo newTrabajo){
        
        return ResponseEntity.ok(servicios.addNewTrabajo(newTrabajo)); 
    }
	
	
	@PatchMapping("/modicar")
    public ResponseEntity<Optional<Trabajo>> modTrabajo(@RequestBody Trabajo newTrabajo){
        
        return ResponseEntity.ok(servicios.modTrabajo(newTrabajo)); 
    }
	
	
	@DeleteMapping("/drop/{idTrabajo}")
	public ResponseEntity<String> dropTrabajo(@PathVariable int idTrabajo){
        
        return ResponseEntity.ok(servicios.dropTrabajo(idTrabajo)); 
    }
}
