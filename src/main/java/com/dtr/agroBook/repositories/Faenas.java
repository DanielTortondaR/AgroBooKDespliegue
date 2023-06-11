
package com.dtr.agroBook.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dtr.agroBook.entities.Faena;

/**
 * @author Daniel Tortonda Ruiz
 *
 */
public interface Faenas extends JpaRepository<Faena, Integer> {

	@Query(nativeQuery = true, value="SELECT * FROM faenas WHERE alacance=:alcance OR alacance=-1")
    public Optional<List<Faena>> faenasPorExplotacion(int alcance);
	
	@Query(nativeQuery = true, value="SELECT * FROM faenas as f JOIN trabajos as t WHERE t.id_trabajo=:idTrabajo AND t.faena=f.id_faena")
	public Optional<Faena> faenasPorTrabajo(int idTrabajo);
}
