package com.gpro.consulting.tiers.gc.domaine;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.SiteValue;

/**
 * @author $Ameni
 *
 */
public interface IGestionnaireCacheDomaine {

	/**
	 * Méthode de récupératiuon de la liste des clients
	 * 
	 * @return
	 */
	public List<PartieInteresseCacheValue> getListePartieInteressee();
	
	/**
	 * Méthode de récupératiuon de la liste des site
	 * 
	 * @return List<SiteValue>
	 */
	public List<SiteValue> getListeSite();

}
