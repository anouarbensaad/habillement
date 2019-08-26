package com.gpro.consulting.tiers.commun.coordination.value;

import java.util.Set;
import java.util.TreeSet;

public class ResultatRechechePartieInteresseeValue {

  private Long nombreResultaRechercher;

  private Set<PartieInteresseValue> partieInteresseValues = new TreeSet<PartieInteresseValue>();

  /**
   * Accesseur en lecture de l'attribut <code>nombreResultaRechercher</code>.
   * 
   * @return Long L'attribut nombreResultaRechercher à lire.
   */
  public Long getNombreResultaRechercher() {
    return nombreResultaRechercher;
  }

  /**
   * Accesseur en écriture de l'attribut <code>nombreResultaRechercher</code>.
   *
   * @param nombreResultaRechercher
   *          L'attribut nombreResultaRechercher à modifier.
   */
  public void setNombreResultaRechercher(Long nombreResultaRechercher) {
    this.nombreResultaRechercher = nombreResultaRechercher;
  }

  /**
   * Accesseur en lecture de l'attribut <code>partieInteresseValues</code>.
   * 
   * @return List<PartieInteresseValue> L'attribut partieInteresseValues à lire.
   */
  public Set < PartieInteresseValue > getPartieInteresseValues() {
    return partieInteresseValues;
  }

  /**
   * Accesseur en écriture de l'attribut <code>partieInteresseValues</code>.
   *
   * @param partieInteresseValues
   *          L'attribut partieInteresseValues à modifier.
   */
  public void setPartieInteresseValues(Set < PartieInteresseValue > partieInteresseValues) {
    this.partieInteresseValues = partieInteresseValues;
  }

}
