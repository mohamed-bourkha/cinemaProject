package org.sid.cinema.dao;

import org.sid.cinema.entities.Seance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public interface ISeanceRepository extends JpaRepository<Seance, Long> {

}
