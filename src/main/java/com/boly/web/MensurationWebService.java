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
import com.boly.dao.MensurationRepository;
import com.boly.dao.ModelRepository;
import com.boly.dao.TailleurRepository;
import com.boly.dao.TypeDhabitRepository;
import com.boly.entity.Catalogue;
import com.boly.entity.Mensuration;
import com.boly.entity.Model;
import com.boly.entity.Tailleur;
import com.boly.entity.TypeDhabit;



@RestController
public class MensurationWebService {
	@Autowired
	private MensurationRepository mensurationRepository;
	
	/**
	 * @param client(nom)
	 * @return Long(Batiment.getId())
	 */
	@RequestMapping(value="/mensurations", method=RequestMethod.POST)
	public Long create(@RequestBody Mensuration param){
		param.setId(null);
		if (param.getNom()=="" || param.getNom()==null) {
			System.out.println("le nom est: "+param.getNom());
			return -1L;
		}
		try {
			param = mensurationRepository.save(param);			
		} catch (Exception e) {
			System.out.println("erreur de sauvegarde");
			System.out.println(e.getMessage());
			return -1L;
		}
		return param.getId();
	}
	@RequestMapping(value="/mensurations", method=RequestMethod.GET)
	public List<Mensuration> readAll(){
		return mensurationRepository.findAll();
	}
	@RequestMapping(value="/MensurationValue/{id}", method=RequestMethod.GET)
	public @ResponseBody Optional<Mensuration> readById(@PathVariable Long id){
		return mensurationRepository.findById(id);
	}
		/**
		 * @param etudiant([id, nom])
		 * @return 
		 */
		@RequestMapping(value="/mensurations", method=RequestMethod.PUT)
		public boolean changeInfo(@RequestBody Mensuration param){
			if (param.getId()==null) {
				System.out.println("id du Model est null");
				return false;
			}
			Optional<Mensuration> mensuration = mensurationRepository.findById(param.getId());
			if (!mensuration.isPresent()) {
				System.out.println("id du Model n'existe pas dans la base");
				return false;
			}
			if (param.getNom()!="" && param.getNom()!=null) {
				mensuration.get().setNom(param.getNom());
			}
			try {
				mensurationRepository.save(mensuration.get());			
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return false;
			}
			System.out.println("id du model d'id: "+mensuration.get().getId()+" a etait update");
			return true;
		}
	
	@RequestMapping(value="/mensurations/{id}", method=RequestMethod.DELETE)
	public @ResponseBody boolean delete(@PathVariable Long id){
		mensurationRepository.deleteById(id);
		return true;
	}

}
