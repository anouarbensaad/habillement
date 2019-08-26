package com.gpro.consulting.tiers.commun.domaine.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gpro.consulting.tiers.commun.coordination.value.SiteValue;
import com.gpro.consulting.tiers.commun.domaine.ISitePartieInteresseeDomaine;
import com.gpro.consulting.tiers.commun.persistance.ISitePartieInteresseePersistance;

// TODO: Auto-generated Javadoc
/**
 * The Class CategoriePartieInteresseeImpl.
 * 
 * @author $mohamed
 */
public class SitePartieInteresseeDomaineImpl implements
		ISitePartieInteresseeDomaine {

	/** The site partie interesse persistance. */
	@Autowired
	ISitePartieInteresseePersistance sitePartieInteressePersistance;

	/**
	 * Gets the site partie interesse persistance.
	 *
	 * @return the site partie interesse persistance
	 */
	public ISitePartieInteresseePersistance getSitePartieInteressePersistance() {
		return sitePartieInteressePersistance;
	}

	/**
	 * Sets the site partie interesse persistance.
	 *
	 * @param sitePartieInteressePersistance the new site partie interesse persistance
	 */
	public void setSitePartieInteressePersistance(ISitePartieInteresseePersistance sitePartieInteressePersistance) {
		this.sitePartieInteressePersistance = sitePartieInteressePersistance;
	}

	/* (non-Javadoc)
	 * rechercher par id site
	 */
	@Override
	public SiteValue rechercheSitePartieInteresseeParId(SiteValue pSiteValue) {
		return sitePartieInteressePersistance.rechercheSitePartieInteresseeParId(pSiteValue);
	}

	/* (non-Javadoc)
	 * lister site
	 */
	@Override
	public List<SiteValue> listeSitePartieInteressee() {
		return sitePartieInteressePersistance.listeSitePartieInteressee();
	}

	/* (non-Javadoc)
	 * creer site 
	 */
	@Override
	public String creerSitePartieInteressee(SiteValue pSiteValue) {
		return sitePartieInteressePersistance.creerSitePartieInteressee(pSiteValue);
	}

	/* (non-Javadoc)
	 * supprimer site 
	 */
	@Override
	public void supprimerSitePartieInteressee(SiteValue pSiteValue) {
		sitePartieInteressePersistance.supprimerSitePartieInteressee(pSiteValue);		
	}

	/* (non-Javadoc)
	 * modifier site
	 */
	@Override
	public String modifierSitePartieInteressee(SiteValue pSiteValue) {
		return sitePartieInteressePersistance.modifierSitePartieInteressee(pSiteValue);
	}

	
	

}
