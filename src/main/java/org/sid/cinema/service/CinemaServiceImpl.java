package org.sid.cinema.service;


import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.sid.cinema.dao.ICategorieRepository;
import org.sid.cinema.dao.ICinemaRepository;
import org.sid.cinema.dao.IFilmRepository;
import org.sid.cinema.dao.IPlaceRepository;
import org.sid.cinema.dao.IProjectionFilmRepository;
import org.sid.cinema.dao.ISalleRepository;
import org.sid.cinema.dao.ISeanceRepository;
import org.sid.cinema.dao.ITicketRepository;
import org.sid.cinema.dao.IVilleRepository;
import org.sid.cinema.entities.Categorie;
import org.sid.cinema.entities.Cinema;
import org.sid.cinema.entities.Film;
import org.sid.cinema.entities.Place;
import org.sid.cinema.entities.ProjectionFilm;
import org.sid.cinema.entities.Salle;
import org.sid.cinema.entities.Seance;
import org.sid.cinema.entities.Ticket;
import org.sid.cinema.entities.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@Transactional

public class CinemaServiceImpl implements ICinemaService {
	@Autowired
	IVilleRepository villeRepo;
	@Autowired
	ICinemaRepository cinemaRepo;
	@Autowired
	ISalleRepository salleRepo;
	@Autowired
	IPlaceRepository placeRepo;
	@Autowired
	ISeanceRepository seanceRepo;
	@Autowired
	ICategorieRepository categorieRepo;
	@Autowired
	IFilmRepository filmeRepo;
	@Autowired
	IProjectionFilmRepository projectionRepo;
	@Autowired
	ITicketRepository ticketRepo;
	
	@Override
	public void initVilles() {
		
		Stream.of("Casablanca", "Rabat" , "Marrakesh" , "Tanger" , "Agadir").forEach(nameville->{
			Ville ville = new Ville();
			ville.setName(nameville);
			villeRepo.save(ville);
			
		});
		
	}

	@Override
	public void initCinemas() {
		villeRepo.findAll().forEach(ville->{
			Stream.of("Mega", "Imax" , "Saharae" , "Andalous").forEach(namecinema->{
				Cinema cinema = new Cinema();
				cinema.setName(namecinema);
				cinema.setVille(ville);
				cinema.setNombreSalles(3 + (int) (Math.random()*7));
				cinemaRepo.save(cinema);
			});
		});
		
	}

	@Override
	public void initSalles() {
		cinemaRepo.findAll().forEach(cinema->{
			for (int i = 0; i < cinema.getNombreSalles(); i++) {
				Salle salle = new Salle();
				salle.setName("salle" + (i+1));
				salle.setNombrePlaces(15 + (int)(Math.random()*25));
				salle.setCinema(cinema);
				salleRepo.save(salle);
			}
		});
		
	}

	@Override
	public void initPlaces() {
		salleRepo.findAll().forEach(salle->{
			for (int i = 0; i < salle.getNombrePlaces(); i++) {
				Place place = new Place();
				place.setNumero(i+1);
				place.setSalle(salle);
				placeRepo.save(place);
			}
		});
		
	}

	@Override
	public void initSeance() {
	   SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm")	;
		
		Stream.of("09:00" , "11:00" , "13:00" , "15:00", "17:00" ,"19:00" , "21:00").forEach(dateseance->{
			Seance seance = new Seance();
			try {
				
				seance.setHeureDebut(dateFormat.parse(dateseance));
			} catch (Exception e) {
				e.printStackTrace();
			}
			seanceRepo.save(seance);
		});
		
	}

	@Override
	public void initCategories() {
		Stream.of("Comedy" , "Action" , "Drama" , "Fiction").forEach(nameCategory->{
			Categorie categorie = new Categorie();
			categorie.setName(nameCategory);
			categorieRepo.save(categorie);
		});
		
	}

	@Override
	public void initFilms() {
		double[] duree = new double[] {1, 1.5 , 2 , 2.5 , 3};
		List<Categorie> categories = categorieRepo.findAll();
		Stream.of("Forrest Gump", "City of Angels", "the note book",  "Verr Zaara" , "The Green Mile" , "Saw").forEach(namefilme->{
			Film film = new Film();
			film.setTitre(namefilme);
			film.setDuree(duree[new Random().nextInt(duree.length)]);
			film.setCategorie(categories.get(new Random().nextInt(categories.size())));
			film.setPhoto(namefilme.replaceAll(" ", "") + ".jpg");
			filmeRepo.save(film);
		});
		
		
	}

	@Override
	public void initProjections() {
		double[] prices = new double[] {30 , 40 , 55, 70 , 100};
		
		villeRepo.findAll().forEach(ville->{
			ville.getCinemas().forEach(cinema->{
				cinema.getSalles().forEach(salle->{
					filmeRepo.findAll().forEach(film->{
						seanceRepo.findAll().forEach(seance->{
							ProjectionFilm projection = new ProjectionFilm();
							projection.setSalle(salle);
							projection.setFilm(film);
							projection.setSeance(seance);
							projection.setPrix(prices[new Random().nextInt(prices.length)]);
							projectionRepo.save(projection);
						});
					});
				});
			});
		});
		
	}

	@Override
	public void initTickets() {
		projectionRepo.findAll().forEach(projetcion->{
			projetcion.getSalle().getPlaces().forEach(place->{
				Ticket ticket = new Ticket();
				ticket.setPlace(place);
				ticket.setProjectionFilm(projetcion);
				ticket.setPrix(projetcion.getPrix());
				ticket.setReserve(false);
				ticketRepo.save(ticket);
				
				
				
			});
			
			
		});
		
	}

}
