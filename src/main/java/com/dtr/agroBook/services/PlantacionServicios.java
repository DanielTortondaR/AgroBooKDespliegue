package com.dtr.agroBook.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dtr.agroBook.entities.Plantacion;
import com.dtr.agroBook.exceptions.ExceptionModel;
import com.dtr.agroBook.repositories.Plantaciones;

/**
 * 
 * @author Daniel Tortonda Ruiz
 *
 */

@Service
public class PlantacionServicios {

	@Autowired
	private Plantaciones plantaciones;
	
	
	
	public Optional<List<Plantacion>> listaPlantacionesAll() throws ExceptionModel {
		
		Optional<List<Plantacion>> listaPlantacions = Optional.of(plantaciones.findAll());
		
		if(listaPlantacions.get().isEmpty() || listaPlantacions.get() == null) {
            
            throw new ExceptionModel(HttpStatus.BAD_REQUEST, "No hay plantaciones registrados");
            
        }else {
            
           return listaPlantacions;
            
        }    
    }
	
	
	
	public Optional<List<Plantacion>> plantacionesPorFinca(int finca) throws ExceptionModel {
		
		Optional<List<Plantacion>> listaPlantacion = plantaciones.plantacionesPorFinca(finca);
		
		if(listaPlantacion.isEmpty() || listaPlantacion.get().isEmpty()) {
            
            throw new ExceptionModel(HttpStatus.BAD_REQUEST, "No tiene plantaciones registradas");
            
        }else {
            
           return listaPlantacion;
            
        }    
    }
	
	
	
	public Optional<List<Plantacion>> listaPlantacionesPorPropietario(int alcance) throws ExceptionModel {
		
		Optional<List<Plantacion>> listaPlantacion = plantaciones.plantacionesPorExplotacion(alcance);
		
		if(listaPlantacion.isEmpty() || listaPlantacion.get().isEmpty()) {
            
            throw new ExceptionModel(HttpStatus.BAD_REQUEST, "No tiene plantaciones registradas");
            
        }else {
            
           return listaPlantacion;
            
        }    
    }

   
	public Optional<Plantacion> datosPlantacionById(int id) throws ExceptionModel{
        
        Optional<Plantacion> searchedPlantacion = plantaciones.findById(id) ;
        
        if(searchedPlantacion.isEmpty()) {
            
            throw new ExceptionModel(HttpStatus.BAD_REQUEST, "Plantacion no encontrada");
            
        }else {
            
            return searchedPlantacion;
            
        }
    }

	
	
	public Optional<Plantacion> addNewPlantacion(Plantacion newPlantacion){
        
		Optional<Plantacion> activa = plantaciones.plantacionActiva(newPlantacion.getFinca().getIdFinca());
		
		if(activa.isPresent()) {
			Plantacion old = activa.get();;
			old.setFechaArranque(newPlantacion.getFechaPlantacion());
			plantaciones.save(old);
		}
        
		
		      	
        return Optional.of(plantaciones.save(newPlantacion));
	}
	
	
	
	public Optional<Plantacion> plantar(Plantacion newPlantacion){
        
        return Optional.of(plantaciones.save(newPlantacion));
	}
	
	
	
	public Optional<Plantacion> arrancar(Plantacion newPlantacion){
        
		Optional<Plantacion> activa = plantaciones.plantacionActiva(newPlantacion.getFinca().getIdFinca());
		
		if(activa.isEmpty()) {
			throw new ExceptionModel(HttpStatus.BAD_REQUEST, "No se puede arrancar una finca que ya está de barbecho");
		}
            	
        return Optional.of(plantaciones.save(newPlantacion));
	}
	
	
	
	public Optional<Plantacion> modPlantacion(Plantacion modPlantacion){
        
        Optional<Plantacion> comprobacion = plantaciones.findById(modPlantacion.getIdPlantacion());
        
        if(comprobacion.isEmpty()) {
        	
        	 throw new ExceptionModel(HttpStatus.BAD_REQUEST, "La plantación no se ha encontrado"); 
        
        }else {
        	return Optional.of( plantaciones.save(modPlantacion));
        }
         
	}
	
	
	public String dropPlantacion(int id) throws ExceptionModel{
        
		datosPlantacionById(id);
		
        plantaciones.deleteById(id);
        
        return "Plantacion eliminada.";
    }
}

