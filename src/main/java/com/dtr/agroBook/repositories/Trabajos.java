package com.dtr.agroBook.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dtr.agroBook.entities.Trabajo;

public interface Trabajos extends JpaRepository<Trabajo, Integer> {

	
	@Query(nativeQuery = true, value="SELECT * FROM trabajos WHERE finca=:finca")
	Optional<List<Trabajo>> trabajosPorFinca(int finca);

	
	@Query(nativeQuery = true, value="SELECT * FROM trabajos as t JOIN fincas as f WHERE f.explotacion=:alcance AND f.id_finca=t.finca")
	Optional<List<Trabajo>> trabajosPorExplotacion(int alcance);

}
