package com.dtr.agroBook.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dtr.agroBook.entities.Finca;

public interface Fincas extends JpaRepository<Finca, Integer> {

	
	@Query(nativeQuery = true, value="SELECT * FROM fincas WHERE explotacion=:idExplotacion ")
	public Optional<List<Finca>> fincasPorExplotacion(int idExplotacion);
	
	
	@Query(nativeQuery = true, value="SELECT * FROM fincas WHERE nombre LIKE %:nombre% and explotacion=:id")
    public Optional<List<Finca>> porNombreAprox(String nombre, int id);
	
	@Query(nativeQuery = true,  value="SELECT * FROM fincas WHERE nombre=:nombre and explotacion=:id")
    public Optional<Finca> porNombre(String nombre, int id);
	
    @Query(nativeQuery = true, value="INSERT INTO fincas VALUES (-1,:fecha,:nombre,:idrxp)")
    public void add(String nombre,String fecha, int idrxp);
	
	
}
