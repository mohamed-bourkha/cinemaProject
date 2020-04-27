package org.sid.cinema.dao;

import org.sid.cinema.entities.ProjectionFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public interface IProjectionFilmRepository extends JpaRepository<ProjectionFilm, Long> {

}
