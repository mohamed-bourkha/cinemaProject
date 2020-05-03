package org.sid.cinema.entities;

import java.io.Serializable;
import java.util.Collection;

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
public class Place implements Serializable {

	// attributs normales
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int numero;
	private double longitude, latitude, altitude;

	// attributs d'association
	@ManyToOne
	private Salle salle;
	@OneToMany(mappedBy = "place")
	@JsonProperty (access = Access.WRITE_ONLY)
	private Collection<Ticket> tickets;

}
