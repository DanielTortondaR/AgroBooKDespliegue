/**
 * 
 */
package com.dtr.agroBook.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dtr.agroBook.entities.Faena;
import com.dtr.agroBook.exceptions.ExceptionModel;
import com.dtr.agroBook.repositories.Faenas;

/**
 * @author Daniel Tortonda Ruiz
 *
 */
@Service
public class FaenaServicios {

	@Autowired
	private Faenas faenas;
	
	
	public Optional<List<Faena>> listaFaenasAll() throws ExceptionModel {
		
		Optional<List<Faena>> listaFaenas = Optional.of(faenas.findAll());
		
		if(listaFaenas.get().isEmpty() || listaFaenas.get() == null) {
            
            throw new ExceptionModel(HttpStatus.BAD_REQUEST, "No hay faenas registradas");
            
        }else {
            
           return listaFaenas;
            
        }    
    }
	
	
	public Optional<List<Faena>> listaFaenasPropietario(int alcance) throws ExceptionModel {
		
		Optional<List<Faena>> listaFaena = faenas.faenasPorExplotacion(alcance);
		
		if(listaFaena.isEmpty() || listaFaena.get().isEmpty()) {
            
            throw new ExceptionModel(HttpStatus.BAD_REQUEST, "No tiene faenas registradas");
            
        }else {
            
           return listaFaena;
            
        }    
    }

   
	public Optional<Faena> datosFaenaById(int id) throws ExceptionModel{
        
        Optional<Faena> searchedFaena = faenas.findById(id) ;
        
        if(searchedFaena.isEmpty()) {
            
            throw new ExceptionModel(HttpStatus.BAD_REQUEST, "Faena no encontrada");
            
        }else {
            
            return searchedFaena;
            
        }
    }

	public Optional<Faena> faenaPorTrabajo(int idTrabajo) throws ExceptionModel{
        
        return faenas.faenasPorTrabajo(idTrabajo);
    }
	
	public Optional<Faena> addNewFaena(Faena newFaena){
        
		Optional<List<Faena>> comprobacion = listaFaenasPropietario(newFaena.getAlacance());
        
		if(!comprobacion.isEmpty()) {
			for (Faena faena : comprobacion.get()) {
				if(newFaena.getNombre().equals(faena.getNombre())) {
					throw new ExceptionModel(HttpStatus.BAD_REQUEST, "Ese nombre de faena ya está en uso");
				}
			}
		}       	
        return Optional.of(faenas.save(newFaena));
	}
	
	
	
	public Optional<Faena> modFaena(Faena modFaena){
        
        Optional<Faena> comprobacion = faenas.findById(modFaena.getIdFaena());
        
        if(comprobacion.isEmpty()) {
        	
        	 throw new ExceptionModel(HttpStatus.BAD_REQUEST, "La faena no se ha encontrado"); 
        
        }else {
        	return Optional.of( faenas.save(modFaena));
        }
         
	}
	
	
	public String dropFaena(int id) throws ExceptionModel{
        
		datosFaenaById(id);
		
        faenas.deleteById(id);
        
        return "Faena eliminada.";
    }
}
