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

import com.boly.dao.CatalogueRepository;
import com.boly.dao.TailleurRepository;
import com.boly.entity.Catalogue;
import com.boly.entity.Tailleur;



@RestController
public class CalogueWebService {
	@Autowired
	private CatalogueRepository catalogueRepository;
	@Autowired
	private TailleurRepository tailleurRepository;
	
	/**
	 * @param client(nom, idTailleur)
	 * @return Long(Batiment.getId())
	 */
	@RequestMapping(value="/catalogues", method=RequestMethod.POST)
	public Long create(@RequestBody Catalogue param){
		param.setId(null);
		if (param.getNom()=="" || param.getNom()==null) {
			System.out.println("le nom est: "+param.getNom());
			return -1L;
		}
		if (param.getIdTailleur()==null) {
			System.out.println("id tailleur: "+param.getIdTailleur());
			return -1L;
		}
		Optional<Tailleur> tailleur = tailleurRepository.findById(param.getIdTailleur());
		if (!tailleur.isPresent()) {
			System.out.println("le tailleur est inconnue dans la base de donnees ");
			return -1L;
		}
		param.setTailleur(tailleur.get());
		try {
			param = catalogueRepository.save(param);			
		} catch (Exception e) {
			System.out.println("erreur de sauvegarde");
			System.out.println(e.getMessage());
			return -1L;
		}
		return param.getId();
	}
	@RequestMapping(value="/catalogues", method=RequestMethod.GET)
	public List<Catalogue> readAll(){
		return catalogueRepository.findAll();
	}
	@RequestMapping(value="/catalogues/{id}", method=RequestMethod.GET)
	public @ResponseBody Optional<Catalogue> readById(@PathVariable Long id){
		return catalogueRepository.findById(id);
	}
		/**
		 * @param etudiant([id, nom])
		 * @return Long(Batiment.getId())
		 */
		@RequestMapping(value="/catalogues", method=RequestMethod.PUT)
		public boolean changeInfo(@RequestBody Catalogue param){
			if (param.getId()==null) {
				System.out.println("id du catalogue est null");
				return false;
			}
			Optional<Catalogue> catalogue = catalogueRepository.findById(param.getId());
			if (!catalogue.isPresent()) {
				System.out.println("id du catalogue n'existe pas dans la base");
				return false;
			}
			if (param.getNom()!="" && param.getNom()!=null) {
				catalogue.get().setNom(param.getNom());
			}
			try {
				catalogueRepository.save(catalogue.get());			
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return false;
			}
			System.out.println("id du catalogue d'id: "+catalogue.get().getId()+" a etait update");
			return true;
		}
	
	@RequestMapping(value="/catalogues/{id}", method=RequestMethod.DELETE)
	public @ResponseBody boolean delete(@PathVariable Long id){
		catalogueRepository.deleteById(id);
		return true;
	}

}
