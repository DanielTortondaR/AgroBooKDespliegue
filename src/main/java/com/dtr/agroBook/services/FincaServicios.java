/**
 * 
 */
package com.dtr.agroBook.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dtr.agroBook.entities.Finca;
import com.dtr.agroBook.exceptions.ExceptionModel;
import com.dtr.agroBook.repositories.Explotaciones;
import com.dtr.agroBook.repositories.Fincas;

/**
 * @author Daniel Tortona Ruiz
 *
 */
@Service
public class FincaServicios {

	
	@Autowired
	private Fincas fincas;
	@Autowired
	private Explotaciones explotaciones;
	
	
	public Optional<List<Finca>> listaFincasAll() throws ExceptionModel {
		
		Optional<List<Finca>> listaFincas = Optional.of(fincas.findAll());
		
		if(listaFincas.get().isEmpty() || listaFincas.get() == null) {
            
            throw new ExceptionModel(HttpStatus.BAD_REQUEST, "No hay fincas registradas");
            
        }else {
            
           return listaFincas;
            
        }    
    }
	
	
	public Optional<List<Finca>> listaFincasPropietario(int explotacion) throws ExceptionModel {
		
		Optional<List<Finca>> listaFinca = fincas.fincasPorExplotacion(explotacion);
		
		if(listaFinca.isEmpty() || listaFinca.get().isEmpty() ) {
            
            throw new ExceptionModel(HttpStatus.BAD_REQUEST, String.format("La explotacion %s no tiene fincas registradas", explotaciones.findById(explotacion).get().getName()));
            
        }else {
            
           return listaFinca;
            
        }    
    }

   
	public Optional<Finca> datosFincaById(int id) throws ExceptionModel{
        
        Optional<Finca> searchedFinca = fincas.findById(id) ;
        
        if(searchedFinca.isEmpty()) {
            
            throw new ExceptionModel(HttpStatus.BAD_REQUEST, "Explotación no encontrada");
            
        }else {
            
            return searchedFinca;
            
        }
    }
	
	
	public Optional<List<Finca>> datosFincaByName(String nombre, int id) throws ExceptionModel{
	        
		Optional<List<Finca>> searchedFinca = fincas.porNombreAprox(nombre, id);
        
        if(searchedFinca.isEmpty()) {
            
            throw new ExceptionModel(HttpStatus.BAD_REQUEST, "Explotación no encontrada");
            
        }else {
            
            return searchedFinca;
            
        }
    }
	
	public Optional<Finca> datosFincaByNameStrict(String nombre, int id) throws ExceptionModel{
        
		Optional<Finca> searchedFinca = fincas.porNombre(nombre, id);
        
        if(searchedFinca.isEmpty()) {
            
            throw new ExceptionModel(HttpStatus.BAD_REQUEST, "Explotación no encontrada");
            
        }else {
            
            return searchedFinca;
            
        }
    }
	
	
	public Optional<Finca> addNewFinca(Finca newFinca, int prop){
        
		Optional<List<Finca>> comprobacion = fincas.fincasPorExplotacion(prop);

		if(!comprobacion.isEmpty()) {
			for (Finca finca : comprobacion.get()) {
				if(newFinca.getName().equals(finca.getName())) {
					throw new ExceptionModel(HttpStatus.BAD_REQUEST, "Ese nombre de finca ya está en uso");
				}
			}
		}
		
		
        return Optional.of(fincas.save(newFinca));		
	}
	
	
	
	public Optional<Finca> modFinca(Finca modFinca){
        
        Optional<Finca> comprobacion = fincas.findById(modFinca.getIdFinca());
        
        if(comprobacion.isEmpty()) {
        	
        	 throw new ExceptionModel(HttpStatus.BAD_REQUEST, "La explotación no se ha encontrado"); 
        
        }else {
        	return Optional.of( fincas.save(modFinca));
        }
         
	}
	
	
	public String dropFinca(int id) throws ExceptionModel{
        
		datosFincaById(id);
		
        fincas.deleteById(id);
        
        return "Finca eliminada.";
    }
}
