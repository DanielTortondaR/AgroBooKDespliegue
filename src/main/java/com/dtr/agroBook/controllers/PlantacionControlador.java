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

import com.dtr.agroBook.entities.Plantacion;
import com.dtr.agroBook.exceptions.ExceptionModel;
import com.dtr.agroBook.services.PlantacionServicios;

/**
 * @author Daniel Tortonda Ruiz
 *
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/plantacion")
public class PlantacionControlador {
	
	
	@Autowired
	private PlantacionServicios servicios;
	
	
	@GetMapping("/all") 
	public ResponseEntity<Optional<List<Plantacion>>> listaPlantaciones() throws ExceptionModel {
		
		return ResponseEntity.ok(servicios.listaPlantacionesAll());
	}
	
	
	@GetMapping("/finca/{idfinca}") 
	public ResponseEntity<Optional<List<Plantacion>>> plantacionesPorFinca(@PathVariable int idfinca) throws ExceptionModel {
		
		return ResponseEntity.ok(servicios.plantacionesPorFinca(idfinca));
	}
	
	
	@GetMapping("/propietario/{explotacion}") 
	public ResponseEntity<Optional<List<Plantacion>>> datosExplotacionByProp(@PathVariable int explotacion) throws ExceptionModel {
		
		return ResponseEntity.ok(servicios.listaPlantacionesPorPropietario(explotacion));
	}
	
	
	@GetMapping("/data/id/{idPlantacion}") 
	public ResponseEntity<Optional<Plantacion>> datosPlantacionById(@PathVariable int idPlantacion) throws ExceptionModel {
		
		return ResponseEntity.ok(servicios.datosPlantacionById(idPlantacion));
	}

	
	@PostMapping("/add")
    public ResponseEntity<Optional<Plantacion>> addNewPlantacion(@RequestBody Plantacion newPlantacion){
        
        return ResponseEntity.ok(servicios.addNewPlantacion(newPlantacion)); 
    }
	
	
	@PostMapping("/plantar")
    public ResponseEntity<Optional<Plantacion>> plantar(@RequestBody Plantacion newPlantacion){
        
        return ResponseEntity.ok(servicios.plantar(newPlantacion)); 
    }
	
	
	@PostMapping("/arrancar")
    public ResponseEntity<Optional<Plantacion>> arrancar(@RequestBody Plantacion newPlantacion){
        
        return ResponseEntity.ok(servicios.arrancar(newPlantacion)); 
    }
	
	
	@PatchMapping("/modicar")
    public ResponseEntity<Optional<Plantacion>> modPlantacion(@RequestBody Plantacion newPlantacion){
        
        return ResponseEntity.ok(servicios.modPlantacion(newPlantacion)); 
    }
	
	
	@DeleteMapping("/drop/{idPlantacion}")
	public ResponseEntity<String> dropPlantacion(@PathVariable int idPlantacion){
        
        return ResponseEntity.ok(servicios.dropPlantacion(idPlantacion)); 
    }
}
