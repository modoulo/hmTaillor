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
import com.boly.dao.ClientRepository;
import com.boly.dao.MensurationRepository;
import com.boly.dao.MensurationValueRepository;
import com.boly.dao.ModelRepository;
import com.boly.dao.TailleurRepository;
import com.boly.dao.TypeDhabitRepository;
import com.boly.entity.Catalogue;
import com.boly.entity.Client;
import com.boly.entity.Mensuration;
import com.boly.entity.MensurationValue;
import com.boly.entity.Model;
import com.boly.entity.Tailleur;
import com.boly.entity.TypeDhabit;



@RestController
public class MensurationValuesWebService {
	@Autowired
	private MensurationValueRepository mensurationValueRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private MensurationRepository mensurationRepository;
	
	/**
	 * @param client(nom, valeur, idClient, idMensuration)
	 * @return Long(Batiment.getId())
	 */
	@RequestMapping(value="/mensurationValues", method=RequestMethod.POST)
	public Long create(@RequestBody MensurationValue param){
		param.setId(null);
		if (param.getValeur()==null) {
			System.out.println("la valeur est: "+param.getValeur());
			return -1L;
		}
		if (param.getValeur()==0.0) {
			System.out.println("la valeur est egale a : "+param.getValeur());
			return -1L;
		}
		if (param.getIdClient()==null) {
			System.out.println("id client: "+param.getIdClient());
			return -1L;
		}
		if (param.getIdMensuration()==null) {
			System.out.println("id de la mensuration: "+param.getIdClient());
			return -1L;
		}
		Optional<Client> client = clientRepository.findById(param.getIdClient());
		Optional<Mensuration> mensuration = mensurationRepository.findById(param.getIdMensuration());
		if (!client.isPresent()) {
			System.out.println("le client est inconnue dans la base de donnees ");
			return -1L;
		}
		if (!mensuration.isPresent()) {
			System.out.println("la mensuration est inconnue dans la base de donnees ");
			return -1L;
		}
		param.setClient(client.get());
		param.setMensuration(mensuration.get());
		try {
			param = mensurationValueRepository.save(param);			
		} catch (Exception e) {
			System.out.println("erreur de sauvegarde");
			System.out.println(e.getMessage());
			return -1L;
		}
		return param.getId();
	}
	@RequestMapping(value="/mensurationValues", method=RequestMethod.GET)
	public List<MensurationValue> readAll(){
		return mensurationValueRepository.findAll();
	}
	@RequestMapping(value="/mensurationValues/{id}", method=RequestMethod.GET)
	public @ResponseBody Optional<MensurationValue> readById(@PathVariable Long id){
		return mensurationValueRepository.findById(id);
	}
		/**
		 * @param etudiant([id, valeur])
		 * @return 
		 */
		@RequestMapping(value="/mensurationValues", method=RequestMethod.PUT)
		public boolean changeInfo(@RequestBody MensurationValue param){
			if (param.getId()==null) {
				System.out.println("id du Model est null");
				return false;
			}
			Optional<MensurationValue> mensurationValue = mensurationValueRepository.findById(param.getId());
			if (!mensurationValue.isPresent()) {
				System.out.println("id du MensurationValue n'existe pas dans la base");
				return false;
			}
			if (param.getValeur()!=null) {
				if (param.getValeur()!=0.0) {
					mensurationValue.get().setValeur(param.getValeur());
				}
				System.out.println("la mensuration ne peut etre egale a: "+param.getValeur());
			}
			try {
				mensurationValueRepository.save(mensurationValue.get());			
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return false;
			}
			System.out.println("id du MensurationValue d'id: "+mensurationValue.get().getId()+" a etait update");
			return true;
		}
	
	@RequestMapping(value="/mensurationValues/{id}", method=RequestMethod.DELETE)
	public @ResponseBody boolean delete(@PathVariable Long id){
		mensurationValueRepository.deleteById(id);
		return true;
	}

}
