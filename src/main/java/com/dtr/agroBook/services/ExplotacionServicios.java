/**
 * 
 */
package com.dtr.agroBook.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import com.dtr.agroBook.entities.Explotacion;
import com.dtr.agroBook.exceptions.ExceptionModel;
import com.dtr.agroBook.repositories.Explotaciones;
 
/**
 * @author Daniel Tortonda Ruiz
 *
 */
@Service
public class ExplotacionServicios {

	
	@Autowired
	private Explotaciones explotaciones;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public Optional<List<Explotacion>> listaExplotaciones() throws ExceptionModel {
		
		Optional<List<Explotacion>> listaExplotaciones = Optional.of(explotaciones.findAll());
		
		if(listaExplotaciones.get().isEmpty() || listaExplotaciones.get() == null) {
            
            throw new ExceptionModel(HttpStatus.BAD_REQUEST, "No hay exploytaciones registradas");
            
        }else {
            
           return listaExplotaciones;
            
        }    
    }

    
	public Optional<Explotacion> datosExplotacionById(int id) throws ExceptionModel{
        
        Optional<Explotacion> searchedExplotacion = explotaciones.findById(id);
        
        if(searchedExplotacion.isEmpty()) {
            
            throw new ExceptionModel(HttpStatus.BAD_REQUEST, "Explotación no encontrada");
            
        }else {
            
            return searchedExplotacion;
            
        }
    }
	
	
	public Optional<Explotacion> datosExplotacionByName(String nombre) throws ExceptionModel{
	        
        Optional<Explotacion> searchedExplotacion = explotaciones.porNombreStrict(nombre);
        
        if(searchedExplotacion.isEmpty()) {
            
            throw new ExceptionModel(HttpStatus.BAD_REQUEST, "Explotación no encontrada");
            
        }else {
            
            return searchedExplotacion;
            
        }
    }
	
	
	public Optional<List<Explotacion>> listaExplotacionesByName(String nombre) throws ExceptionModel {
		
		Optional<List<Explotacion>> listaExplotaciones = explotaciones.porNombreAprox(nombre);
		
		if(listaExplotaciones.isEmpty() || listaExplotaciones.get().isEmpty()) {
            
            throw new ExceptionModel(HttpStatus.BAD_REQUEST, "No hay explotaciones registradas con ese nombre");
            
        }else {
            
           return listaExplotaciones;
            
        }    
    }
	
	
	public Optional<Explotacion> loggin(String nombre,String key) throws ExceptionModel{
        
		
        Optional<Explotacion> searchedExplotacion = explotaciones.porNombreStrict(nombre);
        
        
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        
        

        
        
        if(!b.matches(key, searchedExplotacion.get().getPassword())) {
            
            throw new ExceptionModel(HttpStatus.BAD_REQUEST, "Nombre o contraseña incorrecto");
            
        }else {
            
            return searchedExplotacion;
            
        }
    }
	
	
	public Optional<Explotacion> addNewExplotacion(Explotacion newExplo){
        
        Optional<Explotacion> newExplot = explotaciones.porNombreStrict(newExplo.getName());
        
        if(!newExplot.isEmpty()) {
        	
        	 throw new ExceptionModel(HttpStatus.BAD_REQUEST, "Nombre ya en uso"); 
        
        }else {
        	
        	newExplo.setPassword(passwordEncoder.encode(newExplo.getPassword()));
        	return Optional.of(explotaciones.save(newExplo));
        	
        }
         
	}
	
	
	public Optional<Explotacion> modExplotacion(Explotacion newExplo){
        
        Optional<Explotacion> newExplot = explotaciones.findById(newExplo.getId());
        
        if(newExplot.isEmpty()) {
        	
        	 throw new ExceptionModel(HttpStatus.BAD_REQUEST, "La explotación no se ha encontrado"); 
        
        }else {
        	explotaciones.save(newExplo);
        }
        return newExplot;
	}
	
	
	public String dropExplotacion(int id) throws ExceptionModel{
        
		datosExplotacionById(id);
		
        explotaciones.deleteById(id);
        
        return "Explotación eliminada.";
    }
}
