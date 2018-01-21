 package com.boly.web;


import java.util.List; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.boly.dao.AdminRepository;
import com.boly.entity.Admin;
import urlParams.ChangeMotDePasseParam;



@RestController
public class AdminWebService {
	@Autowired
	private AdminRepository adminRepository;
	
	
		@RequestMapping(value="/admin", method=RequestMethod.GET)
		public Admin readAll(){
			return adminRepository.findAll().get(0);
		}
		/**
		 * @param etudiant([nom, prenom, email numTel])
		 * @return Long(Batiment.getId())
		 */
		@RequestMapping(value="/admin", method=RequestMethod.PUT)
		public boolean changeInfo(@RequestBody Admin param){

			List<Admin> admins = adminRepository.findAll();
			if (admins.isEmpty()) {
				return false;
			}
			Admin admin = admins.get(0);
			if (param.getNom()!="" && param.getNom()!=null) {
				admin.setNom(param.getNom());
			}
			if (param.getPrenom()!="" && param.getPrenom()!=null) {
				admin.setPrenom(param.getPrenom());
			}
			if (param.getEmail()!="" && param.getEmail()!=null) {
				admin.setEmail(param.getEmail());
			}
			if (param.getNumtel()!="" && param.getNumtel()!=null) {
				admin.setNumtel(param.getNumtel());
			}
			try {
				admin = adminRepository.save(admin);			
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return false;
			}
			return true;
		}

		/**
		 * @param param(id, nouveauMotDePasse, ancienMotDePasset)
		 * @return
		 */
		@RequestMapping(value="/admin/changerMotDePasse", method=RequestMethod.PUT )
		public @ResponseBody boolean changeMotDePasse(@RequestBody ChangeMotDePasseParam param){
			
			if (param.getAncienMotDePasse()==null || param.getAncienMotDePasse()=="" ) {
				System.out.println("param.ancienMotDePasse="+param.getAncienMotDePasse());
				return false;
			}
			if (param.getNouveauMotDePasse()==null || param.getNouveauMotDePasse()=="" ) {
				System.out.println("param.nouveauMotDePasse="+param.getNouveauMotDePasse());
				return false;
			}
			List<Admin> admins = adminRepository.findAll();
			if (admins.isEmpty()) {
				System.out.println("erreur: il n'y a pas d'administrateur dans la base de donnee");
				return false;
			}
			Admin admin = admins.get(0);
			if (admin.verifieMotDePasse(param.getAncienMotDePasse())) {
				admin.setMotDePasse(param.getNouveauMotDePasse());
				adminRepository.save(admin);
				System.out.println("mot de passe de l'admin "+admin.getEmail()+" est update");
				return true;
			}else {
				System.out.println("ancien mot de passe incorrecte");
				return false;
			}
		}

	
}
