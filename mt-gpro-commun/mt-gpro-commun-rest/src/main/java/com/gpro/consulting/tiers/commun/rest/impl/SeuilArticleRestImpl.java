package com.gpro.consulting.tiers.commun.rest.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.SeuilArticleValue;
import com.gpro.consulting.tiers.commun.service.ISeuilArticleService;

@Controller
@RequestMapping("/seuilArticle")
public class SeuilArticleRestImpl {

  @Autowired
  private ISeuilArticleService seuilArticleService;

  List < SeuilArticleValue > listeType = new ArrayList <>();

  /************* get SeuilArticle By ID *************/
  @RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody SeuilArticleValue getSeuilArticle(@PathVariable Long id) {
    return seuilArticleService.rechercheSeuilArticleParId(id);
  }

  /********** all Seuils ***********/
  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody List < SeuilArticleValue > viewAllType() {
    return this.listeType = seuilArticleService.listeSeuilArticle();
  }

  /********** Creer Seuils ***********/
  @RequestMapping(value = "/creerSeuilArticle", method = RequestMethod.POST)
  public @ResponseBody String creerSeuilArticle(@RequestBody SeuilArticleValue pSeuilArticleValue) {
    // transformation et transcodage des referenciel
    return seuilArticleService.creerSeuilArticle(pSeuilArticleValue);
  }

  /********** Modifier Seuil ***********/
  @RequestMapping(value = "/modifierSeuilArticle", method = RequestMethod.POST)
  public @ResponseBody String modifierSeuilArticle(@RequestBody SeuilArticleValue pSeuilArticleValue) {
    return this.seuilArticleService.modifierSeuilArticle(pSeuilArticleValue);
  }

  /********** Supprimer Seuil ***********/
  @RequestMapping(value = "/supprimerSeuilArticle:{id}", method = RequestMethod.DELETE)
  public void supprimerSeuilArticle(@PathVariable("id") String id) {
    Long idSupp = Long.parseLong(id);
    SeuilArticleValue color = new SeuilArticleValue();
    color.setId(idSupp);
    seuilArticleService.supprimerSeuilArticle(idSupp);

  }

  /**
   * Accesseur en lecture de l'attribut <code>seuilArticleService</code>.
   * 
   * @return ISeuilArticleService L'attribut seuilArticleService à lire.
   */
  public ISeuilArticleService getSeuilArticleService() {
    return seuilArticleService;
  }

  /**
   * Accesseur en écriture de l'attribut <code>seuilArticleService</code>.
   *
   * @param seuilArticleService
   *          L'attribut seuilArticleService à modifier.
   */
  public void setSeuilArticleService(ISeuilArticleService seuilArticleService) {
    this.seuilArticleService = seuilArticleService;
  }

  /**
   * Accesseur en lecture de l'attribut <code>listeType</code>.
   * 
   * @return List<SeuilArticleValue> L'attribut listeType à lire.
   */
  public List < SeuilArticleValue > getListeType() {
    return listeType;
  }

  /**
   * Accesseur en écriture de l'attribut <code>listeType</code>.
   *
   * @param listeType
   *          L'attribut listeType à modifier.
   */
  public void setListeType(List < SeuilArticleValue > listeType) {
    this.listeType = listeType;
  }

}
