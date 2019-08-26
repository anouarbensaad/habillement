package com.gpro.consulting.tiers.commun.domaine.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gpro.consulting.tiers.commun.coordination.value.MetrageValue;
import com.gpro.consulting.tiers.commun.domaine.IMetrageDomaine;
import com.gpro.consulting.tiers.commun.persistance.IMetragePersistance;

public class MetrageDomaineImpl   implements IMetrageDomaine{

	@Autowired
	IMetragePersistance ebMetragePersistance;
	
	/************************ Creation Metrage ***************************/
	@Override
	public String creerMetrage(MetrageValue pMetrageValue) {
		return ebMetragePersistance.creerMetrage(pMetrageValue);
	}

	@Override
	public void supprimerMetrage(Long pId) {
		ebMetragePersistance.supprimerMetrage(pId);
	}

	@Override
	public String modifierMetrage(MetrageValue pMetrageValue) {
		return ebMetragePersistance.modifierMetrage(pMetrageValue);
	}

	@Override
	public MetrageValue rechercheMetrageParId(MetrageValue pMetrageValue) {
		return ebMetragePersistance.rechercheMetrageParId(pMetrageValue);
	}

	@Override
	public List<MetrageValue> listeMetrage() {
		return ebMetragePersistance.listeMetrage();
	}
	
	/************************ Getter & Setter ***************************/
	public IMetragePersistance getEbMetragePersistance() {
		return ebMetragePersistance;
	}

	public void setEbMetragePersistance(IMetragePersistance ebMetragePersistance) {
		this.ebMetragePersistance = ebMetragePersistance;
	}

	
}
