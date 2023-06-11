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

import com.dtr.agroBook.entities.Cultivo;
import com.dtr.agroBook.exceptions.ExceptionModel;
import com.dtr.agroBook.services.CultivoServicios;

/**
 * 
 * @author Daniel Tortonda Ruiz
 *
 */

@RestController
@CrossOrigin(origins = "https://agrobook-f959a.web.app")
@RequestMapping("/cultivo")
public class CultivoControlador {
	
	
	@Autowired
	private CultivoServicios servicios;
	
	
	@GetMapping("/all") 
	public ResponseEntity<Optional<List<Cultivo>>> listaCultivos() throws ExceptionModel {
		
		return ResponseEntity.ok(servicios.listaCultivosAll());
	}
	
	
	@GetMapping("/propietario/{explotacion}") 
	public ResponseEntity<Optional<List<Cultivo>>> datosExplotacionByProp(@PathVariable int explotacion) throws ExceptionModel {
		
		return ResponseEntity.ok(servicios.listaCultivosPropietario(explotacion));
	}
	
	
	@GetMapping("/data/id/{idCultivo}") 
	public ResponseEntity<Optional<Cultivo>> datosExplotacionById(@PathVariable int idCultivo) throws ExceptionModel {
		
		return ResponseEntity.ok(servicios.datosCultivoById(idCultivo));
	}

	
	@PostMapping("/add/to/{prop}")
    public ResponseEntity<Optional<Cultivo>> addNewCultivo(@RequestBody Cultivo newCultivo,@PathVariable int prop){
        
        return ResponseEntity.ok(servicios.addNewCultivo(newCultivo, prop )); 
    }
	
	
	@PatchMapping("/modicar")
    public ResponseEntity<Optional<Cultivo>> modCultivo(@RequestBody Cultivo newCultivo){
        
        return ResponseEntity.ok(servicios.modCultivo(newCultivo)); 
    }
	
	
	@DeleteMapping("/drop/{idCultivo}")
	public ResponseEntity<String> dropExplotacion(@PathVariable int idCultivo){
        
        return ResponseEntity.ok(servicios.dropCultivo(idCultivo)); 
    }
}
