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
import com.boly.dao.TypeDhabitRepository;
import com.boly.entity.Catalogue;
import com.boly.entity.Tailleur;
import com.boly.entity.TypeDhabit;



@RestController
public class TypeDhabitWebService {
	@Autowired
	private TypeDhabitRepository typeDhabitRepository;

	
	/**
	 * @param client(nom)
	 * @return Long(Batiment.getId())
	 */
	@RequestMapping(value="/typeDhabits", method=RequestMethod.POST)
	public Long create(@RequestBody TypeDhabit param){
		param.setId(null);
		if (param.getNom()=="" || param.getNom()==null) {
			System.out.println("le nom est: "+param.getNom());
			return -1L;
		}
		try {
			param = typeDhabitRepository.save(param);			
		} catch (Exception e) {
			System.out.println("erreur de sauvegarde");
			System.out.println(e.getMessage());
			return -1L;
		}
		return param.getId();
	}
	@RequestMapping(value="/typeDhabits", method=RequestMethod.GET)
	public List<TypeDhabit> readAll(){
		return typeDhabitRepository.findAll();
	}
	@RequestMapping(value="/typeDhabits/{id}", method=RequestMethod.GET)
	public @ResponseBody Optional<TypeDhabit> readById(@PathVariable Long id){
		return typeDhabitRepository.findById(id);
	}
		/**
		 * @param etudiant([id, nom])
		 * @return Long(Batiment.getId())
		 */
		@RequestMapping(value="/typeDhabits", method=RequestMethod.PUT)
		public boolean changeInfo(@RequestBody TypeDhabit param){
			if (param.getId()==null) {
				System.out.println("id du typeDhabit est null");
				return false;
			}
			Optional<TypeDhabit> typeDhabit = typeDhabitRepository.findById(param.getId());
			if (!typeDhabit.isPresent()) {
				System.out.println("id du typeDhabit n'existe pas dans la base");
				return false;
			}
			if (param.getNom()!="" && param.getNom()!=null) {
				typeDhabit.get().setNom(param.getNom());
			}
			try {
				typeDhabitRepository.save(typeDhabit.get());			
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return false;
			}
			System.out.println("id du typeDhabit d'id: "+typeDhabit.get().getId()+" a etait update");
			return true;
		}
	
	@RequestMapping(value="/typeDhabit/{id}", method=RequestMethod.DELETE)
	public @ResponseBody boolean delete(@PathVariable Long id){
		typeDhabitRepository.deleteById(id);
		return true;
	}

}
