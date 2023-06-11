/**
 * 
 */
package com.dtr.agroBook.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dtr.agroBook.entities.Campanna;
import com.dtr.agroBook.exceptions.ExceptionModel;
import com.dtr.agroBook.repositories.Campannas;

/**
 * @author Daniel Tortonda Ruiz
 *
 */

@Service
public class CampannaServicios {

	@Autowired
	private Campannas campannas;
	
	public Optional<List<Campanna>> listaCampannasAll() throws ExceptionModel {
		
		Optional<List<Campanna>> listaCampannas = Optional.of(campannas.findAll());
		
		if(listaCampannas.get().isEmpty() || listaCampannas.get() == null) {
            
            throw new ExceptionModel(HttpStatus.BAD_REQUEST, "No hay campañas registradas");
            
        }else {
            
           return listaCampannas;
            
        }    
    }
	
	
	public Optional<List<Campanna>> listaCampannasPropietario(int alcance) throws ExceptionModel {
		
		
            
           return campannas.campannasPorExplotacion(alcance);
            
           
    }
	
	public Optional<List<Campanna>> listaCampannasActivas(int alcance) throws ExceptionModel {
		
		Optional<List<Campanna>> listaCampanna = campannas.campannasActivas(alcance);
		
		if(listaCampanna.isEmpty() || listaCampanna.get().isEmpty()) {
            
            throw new ExceptionModel(HttpStatus.BAD_REQUEST, "No tiene campañas registrados");
            
        }else {
            
           return listaCampanna;
            
        }    
    }
	
	public Optional<List<Campanna>> listaCampannasFinca(int finca) throws ExceptionModel {
		
		Optional<List<Campanna>> listaCampanna = campannas.campannasPorFinca(finca);
		
		if(listaCampanna.isEmpty() || listaCampanna.get().isEmpty()) {
            
            throw new ExceptionModel(HttpStatus.BAD_REQUEST, "No tiene campannas registrados");
            
        }else {
            
           return listaCampanna;
            
        }    
    }

   
	public Optional<Campanna> datosCampannaById(int id) throws ExceptionModel{
        
        Optional<Campanna> searchedCampanna = campannas.findById(id) ;
        
        if(searchedCampanna.isEmpty()) {
            
            throw new ExceptionModel(HttpStatus.BAD_REQUEST, "Campanna no encontrado");
            
        }else {
            
            return searchedCampanna;
            
        }
    }

	
	
	public Optional<Campanna> addNewCampanna(Campanna newCampanna, int prop){
        
		Optional<List<Campanna>> comprobacion = listaCampannasPropietario(prop);
        
		if(!comprobacion.isEmpty()) {
			for (Campanna campanna : comprobacion.get()) {
				if(newCampanna.getNombre().equals(campanna.getNombre())) {
					throw new ExceptionModel(HttpStatus.BAD_REQUEST, "Ese nombre de campaña ya está en uso");
				}
			}
		}       	
        return Optional.of(campannas.save(newCampanna));
	}
	
	
	
	public Optional<Campanna> modCampanna(Campanna modCampanna){
        
        Optional<Campanna> comprobacion = campannas.findById(modCampanna.getIdCampanna());
        
        if(comprobacion.isEmpty()) {
        	
        	 throw new ExceptionModel(HttpStatus.BAD_REQUEST, "La campaña no se ha encontrado"); 
        
        }else {
        	return Optional.of( campannas.save(modCampanna));
        }
         
	}
	
	
	public String dropCampanna(int id) throws ExceptionModel{
        
		datosCampannaById(id);
		
        campannas.deleteById(id);
        
        return "Campanna eliminada.";
    }
}

