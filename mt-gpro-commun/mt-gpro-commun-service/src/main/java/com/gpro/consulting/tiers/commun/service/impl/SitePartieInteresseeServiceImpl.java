package com.gpro.consulting.tiers.commun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.SiteValue;
import com.gpro.consulting.tiers.commun.domaine.ISitePartieInteresseeDomaine;
import com.gpro.consulting.tiers.commun.service.ISitePartieInteresseeService;

/**
 * The Class CategoriePartieInteresseeImpl.
 * 
 * @author $mohamed
 */
@Service
@Transactional
public class SitePartieInteresseeServiceImpl implements ISitePartieInteresseeService {
	@Autowired
	ISitePartieInteresseeDomaine sitePartieInteresseeDomaine;

	@Override
	public SiteValue rechercheCategoriePartieInteresseeParId(SiteValue pSiteValue) {
		return sitePartieInteresseeDomaine.rechercheSitePartieInteresseeParId(pSiteValue);
	}

	@Override
	public List<SiteValue> listeSitePartieInteressee() {
		 return sitePartieInteresseeDomaine.listeSitePartieInteressee();
	}

	public ISitePartieInteresseeDomaine getSitePartieInteresseeDomaine() {
		return sitePartieInteresseeDomaine;
	}

	public void setSitePartieInteresseeDomaine(ISitePartieInteresseeDomaine sitePartieInteresseeDomaine) {
		this.sitePartieInteresseeDomaine = sitePartieInteresseeDomaine;
	}

	@Override
	public String creerSitePartieInteressee(SiteValue pSiteValue) {
		return sitePartieInteresseeDomaine.creerSitePartieInteressee(pSiteValue);
	}

	@Override
	public void supprimerSitePartieInteressee(SiteValue pSiteValue) {
		sitePartieInteresseeDomaine.supprimerSitePartieInteressee(pSiteValue);		
	}

	@Override
	public String modifierSitePartieInteressee(SiteValue pSiteValue) {
		return sitePartieInteresseeDomaine.modifierSitePartieInteressee(pSiteValue);
	}
  

}
