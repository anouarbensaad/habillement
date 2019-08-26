package com.gpro.consulting.tiers.gs.service.test.impl;

import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gs.coordination.value.BonMouvementStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.MouvementStockValue;
import com.gpro.consulting.tiers.gs.domaine.IBonMouvementDomaine;

@ContextConfiguration(locations = {"/spring/application-context.xml", "/spring/config-ma-gpro-gs-service-test.xml",
  "/spring/config-ma-gpro-gs-persistance.xml", "/spring/config-ma-gpro-gs-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class BonMouvementServiceTest {
  @Autowired
  IBonMouvementDomaine bonMouvementDomaine;
  /** La datasource. */
  @Autowired
  private DataSource dataSource;

  // // test recherche multi critere
  // @Test
  // @DirtiesContext
  // @Rollback(false)
  // public void testRechercheMultiCritereMouvement() {
  // // Creation d'un objet valeur RechercheMulticritere
  // RechercheMulticritereBonMouvementStockValue vRechercheMultiCritere = new
  // RechercheMulticritereBonMouvementStockValue();
  // vRechercheMultiCritere.setValeurInf(1.0);
  // vRechercheMultiCritere.setValeurSupp(5.0);
  // // lancer recherche
  // ResultatRechecheBonMouvementStockValue vResultatRecherche =
  // bonMouvementDomaine.rechercherBonMouvementMultiCritere(vRechercheMultiCritere);
  // // Test et vérifiacation
  // assertNotNull(vResultatRecherche);
  // assertTrue(vResultatRecherche.getNombreResultaRechercher().equals(2L));
  // }
  //
  // test si magasin est ajouter
  @Test
  @DirtiesContext
  @Rollback(false)
  public void testCreatBonMouvement() {
    // creation mouvement

    MouvementStockValue mouvement1 = new MouvementStockValue();
    MouvementStockValue mouvement2 = new MouvementStockValue();

    mouvement1.setCones(2L);
    mouvement2.setCones(1L);
    mouvement1.setQuantiteReelle(300.0);
    mouvement1.setDescription("B171020151348");
    mouvement1.setTypeArticle(1L);
    mouvement2.setTypeArticle(1L);
    mouvement2.setQuantiteReelle(300.0);
    mouvement2.setDescription("B171020151348");
    mouvement1.setEntiteStock(300L);
    mouvement2.setEntiteStock(401L);

    Set < MouvementStockValue > mouvementValues = new HashSet < MouvementStockValue >();
    mouvementValues.add(mouvement1);
    mouvementValues.add(mouvement2);
    // creation bonmouvement
    BonMouvementStockValue bonMouvement = new BonMouvementStockValue();
    bonMouvement.setDescription("B171020151339");
    bonMouvement.setType("SORTIE");
    bonMouvement.setNumero("B171020151339");
    bonMouvement.setMouvements(mouvementValues);
    String vIdbonMouvementResultant = bonMouvementDomaine.creerBonMouvement(bonMouvement);
    // Test et vérifiacation
    assertNotNull(vIdbonMouvementResultant);
  }

  @Test
  @DirtiesContext
  @Rollback(false)
  public void testModifierBonMouvement() {
    // creation mouvement

    MouvementStockValue mouvement1 = new MouvementStockValue();
    MouvementStockValue mouvement2 = new MouvementStockValue();

    mouvement1.setCones(2L);
    mouvement2.setCones(1L);
    mouvement1.setQuantiteReelle(300.0);
    mouvement1.setDescription("B171020151348");
    mouvement1.setTypeArticle(1L);
    mouvement2.setTypeArticle(1L);
    mouvement2.setQuantiteReelle(5000.0);
    mouvement2.setDescription("MVT Ajoute avec ajout ES");
    mouvement2.setNouveau(true);
    mouvement1.setEntiteStock(300L);
   // mouvement2.setEntiteStock(401L);
    mouvement2.setIdArticle(68L);
    mouvement2.setIdMagasin(3L);

    Set < MouvementStockValue > mouvementValues = new HashSet < MouvementStockValue >();
   // mouvementValues.add(mouvement1);
   
    mouvementValues.add(mouvement2);
    // creation bonmouvement
    Long bonId=253150L;
    BonMouvementStockValue bonMouvement =bonMouvementDomaine.rechercheBonMouvementParId(bonId);
    bonMouvement.setDescription("BE Modifie");
  //  bonMouvement.setType("SORTIE");
    bonMouvement.setNumero(bonMouvement.getNumero()+"MODIF");
    Set<MouvementStockValue> listTemp=new HashSet<MouvementStockValue>();
    listTemp.addAll(bonMouvement.getMouvements());
  //  listTemp.remove()
    mouvementValues.addAll(listTemp);
    bonMouvement.setMouvements(mouvementValues);
    String vIdbonMouvementResultant = bonMouvementDomaine.modifierBonMouvement(bonMouvement);
    // Test et vérifiacation
    assertNotNull(vIdbonMouvementResultant);
  }
  
  @Test
  @DirtiesContext
  @Rollback(false)
  public void testModifierBonMouvementSort() {
    // creation mouvement

    MouvementStockValue mouvement1 = new MouvementStockValue();
    MouvementStockValue mouvement2 = new MouvementStockValue();

    mouvement1.setCones(2L);
    mouvement2.setCones(1L);
    mouvement1.setQuantiteReelle(300.0);
    mouvement1.setDescription("Bon de sortie modifie avec succes");
    mouvement1.setTypeArticle(1L);
    mouvement2.setTypeArticle(1L);
    mouvement2.setQuantiteReelle(10.0);
    mouvement2.setDescription("MVT Ajoute");
    mouvement2.setNouveau(true);
    mouvement1.setEntiteStock(300L);
    mouvement2.setEntiteStock(500L);
   
    Set < MouvementStockValue > mouvementValues = new HashSet < MouvementStockValue >();
   // mouvementValues.add(mouvement1);
   
    mouvementValues.add(mouvement2);
    // creation bonmouvement
    Long bonId=252950L;
    BonMouvementStockValue bonMouvement =bonMouvementDomaine.rechercheBonMouvementParId(bonId);
    bonMouvement.setDescription("BS Modifie");
  //  bonMouvement.setType("SORTIE");
    bonMouvement.setNumero(bonMouvement.getNumero()+"MODIF");
    Set<MouvementStockValue> listTemp=new HashSet<MouvementStockValue>();
    listTemp.addAll(bonMouvement.getMouvements());
  //  listTemp.remove()
    mouvementValues.addAll(listTemp);
    bonMouvement.setMouvements(mouvementValues);
    String vIdbonMouvementResultant = bonMouvementDomaine.modifierBonMouvement(bonMouvement);
    // Test et vérifiacation
    assertNotNull(vIdbonMouvementResultant);
  }
  
  
  
  public IBonMouvementDomaine getBonMouvementDomaine() {
    return bonMouvementDomaine;
  }

  public void setBonMouvementDomaine(IBonMouvementDomaine bonMouvementDomaine) {
    this.bonMouvementDomaine = bonMouvementDomaine;
  }

  public DataSource getDataSource() {
    return dataSource;
  }

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }

}
