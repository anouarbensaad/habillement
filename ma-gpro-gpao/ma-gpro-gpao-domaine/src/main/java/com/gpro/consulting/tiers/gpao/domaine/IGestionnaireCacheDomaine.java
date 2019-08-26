package com.gpro.consulting.tiers.gpao.domaine;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.SousFamilleProduitValue;

/**
 * @author toshiba
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
	 * Méthode de récupératiuon de la liste des sousFamilleProduit
	 * 
	 * @return List<SousFamilleProduit>
	 */
	public List<SousFamilleProduitValue> getListeSousFamilleProduit();
}
