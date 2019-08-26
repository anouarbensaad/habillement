package com.gpro.consulting.tiers.commun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.CategorieValue;
import com.gpro.consulting.tiers.commun.domaine.ICategoriePartieInteresseeDomaine;
import com.gpro.consulting.tiers.commun.service.ICategoriePartieInteresseeService;

/**
 * The Class CategoriePartieInteresseeImpl.
 * 
 * @author $mohamed
 */
@Service
@Transactional
public class CategoriePartieInteresseeServiceImpl implements ICategoriePartieInteresseeService {
  @Autowired
  ICategoriePartieInteresseeDomaine categoriePartieInteresseeDomaine;

  /*
   * methode de creation cathegorie Partie Interessée
   */
  @Override
  public String creerCategoriePartieInteressee(CategorieValue pCategorieValue) {

    return categoriePartieInteresseeDomaine.creerCategoriePartieInteressee(pCategorieValue);
  }

  /******************* supprimer categorie partie interesse ************************/

  @Override
  public void supprimerCategoriePartieInteressee(Long pCategorieValue) {
    categoriePartieInteresseeDomaine.supprimerCategoriePartieInteressee(pCategorieValue);
  }

  /******************* modifier categorie partie interesse ************************/
  @Override
  public String modifierCategoriePartieInteressee(CategorieValue pCategorieValue) {
    return categoriePartieInteresseeDomaine.modifierCategoriePartieInteressee(pCategorieValue);
  }

  /******************* recherche categorie partie interesse par Id ************************/

  @Override
  public CategorieValue rechercheCategoriePartieInteresseeParId(CategorieValue pCategorieValue) {
    return categoriePartieInteresseeDomaine.rechercheCategoriePartieInteresseeParId(pCategorieValue);
  }

  @Override
  public List < CategorieValue > listeCategoriePartieInteressee() {
    return categoriePartieInteresseeDomaine.listeCategoriePartieInteressee();
  }

  public ICategoriePartieInteresseeDomaine getCategoriePartieInteresseeDomaine() {
    return categoriePartieInteresseeDomaine;
  }

  public void setCategoriePartieInteresseeDomaine(ICategoriePartieInteresseeDomaine categoriePartieInteresseeDomaine) {
    this.categoriePartieInteresseeDomaine = categoriePartieInteresseeDomaine;
  }

}
