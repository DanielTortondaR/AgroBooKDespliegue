package com.dtr.agroBook.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dtr.agroBook.entities.Campanna;

public interface Campannas extends JpaRepository<Campanna, Integer> {

	@Query(nativeQuery = true, value="SELECT * FROM campanna WHERE alacance=:alcance")
    public Optional<List<Campanna>> campannasPorExplotacion(int alcance);

	@Query(nativeQuery = true, value="SELECT * FROM campanna WHERE alacance=:alcance AND fecha_fin=NULL")
    public Optional<List<Campanna>> campannasActivas(int alcance);
	
	@Query(nativeQuery = true, value="SELECT * FROM campanna as c JOIN producciones as p WHERE p.finca=:finca AND c.campanna=p.id_campanna")
	public Optional<List<Campanna>> campannasPorFinca(int finca);
}
