package org.sid.cinema.dao;

import org.sid.cinema.entities.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public interface ICinemaRepository extends JpaRepository<Cinema, Long> {

}
