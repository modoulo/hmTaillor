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
import com.boly.dao.ModelRepository;
import com.boly.dao.TailleurRepository;
import com.boly.dao.TypeDhabitRepository;
import com.boly.entity.Catalogue;
import com.boly.entity.Model;
import com.boly.entity.Tailleur;
import com.boly.entity.TypeDhabit;



@RestController
public class ModelWebService {
	@Autowired
	private ModelRepository modelRepository;
	@Autowired
	private CatalogueRepository catalogueRepository;
	@Autowired
	private TypeDhabitRepository typeDhabitRepository;
	
	/**
	 * @param client(nom, idCatalogue, idTypeDhabit, [description])
	 * @return Long(Batiment.getId())
	 */
	@RequestMapping(value="/models", method=RequestMethod.POST)
	public Long create(@RequestBody Model param){
		param.setId(null);
		if (param.getNom()=="" || param.getNom()==null) {
			System.out.println("le nom est: "+param.getNom());
			return -1L;
		}
		if (param.getIdCatalogue()==null) {
			System.out.println("id tailleur: "+param.getIdCatalogue());
			return -1L;
		}
		if (param.getIdTypeDhabit()==null) {
			System.out.println("id tailleur: "+param.getIdTypeDhabit());
			return -1L;
		}
		Optional<Catalogue> catalogue = catalogueRepository.findById(param.getIdCatalogue());
		Optional<TypeDhabit> typeDhabit = typeDhabitRepository.findById(param.getIdTypeDhabit());
		if (!catalogue.isPresent()) {
			System.out.println("le catalogue est inconnue dans la base de donnees ");
			return -1L;
		}
		if (!typeDhabit.isPresent()) {
			System.out.println("le type d'habit est inconnue dans la base de donnees ");
			return -1L;
		}
		param.setCatalogue(catalogue.get());
		param.setTypeDhabit(typeDhabit.get());
		try {
			param = modelRepository.save(param);			
		} catch (Exception e) {
			System.out.println("erreur de sauvegarde");
			System.out.println(e.getMessage());
			return -1L;
		}
		return param.getId();
	}
	@RequestMapping(value="/models", method=RequestMethod.GET)
	public List<Model> readAll(){
		return modelRepository.findAll();
	}
	@RequestMapping(value="/models/{id}", method=RequestMethod.GET)
	public @ResponseBody Optional<Model> readById(@PathVariable Long id){
		return modelRepository.findById(id);
	}
		/**
		 * @param etudiant([id, nom, description])
		 * @return 
		 */
		@RequestMapping(value="/models", method=RequestMethod.PUT)
		public boolean changeInfo(@RequestBody Model param){
			if (param.getId()==null) {
				System.out.println("id du Model est null");
				return false;
			}
			Optional<Model> model = modelRepository.findById(param.getId());
			if (!model.isPresent()) {
				System.out.println("id du Model n'existe pas dans la base");
				return false;
			}
			if (param.getNom()!="" && param.getNom()!=null) {
				model.get().setNom(param.getNom());
			}
			if (param.getDescription()!="" && param.getCatalogue()!=null) {
				model.get().setDescription(param.getDescription());
			}
			try {
				modelRepository.save(model.get());			
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return false;
			}
			System.out.println("id du model d'id: "+model.get().getId()+" a etait update");
			return true;
		}
	
	@RequestMapping(value="/models/{id}", method=RequestMethod.DELETE)
	public @ResponseBody boolean delete(@PathVariable Long id){
		modelRepository.deleteById(id);
		return true;
	}

}
