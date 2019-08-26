package com.gpro.consulting.tiers.gs.persitance;

import java.util.List;

import com.gpro.consulting.tiers.gs.coordination.value.EmplacementValue;


// TODO: Auto-generated Javadoc
/**
 * The Interface IEmplacementPersistance.
 */
public interface IEmplacementPersistance {
	
	  /**
  	 * Creer emplacement.
  	 *
  	 * @param pEmplacementValue the emplacement value
  	 * @return the string
  	 */
  	public String creerEmplacement(EmplacementValue pEmplacementValue);

	  /**
  	 * Supprimer emplacement.
  	 *
  	 * @param pId the id
  	 */
  	public void supprimerEmplacement(Long pId);
	
	  /**
  	 * Modifier emplacement.
  	 *
  	 * @param pEmplacementValue the emplacement value
  	 * @return the string
  	 */
  	public String modifierEmplacement(EmplacementValue pEmplacementValue);

	  /**
  	 * Recherche emplacement value par id.
  	 *
  	 * @param pEmplacementValue the emplacement value
  	 * @return the emplacement value
  	 */
  	public EmplacementValue rechercheEmplacementValueParId(EmplacementValue pEmplacementValue);

	  /**
  	 * Liste emplacement value.
  	 *
  	 * @return the list
  	 */
  	public List < EmplacementValue > listeEmplacementValue();
	  

}
