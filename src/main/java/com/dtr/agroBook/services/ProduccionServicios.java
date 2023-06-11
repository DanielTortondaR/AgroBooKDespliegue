/**
 * 
 */
package com.dtr.agroBook.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dtr.agroBook.entities.Produccion;
import com.dtr.agroBook.exceptions.ExceptionModel;
import com.dtr.agroBook.repositories.Producciones;

/**
 * @author Daniel Tortonda Ruiz
 *
 */

@Service
public class ProduccionServicios {

	@Autowired
	private Producciones producciones;
	
	
	
	public Optional<List<Produccion>> listaProduccionesAll() throws ExceptionModel {
		
		Optional<List<Produccion>> listaProduccions = Optional.of(producciones.findAll());
		
		if(listaProduccions.get().isEmpty() || listaProduccions.get() == null) {
            
            throw new ExceptionModel(HttpStatus.BAD_REQUEST, "No hay producciones registrados");
            
        }else {
            
           return listaProduccions;
            
        }    
    }
	
	
	
	public Optional<List<Produccion>> produccionesPorFinca(int finca) throws ExceptionModel {
		
		Optional<List<Produccion>> listaProduccion = producciones.produccionesPorFinca(finca);
		
		if(listaProduccion.isEmpty() || listaProduccion.get().isEmpty()) {
            
            throw new ExceptionModel(HttpStatus.BAD_REQUEST, "No tiene producciones registradas");
            
        }else {
            
           return listaProduccion;
            
        }    
    }
	
	
	
	public Optional<List<Produccion>> listaProduccionesPorPropietario(int alcance) throws ExceptionModel {
		
		Optional<List<Produccion>> listaProduccion = producciones.produccionesPorExplotacion(alcance);
		
		if(listaProduccion.isEmpty() || listaProduccion.get().isEmpty()) {
            
            throw new ExceptionModel(HttpStatus.BAD_REQUEST, "No tiene producciones registradas");
            
        }else {
            
           return listaProduccion;
            
        }    
    }

   
	public Optional<Produccion> datosProduccionById(int id) throws ExceptionModel{
        
        Optional<Produccion> searchedProduccion = producciones.findById(id) ;
        
        if(searchedProduccion.isEmpty()) {
            
            throw new ExceptionModel(HttpStatus.BAD_REQUEST, "Produccion no encontrada");
            
        }else {
            
            return searchedProduccion;
            
        }
    }

	
	
	public Optional<Produccion> addNewProduccion(Produccion newProduccion){
		

			return Optional.of(producciones.save(newProduccion));
        
	}
	
	
	public Optional<Produccion> modProduccion(Produccion modProduccion){
        
        Optional<Produccion> comprobacion = producciones.findById(modProduccion.getIdProduccion());
        
        if(comprobacion.isEmpty()) {
        	
        	 throw new ExceptionModel(HttpStatus.BAD_REQUEST, "La plantación no se ha encontrado"); 
        
        }else {
        	return Optional.of( producciones.save(modProduccion));
        }
         
	}
	
	
	public String dropProduccion(int id) throws ExceptionModel{
        
		datosProduccionById(id);
		
        producciones.deleteById(id);
        
        return "Produccion eliminada.";
    }
}
