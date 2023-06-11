
package com.dtr.agroBook.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dtr.agroBook.entities.Cultivo;
import com.dtr.agroBook.exceptions.ExceptionModel;
import com.dtr.agroBook.repositories.Cultivos;

/**
 * @author Daniel Tortonda Ruiz
 */
@Service
public class CultivoServicios {
	
	@Autowired
	private Cultivos cultivos;
	
	
	
	public Optional<List<Cultivo>> listaCultivosAll() throws ExceptionModel {
		
		Optional<List<Cultivo>> listaCultivos = Optional.of(cultivos.findAll());
		
		if(listaCultivos.get().isEmpty() || listaCultivos.get() == null) {
            
            throw new ExceptionModel(HttpStatus.BAD_REQUEST, "No hay cultivos registrados");
            
        }else {
            
           return listaCultivos;
            
        }    
    }
	
	
	public Optional<List<Cultivo>> listaCultivosPropietario(int alcance) throws ExceptionModel {
		
		Optional<List<Cultivo>> listaCultivo = cultivos.cultivosPorExplotacion(alcance);
		
		if(listaCultivo.isEmpty() || listaCultivo.get().isEmpty()) {
            
            throw new ExceptionModel(HttpStatus.BAD_REQUEST, "No tiene cultivos registrados");
            
        }else {
            
           return listaCultivo;
            
        }    
    }

   
	public Optional<Cultivo> datosCultivoById(int id) throws ExceptionModel{
        
        Optional<Cultivo> searchedCultivo = cultivos.findById(id) ;
        
        if(searchedCultivo.isEmpty()) {
            
            throw new ExceptionModel(HttpStatus.BAD_REQUEST, "Cultivo no encontrado");
            
        }else {
            
            return searchedCultivo;
            
        }
    }

	
	
	public Optional<Cultivo> addNewCultivo(Cultivo newCultivo, int prop){
        
		Optional<List<Cultivo>> comprobacion = listaCultivosPropietario(prop);
        
		if(!comprobacion.isEmpty()) {
			for (Cultivo cultivo : comprobacion.get()) {
				if(newCultivo.getNombreCultivo().equals(cultivo.getNombreCultivo()) && newCultivo.getVariedad().equals(cultivo.getVariedad())) {
					throw new ExceptionModel(HttpStatus.BAD_REQUEST, "Ese nombre de cultivo ya está en uso");
				}
			}
		}       	
        return Optional.of(cultivos.save(newCultivo));
	}
	
	
	
	public Optional<Cultivo> modCultivo(Cultivo modCultivo){
        
        Optional<Cultivo> comprobacion = cultivos.findById(modCultivo.getIdCultivos());
        
        if(comprobacion.isEmpty()) {
        	
        	 throw new ExceptionModel(HttpStatus.BAD_REQUEST, "La explotación no se ha encontrado"); 
        
        }else {
        	return Optional.of( cultivos.save(modCultivo));
        }
         
	}
	
	
	public String dropCultivo(int id) throws ExceptionModel{
        
		datosCultivoById(id);
		
        cultivos.deleteById(id);
        
        return "Cultivo eliminada.";
    }
}
