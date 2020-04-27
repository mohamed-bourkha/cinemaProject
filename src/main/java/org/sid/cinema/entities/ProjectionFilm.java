package org.sid.cinema.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//les annotations JPA sont : @Entity , @Table, @Id, @GeneratedValue , @Inheritance ,@ManyToOne , @ManyToMany , ....
//les annotations lombook sont : @Data , @NoArgConstructor , @AllArgConstructor , @ToString, ..... 

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectionFilm implements Serializable {

	// attributs normales
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateProjection;
	private double prix;

	// attributs d'association
	@ManyToOne
	private Salle salle;
	@ManyToOne
	private Film film;
	@OneToMany(mappedBy = "projectionFilm")
	private Collection<Ticket> tickets;
	@ManyToOne
	private Seance seance;

}
