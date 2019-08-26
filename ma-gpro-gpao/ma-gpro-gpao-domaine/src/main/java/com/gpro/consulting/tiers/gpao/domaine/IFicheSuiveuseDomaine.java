package com.gpro.consulting.tiers.gpao.domaine;

import com.gpro.consulting.tiers.gpao.coordination.fichesuiveuse.vue.FicheSuiveuseVue;

/**
 * Fiche Suiveuse Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 20/05/2016
 *
 */
public interface IFicheSuiveuseDomaine {

	public FicheSuiveuseVue getByOrdreFabricationId(Long ordreFabricationId);

}
