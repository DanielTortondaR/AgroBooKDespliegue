package com.dtr.agroBook.repositories;
import java.util.List;
import java.util.Optional;

/**
 * @author Daniel Tortonda Ruiz
 *
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dtr.agroBook.entities.Explotacion;



public interface Explotaciones extends JpaRepository<Explotacion, Integer> {

	@Query(nativeQuery = true, value="SELECT * FROM explotaciones WHERE NOMBRE=:nombre and PASSWORD=:key")
    public Optional<Explotacion> loggin(String nombre, String key);
	
	@Query(nativeQuery = true, value="SELECT * FROM explotaciones WHERE NOMBRE=:nombre")
    public Optional<Explotacion> porNombreStrict(String nombre);
    
	@Query(nativeQuery = true, value="SELECT * FROM explotaciones WHERE nombre LIKE %:nombre%")
    public Optional<List<Explotacion>> porNombreAprox(String nombre);
}
