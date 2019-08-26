package com.gpro.consulting.tiers.commun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.MetrageValue;
import com.gpro.consulting.tiers.commun.domaine.IMetrageDomaine;
import com.gpro.consulting.tiers.commun.service.IMetrageService;

@Service
@Transactional
public class MetrageServiceImpl implements IMetrageService{

	@Autowired
	IMetrageDomaine ebMetrageDomaine;
	
	/************************ Creation Metrage *****************************/
	@Override
	public String creerMetrage(MetrageValue pMetrageValue) {
		return ebMetrageDomaine.creerMetrage(pMetrageValue);
	}
	
	/************************ suppression Metrage  ***************************/
	@Override
	public void supprimerMetrage(Long pId) {
		ebMetrageDomaine.supprimerMetrage(pId);
	}

	/************************ Modification Metrage ***************************/
	@Override
	public String modifierMetrage(MetrageValue pMetrageValue) {
		return ebMetrageDomaine.modifierMetrage(pMetrageValue);
	}

	/************************** Recherche Metrage *****************************/
	@Override
	public MetrageValue rechercheMetrageParId(MetrageValue pMetrageValue) {
		return ebMetrageDomaine.rechercheMetrageParId(pMetrageValue);
	}

	@Override
	public List<MetrageValue> listeMetrage() {
		return ebMetrageDomaine.listeMetrage();
	}
	
	/************************** Getter & Setter *****************************/
	public IMetrageDomaine getEbMetrageDomaine() {
		return ebMetrageDomaine;
	}

	public void setEbMetrageDomaine(IMetrageDomaine ebMetrageDomaine) {
		this.ebMetrageDomaine = ebMetrageDomaine;
	}

	
}
