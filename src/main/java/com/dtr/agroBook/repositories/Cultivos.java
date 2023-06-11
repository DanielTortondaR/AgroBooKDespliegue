package com.dtr.agroBook.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dtr.agroBook.entities.Cultivo;

public interface Cultivos extends JpaRepository<Cultivo, Integer> {

	@Query(nativeQuery = true, value="SELECT * FROM cultivos WHERE alcance=:alcance OR alcance=-1")
    public Optional<List<Cultivo>> cultivosPorExplotacion(int alcance);
	
	
}
