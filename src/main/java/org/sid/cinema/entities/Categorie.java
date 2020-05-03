package org.sid.cinema.entities;

import java.io.Serializable;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Categorie implements Serializable {

	// attributs normales
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // cad code generer automatiquement
	private Long id; // c'est l'identifiant de la table et qui est generer automatiquement
	@Column(length = 50)
	private String name;

	// attributs d'association
	@OneToMany(mappedBy = "categorie") // "categorie" est attribut $ d'association $ de la classe Film (association
										// entre la classe Category et Film )
	@JsonProperty(access = Access.WRITE_ONLY)// on utilise l'annotation "JsonProperty" pour eviter au moment d'execution l'appel a cette classe
	private Collection<Film> films;

}
