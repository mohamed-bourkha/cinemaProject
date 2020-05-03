package org.sid.cinema.web;

import java.io.File;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.sid.cinema.dao.IFilmRepository;
import org.sid.cinema.dao.ITicketRepository;
import org.sid.cinema.entities.Film;
import org.sid.cinema.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@RestController
public class CinemaController {
	@Autowired
	private IFilmRepository filmRepo;
	@Autowired
	private ITicketRepository ticketRepo;

	// une Api Rest manuelle pour tester la recuperation d'une liste
//	@GetMapping ("/listFilms")
//	public List<Film> 	films(){
	// return filmRepo.findAll();
	// }
	
	// c'est l'un des methodes pertinente pour recuperer et lire un fichier 
	@GetMapping(path = "/imageFilm/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] images(@PathVariable(name = "id") Long id) throws Exception {
		Film f = filmRepo.findById(id).get();
		String photoName = f.getPhoto();
		File file = new File(System.getProperty("user.home") + "/cinema/images/" + photoName);
		Path path = Paths.get(file.toURI());
		return Files.readAllBytes(path);
	}

	// une methode qui doit etre place dons la couche metier mais
	// avec le test on a ajoute l'annotation Transactional pour que le traitement
	// demarre sans probleme
	@PostMapping("/payerTickets")
	@Transactional
	public List<Ticket> payerTickets(@RequestBody TicketForm ticketForm) {
		List<Ticket> listTickets = new ArrayList<Ticket>();
		ticketForm.getTickets().forEach(idTicket -> {
			Ticket ticket = ticketRepo.findById(idTicket).get();
			ticket.setNomClient(ticketForm.getNomClient());
			ticket.setReserve(true);
			ticket.setCodePayement(ticketForm.getCodePayement());
			ticketRepo.save(ticket);
			listTickets.add(ticket);
		});
		return listTickets;
	}

}

// Creation de la classe "TicketForm" pour quand puisse instancier
//l'objet "ticketForm" qui va contient les attributs qui va appliquer la methode 'payerTickets'   
@Data
class TicketForm {

	private String nomClient;
	private int codePayement;
	private List<Long> tickets = new ArrayList<>();

}
