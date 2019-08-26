package com.gpro.consulting.tiers.commun.domaine.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.RechercheMulticriterePartieInteresseeValue;
import com.gpro.consulting.tiers.commun.coordination.value.ResultatRechechePartieInteresseeValue;
import com.gpro.consulting.tiers.commun.domaine.IPartieInteresseeDomaine;
import com.gpro.consulting.tiers.commun.persistance.IPartieInteresseePersistance;

/**
 * The Class CategoriePartieInteresseeImpl.
 * 
 * @author $mohamed
 */
public class PartieInteresseeDomaineImpl implements IPartieInteresseeDomaine {

  @Autowired
  IPartieInteresseePersistance partieInteresseePersistance;

  /*
   * methode de creation Partie Interessée
   */
  @Override
  public String creerPartieInteressee(PartieInteresseValue pPartieInteresseValue) {
    return partieInteresseePersistance.creerPartieInteressee(pPartieInteresseValue);
  }

  /**************** supprimer partie interessee *******************/
  @Override
  public void supprimerPartieInteressee(Long pId) {
    partieInteresseePersistance.supprimerPartieInteressee(pId);
  }

  /**************** modifier partie interessee *******************/
  @Override
  public String modifierPartieInteressee(PartieInteresseValue pPartieInteresseValue) {
    return partieInteresseePersistance.modifierPartieInteressee(pPartieInteresseValue);
  }

  /**************** recherche partie interessee Par Id *******************/

  @Override
  public PartieInteresseValue recherchePartieInteresseeParId(PartieInteresseValue pPartieInteresseValue) {
    return partieInteresseePersistance.recherchePartieInteresseeParId(pPartieInteresseValue);
  }

  @Override
  public PartieInteresseValue getAbreviationClient(Long id) {
    return partieInteresseePersistance.getAbreviationClient(id);
  }

  
  /**************** liste partie interessee *******************/

  @Override
  public java.util.List < PartieInteresseValue > listePartieInteressee() {
	  
	  List<PartieInteresseValue> list = partieInteresseePersistance.listePartieInteressee();
	  Collections.sort(list);
	  
	  return list;
  }

  /********************** recherche multi critere partie interesse ***************************/
  @Override
  public ResultatRechechePartieInteresseeValue rechercherPartieInteresseMultiCritere(
    RechercheMulticriterePartieInteresseeValue pRecherchePartieInteresseMulitCritere) {
    return partieInteresseePersistance.rechercherPartieInteresseMultiCritere(pRecherchePartieInteresseMulitCritere);
  }

  public IPartieInteresseePersistance getPartieInteresseePersistance() {
    return partieInteresseePersistance;
  }

  public void setPartieInteresseePersistance(IPartieInteresseePersistance partieInteresseePersistance) {
    this.partieInteresseePersistance = partieInteresseePersistance;
  }

  @Override
  public List<PartieInteresseCacheValue> listePartieInteresseeCache() {
		
	  return partieInteresseePersistance.listePartieInteresseeCache();
  }

	@Override
	public PartieInteresseValue getById(Long id) {
		
		return partieInteresseePersistance.getById(id);
	}

}
