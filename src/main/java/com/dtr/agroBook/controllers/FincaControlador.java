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

import com.dtr.agroBook.entities.Finca;
import com.dtr.agroBook.exceptions.ExceptionModel;
import com.dtr.agroBook.services.FincaServicios;

/**
 * @author Daniel 
 *
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/finca")
public class FincaControlador {

	
	@Autowired
	private FincaServicios servicios;
	
	
	
	@GetMapping("/all") 
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Optional<List<Finca>>> listaExplotaciones() throws ExceptionModel {
		
		return ResponseEntity.ok(servicios.listaFincasAll());
	}
	
	
	@GetMapping("/propietario/{explotacion}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Optional<List<Finca>>> datosExplotacionByProp(@PathVariable int explotacion) throws ExceptionModel {
		
		return ResponseEntity.ok(servicios.listaFincasPropietario(explotacion));
	}
	
	
	@GetMapping("/data/id/{idFinca}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Optional<Finca>> datosExplotacionById(@PathVariable int idFinca) throws ExceptionModel {
		
		return ResponseEntity.ok(servicios.datosFincaById(idFinca));
	}
	
	
	@GetMapping("/porNombre/{nombreFinca}/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Optional<List<Finca>>> listaExplotacionesByName(@PathVariable String nombreFinca, @PathVariable int id) throws ExceptionModel {
		
		return ResponseEntity.ok(servicios.datosFincaByName(nombreFinca, id));
	}
	
	@GetMapping("/porNombreStrict/{nombreFinca}/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Optional<Finca>> listaExplotacionesByNameStrict(@PathVariable String nombreFinca, @PathVariable int id) throws ExceptionModel {
		
		return ResponseEntity.ok(servicios.datosFincaByNameStrict(nombreFinca, id));
	}

	
	@PostMapping("/add/{prop}")
	@CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Optional<Finca>> addNewFinca(@RequestBody Finca newFinca,@PathVariable int prop){
        
        return ResponseEntity.ok(servicios.addNewFinca(newFinca, prop )); 
    }
	
	
	@PatchMapping("/modicar")
	@CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Optional<Finca>> modFinca(@RequestBody Finca newFinca){
        
        return ResponseEntity.ok(servicios.modFinca(newFinca)); 
    }
	
	
	@DeleteMapping("/drop/{idFinca}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<String> dropExplotacion(@PathVariable int idFinca){
        
        return ResponseEntity.ok(servicios.dropFinca(idFinca)); 
    }
}
