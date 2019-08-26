package com.gpro.consulting.tiers.gpao.domaine.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.SousFamilleProduitValue;
import com.gpro.consulting.tiers.commun.service.IPartieInteresseeService;
import com.gpro.consulting.tiers.commun.service.ISousFamilleProduitService;
import com.gpro.consulting.tiers.gpao.domaine.IGestionnaireCacheDomaine;

/**
 * @author $Ameni
 *
 */

@Component
public class GestionnaireCacheDomaineImpl implements IGestionnaireCacheDomaine {

	@Autowired
	private IPartieInteresseeService partieInteresseService;
	
	@Autowired
	private ISousFamilleProduitService sousFamilleProduitService;

	@Override
	public List<PartieInteresseCacheValue> getListePartieInteressee() {
		
		return partieInteresseService.listePartieInteresseeCache();
	}
	
	@Override
	public List<SousFamilleProduitValue> getListeSousFamilleProduit() {
		
		return sousFamilleProduitService.listeSousFamilleProduit();
	}
	
}
