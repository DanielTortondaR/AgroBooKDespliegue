package com.dtr.agroBook.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dtr.agroBook.entities.Trabajo;
import com.dtr.agroBook.exceptions.ExceptionModel;
import com.dtr.agroBook.repositories.Trabajos;

/**
 * 
 * @author Daniel Tortonda Ruiz
 *
 */

@Service
public class TrabajoServicio {
	
	@Autowired
	private Trabajos trabajos;
	
	
	public Optional<List<Trabajo>> listaTrabajosAll() throws ExceptionModel {
		
		Optional<List<Trabajo>> listaTrabajos = Optional.of(trabajos.findAll());
		
		if(listaTrabajos.get().isEmpty() || listaTrabajos.get() == null) {
            
            throw new ExceptionModel(HttpStatus.BAD_REQUEST, "No hay trabajos registrados");
            
        }else {
            
           return listaTrabajos;
            
        }    
    }
	
	
	public Optional<List<Trabajo>> trabajosPorFinca(int finca) throws ExceptionModel {
		
		Optional<List<Trabajo>> listaTrabajo = trabajos.trabajosPorFinca(finca);
		
		if(listaTrabajo.isEmpty() || listaTrabajo.get().isEmpty()) {
            
            throw new ExceptionModel(HttpStatus.BAD_REQUEST, "Esta finca no tiene trabajos registradas");
            
        }else {
            
           return listaTrabajo;
            
        }    
    }
	
	
	
	public Optional<List<Trabajo>> listaTrabajosPorPropietario(int alcance) throws ExceptionModel {
		
		Optional<List<Trabajo>> listaTrabajo = trabajos.trabajosPorExplotacion(alcance);
		
		if(listaTrabajo.isEmpty() || listaTrabajo.get().isEmpty()) {
            
            throw new ExceptionModel(HttpStatus.BAD_REQUEST, "No tiene trabajos registradas");
            
        }else {
            
           return listaTrabajo;
            
        }    
    }

   
	
	public Optional<Trabajo> datosTrabajoById(int id) throws ExceptionModel{
        
        Optional<Trabajo> searchedTrabajo = trabajos.findById(id) ;
        
        if(searchedTrabajo.isEmpty()) {
            
            throw new ExceptionModel(HttpStatus.BAD_REQUEST, "Trabajo no encontrado");
            
        }else {
            
            return searchedTrabajo;
            
        }
    }

	
	
	public Optional<Trabajo> addNewTrabajo(Trabajo newTrabajo){
		      	
        return Optional.of(trabajos.save(newTrabajo));
	}

	
	
	public Optional<Trabajo> modTrabajo(Trabajo modTrabajo){
        
        Optional<Trabajo> comprobacion = trabajos.findById(modTrabajo.getIdTrabajo());
        
        if(comprobacion.isEmpty()) {
        	
        	 throw new ExceptionModel(HttpStatus.BAD_REQUEST, "El t+rabajo no se ha encontrado"); 
        
        }else {
        	return Optional.of( trabajos.save(modTrabajo));
        }
         
	}
	
	
	public String dropTrabajo(int id) throws ExceptionModel{
        
		datosTrabajoById(id);
		
        trabajos.deleteById(id);
        
        return "Trabajo eliminado.";
    }
}


