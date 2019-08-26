package com.gpro.consulting.tiers.gc.domaine.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.SiteValue;
import com.gpro.consulting.tiers.commun.service.IPartieInteresseeService;
import com.gpro.consulting.tiers.commun.service.ISitePartieInteresseeService;
import com.gpro.consulting.tiers.gc.domaine.IGestionnaireCacheDomaine;

/**
 * @author $Ameni
 *
 */

@Component
public class GestionnaireCacheDomaineImpl implements IGestionnaireCacheDomaine {
	@Autowired
	private IPartieInteresseeService partieInteresseService;
	
	@Autowired
	private ISitePartieInteresseeService sitePartieInteresseeService;


	@Override
	public List<PartieInteresseCacheValue> getListePartieInteressee() {
		return partieInteresseService.listePartieInteresseeCache();

	}

	@Override
	public List<SiteValue> getListeSite() {
		return sitePartieInteresseeService.listeSitePartieInteressee();
	}
	

}
