package com.gpro.consulting.tiers.commun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.DeviseValue;
import com.gpro.consulting.tiers.commun.domaine.IDeviseDomaine;
import com.gpro.consulting.tiers.commun.service.IDeviseService;

@Service
@Transactional
public class DevisePartieInteresseeServiceImpl implements IDeviseService{

		@Autowired
		IDeviseDomaine devisePartieInteresseeDomaine;
		
		/************************ Creation Matiere *****************************/
		@Override
		public String creerDevise(DeviseValue pDeviseValue) {
			return devisePartieInteresseeDomaine.creerDevise(pDeviseValue);
		}

		/************************ suppression Matiere ***************************/
		@Override
		public void supprimerDevise(Long pId) {
			devisePartieInteresseeDomaine.supprimerDevise(pId);
		}

		/************************ Modification Matiere ***************************/
		@Override
		public String modifierDevise(DeviseValue pDeviseValue) {
			return devisePartieInteresseeDomaine.modifierDevise(pDeviseValue);
		}

		/************************** Recherche Matiere *****************************/
		@Override
		public DeviseValue rechercheDeviseParId(DeviseValue pDeviseValue) {
			return devisePartieInteresseeDomaine.rechercheDeviseParId(pDeviseValue);
		}
		
		/************************** Liste des Matieres *****************************/
		@Override
		public List<DeviseValue> listeDevise() {
			return devisePartieInteresseeDomaine.listeDevise();
		}

		/************************** Getter & Setter *****************************/
		public IDeviseDomaine getdevisePartieInteresseeDomaine() {
			return devisePartieInteresseeDomaine;
		}

		public void setdevisePartieInteresseeDomaine(IDeviseDomaine devisePartieInteresseeDomaine) {
			this.devisePartieInteresseeDomaine = devisePartieInteresseeDomaine;
		}

		
	}
