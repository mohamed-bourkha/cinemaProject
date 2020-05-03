package org.sid.cinema.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//les annotations JPA sont : @Entity , @Table, @Id, @GeneratedValue , @Inheritance ,@ManyToOne , @ManyToMany , ....
//les annotations lombook sont : @Data , @NoArgConstructor , @AllArgConstructor , @ToString, ..... 

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Salle implements Serializable {

	// attributs normales
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 50)
	private String name;
	private int nombrePlaces;

	// attributs d'association
	@ManyToOne
	@JsonProperty(access = Access.WRITE_ONLY)// on utilise l'annotation "JsonProperty" pour eviter au moment d'execution l'appel a cette classe
	private Cinema cinema;
	@OneToMany(mappedBy = "salle")
	@JsonProperty(access = Access.WRITE_ONLY) // on utilise l'annotation "JsonProperty" pour eviter au moment d'execution l'appel a cette classe
	private Collection<Place> places;
	@OneToMany(mappedBy = "salle")
	@JsonProperty(access = Access.WRITE_ONLY)// on utilise l'annotation "JsonProperty" pour eviter au moment d'execution l'appel a cette classe
	private Collection<ProjectionFilm> projectionFilms;

}
