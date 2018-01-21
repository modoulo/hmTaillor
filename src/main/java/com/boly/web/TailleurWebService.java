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
import com.boly.dao.TailleurRepository;
import com.boly.entity.Tailleur;
import com.boly.entity.Sexe;
import urlParams.ChangeMotDePasseParam;



@RestController
public class TailleurWebService {
	@Autowired
	private TailleurRepository tailleurRepository;
	
	/**
	 * @param client(nom, prenom, sexe, motDePasse[ email,numTel, adresse])
	 * @return Long(Batiment.getId())
	 */
	@RequestMapping(value="/tailleurs", method=RequestMethod.POST)
	public Long create(@RequestBody Tailleur param){
		param.setId(null);
		if (param.getNom()=="" || param.getNom()==null) {
			System.out.println("le nom est: "+param.getNom());
			return -1L;
		}

		if (param.getEmail()=="" || param.getEmail()==null) {
			System.out.println("l'email est: "+param.getEmail());
			return -1L;
		}
		if (param.isMotDePasseNull()) {
			System.out.println("le mot de passe est null: ");
			return -1L;
		}
		if (param.getPrenom()=="" || param.getPrenom()==null) {
			System.out.println("le prenom est: "+param.getPrenom());
			return -1L;
		}
		if (param.getSexe()==null || param.getSexe()==Sexe.I) {
			System.out.println("le sexe est: "+param.getSexe());
			return -1L;
		}
		if (tailleurRepository.findTopByEmail(param.getEmail()).isPresent()) {
			System.out.println("il existe un compte avec le mail : "+param.getEmail());
			return -1L;
		}
		try {
			param = tailleurRepository.save(param);			
		} catch (Exception e) {
			System.out.println("erreur de sauvegarde");
			System.out.println(e.getMessage());
			return -1L;
		}
		return param.getId();
	}
	
	@RequestMapping(value="/tailleurs", method=RequestMethod.GET)
	public List<Tailleur> readAll(){
		return tailleurRepository.findAll();
	}
	@RequestMapping(value="/tailleurs/{id}", method=RequestMethod.GET)
	public @ResponseBody Optional<Tailleur> readById(@PathVariable Long id){
		return tailleurRepository.findById(id);
	}

		/**
		 * @param etudiant([nom, prenom, sexe, email,adresse, numTel])
		 * @return Long(Batiment.getId())
		 */
		@RequestMapping(value="/tailleurs", method=RequestMethod.PUT)
		public boolean changeInfo(@RequestBody Tailleur param){
			if (param.getId()==null) {
				return false;
			}
			if (!tailleurRepository.findById(param.getId()).isPresent()) {
				return false;
			}
			Tailleur tailleur = tailleurRepository.findById(param.getId()).get();
			if (param.getNom()!="" && param.getNom()!=null) {
				tailleur.setNom(param.getNom());
			}
			if (param.getPrenom()!="" && param.getPrenom()!=null) {
				tailleur.setPrenom(param.getPrenom());
			}
			if (param.getSexe()!=null && param.getSexe()!=Sexe.I) {
				tailleur.setSexe(param.getSexe());
			}
			if (param.getEmail()!="" && param.getEmail()!=null) {
				tailleur.setEmail(param.getEmail());
			}
			if (param.getNumtel()!="" && param.getNumtel()!=null) {
				tailleur.setNumtel(param.getNumtel());
			}
			if (param.getAdresse()!="" && param.getAdresse()!=null) {
				tailleur.setAdresse(param.getAdresse());
			}
			try {
				tailleur = tailleurRepository.save(tailleur);			
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
		@RequestMapping(value="/etudiants/changerMotDePasse", method=RequestMethod.PUT )
		public @ResponseBody boolean changeMotDePasse(@RequestBody ChangeMotDePasseParam param){
			if (param.getId()==null) {
				System.out.println("param.id="+param.getId());
				return false;
			}
			if (param.getAncienMotDePasse()==null || param.getAncienMotDePasse()=="" ) {
				System.out.println("param.ancienMotDePasse="+param.getAncienMotDePasse());
				return false;
			}
			if (param.getNouveauMotDePasse()==null || param.getNouveauMotDePasse()=="" ) {
				System.out.println("param.nouveauMotDePasse="+param.getNouveauMotDePasse());
				return false;
			}
			Optional<Tailleur> etudiant = tailleurRepository.findById(param.getId());
			if (!etudiant.isPresent()) {
				System.out.println("L'etudiant est inconnu dans la basse de donnees");
				return false;
			}
			if (etudiant.get().verifieMotDePasse(param.getAncienMotDePasse())) {
				etudiant.get().setMotDePasse(param.getNouveauMotDePasse());
				tailleurRepository.save(etudiant.get());
				System.out.println("mot de passe de l'etudiant "+etudiant.get().getEmail()+" est update");
				return true;
			}else {
				System.out.println("ancien mot de passe incorrecte");
				return false;
			}
		}
		
		

		@RequestMapping(value="/tailleur/{id}", method=RequestMethod.DELETE)
		public @ResponseBody boolean delete(@PathVariable Long id){
			tailleurRepository.deleteById(id);
			return true;
		}

	
}
