package com.dtr.agroBook.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dtr.agroBook.entities.Plantacion;

public interface Plantaciones extends JpaRepository<Plantacion, Integer> {

	@Query(nativeQuery = true, value="SELECT * FROM plantaciones as p JOIN fincas as f WHERE f.explotacion=:alcance AND f.id_finca=p.finca")
    public Optional<List<Plantacion>> plantacionesPorExplotacion(int alcance);
	
	@Query(nativeQuery = true, value="SELECT * FROM plantaciones WHERE finca=:finca")
    public Optional<List<Plantacion>> plantacionesPorFinca(int finca);
	
	@Query(nativeQuery = true, value="SELECT * FROM plantaciones WHERE finca=:finca AND fecha_arranque=NUll")
    public Optional<Plantacion> plantacionActiva(int finca);
}
