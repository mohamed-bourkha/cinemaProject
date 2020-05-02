package org.sid.cinema;

import org.sid.cinema.service.ICinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CinemaApplication implements CommandLineRunner {

	@Autowired
	ICinemaService cinemaServImp;
	
	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
		
		
	}

	@Override
	public void run(String... args) throws Exception {
		cinemaServImp.initVilles();
		cinemaServImp.initCinemas();
		cinemaServImp.initSalles();
		cinemaServImp.initPlaces();
		cinemaServImp.initSeance();
		cinemaServImp.initCategories();
		cinemaServImp.initFilms();
		cinemaServImp.initProjections();
		cinemaServImp.initTickets();
	
		
	}
	

}
