 package com.boly.web;


import java.util.List; 
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.boly.dao.ClientRepository;
import com.boly.dao.TailleurRepository;
import com.boly.entity.Client;
import com.boly.entity.Sexe;
import com.boly.entity.Tailleur;



@RestController
public class ClientWebService {
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private TailleurRepository tailleurRepository;
	
	/**
	 * @param client(nom, prenom, sexe, idTailleur[ email,numTel, adresse])
	 * @return Long(Batiment.getId())
	 */
	@RequestMapping(value="/clients", method=RequestMethod.POST)
	public Long create(@RequestBody Client client){
		client.setId(null);
		if (client.getNom()=="" || client.getNom()==null) {
			System.out.println("le nom est: "+client.getNom());
			return -1L;
		}
		if (client.getEmail()=="" || client.getEmail()==null) {
			System.out.println("l'email est: "+client.getEmail());
			return -1L;
		}
		if (client.getPrenom()=="" || client.getPrenom()==null) {
			System.out.println("le prenom est: "+client.getPrenom());
			return -1L;
		}
		if (client.getSexe()==null || client.getSexe()==Sexe.I) {
			System.out.println("le sexe est: "+client.getSexe());
			return -1L;
		}
		if (client.getIdTailleur()==null) {
			System.out.println("id tailleur: "+client.getIdTailleur());
			return -1L;
		}
		Optional<Tailleur> tailleur = tailleurRepository.findById(client.getIdTailleur());
		if (!tailleur.isPresent()) {
			System.out.println("le tailleur est inconnue dans la base de donnees ");
			return -1L;
		}
		client.setTailleur(tailleur.get());
		try {
			client = clientRepository.save(client);			
		} catch (Exception e) {
			System.out.println("erreur de sauvegarde");
			System.out.println(e.getMessage());
			return -1L;
		}
		return client.getId();
	}
	@RequestMapping(value="/clients", method=RequestMethod.GET)
	public List<Client> readAll(){
		return clientRepository.findAll();
	}
	@RequestMapping(value="/clients/{id}", method=RequestMethod.GET)
	public @ResponseBody Optional<Client> readById(@PathVariable Long id){
		return clientRepository.findById(id);
	}
	 /**
		 * @param etudiant([nom, prenom, sexe, email,adresse, numTel])
		 * @return Long(Batiment.getId())
		 */
		@RequestMapping(value="/clients", method=RequestMethod.PUT)
		public boolean changeInfo(@RequestBody Client param){
			if (param.getId()==null) {
				return false;
			}
			if (!clientRepository.findById(param.getId()).isPresent()) {
				return false;
			}
			Client client = clientRepository.findById(param.getId()).get();
			if (param.getNom()!="" && param.getNom()!=null) {
				client.setNom(param.getNom());
			}
			if (param.getPrenom()!="" && param.getPrenom()!=null) {
				client.setPrenom(param.getPrenom());
			}
			if (param.getSexe()!=null && param.getSexe()!=Sexe.I) {
				client.setSexe(param.getSexe());
			}
			if (param.getEmail()!="" && param.getEmail()!=null) {
				client.setEmail(param.getEmail());
			}
			if (param.getNumtel()!="" && param.getNumtel()!=null) {
				client.setNumtel(param.getNumtel());
			}
			if (param.getAdresse()!="" && param.getAdresse()!=null) {
				client.setAdresse(param.getAdresse());
			}
			try {
				client = clientRepository.save(client);			
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return false;
			}
			return true;
		}
	
	@RequestMapping(value="/clients/{id}", method=RequestMethod.DELETE)
	public @ResponseBody boolean delete(@PathVariable Long id){
		clientRepository.deleteById(id);
		return true;
	}

}
