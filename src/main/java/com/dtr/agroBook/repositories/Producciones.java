package com.dtr.agroBook.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dtr.agroBook.entities.Produccion;

public interface Producciones extends JpaRepository<Produccion, Integer> {
	
	
	@Query(nativeQuery = true, value="SELECT * FROM producciones WHERE finca=:finca")
    public Optional<List<Produccion>> produccionesPorFinca(int finca);
	
	@Query(nativeQuery = true, value="SELECT * FROM producciones as p JOIN fincas as f WHERE f.explotacion=:finca AND f.id_finca=p.finca")
    public Optional<List<Produccion>> produccionesPorExplotacion(int finca);
	
	@Query(nativeQuery = true, value="SELECT * FROM producciones WHERE finca=:finca AND campanna=:campanna")
    public Optional<Produccion> acumular(int finca, int campanna);
	
}
