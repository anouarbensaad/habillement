package com.gpro.consulting.tiers.commun.service.test.impl;
import static org.junit.Assert.assertNotNull;
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

import com.gpro.consulting.tiers.commun.coordination.value.CategorieValue;
import com.gpro.consulting.tiers.commun.domaine.ICategoriePartieInteresseeDomaine;

@ContextConfiguration(locations = {"/spring/application-context.xml", "/spring/config-mt-gpro-commun-service-test.xml",
  "/spring/config-mt-gpro-commun-persistance.xml", "/spring/config-mt-gpro-commun-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class CategoriePartieInteresseeServiceTest {

  @Autowired
  private ICategoriePartieInteresseeDomaine categoriePartieInteresseeDomaine;
  /** La datasource. */
  @Autowired
  private DataSource dataSource;

  public ICategoriePartieInteresseeDomaine getADomaine() {
    return categoriePartieInteresseeDomaine;
  }
  // test si categorie partie interesse est ajouter
  @Test
  @DirtiesContext
  @Rollback(false)
  public void testCreereCategoriePartieInteressee() {

    // Creation d'un objet valeur Catégorie
   // CategorieValue categorie = new CategorieValue();
   // categorie.setDesignation("fournisseur");
    // Lancer la création

    // String vIdCategorieResulatant =
    // categoriePartieInteresseeDomaine.creerCategoriePartieInteressee(categorie);

    // Test et vérifiacation

    // assertNotNull(vIdCategorieResulatant);
  }
  // test si categorie partie interesse est supprimer
  @Test
  @DirtiesContext
  @Rollback(false)
  public void testSupprimerCategoriePartieInteressee() {

    // create objet valeur Catégorie
    //CategorieValue pCategoriePartieInteresseValue = new CategorieValue();
   // pCategoriePartieInteresseValue.setId(23L);
    // categoriePartieInteresseeDomaine.supprimerCategoriePartieInteressee(pCategoriePartieInteresseValue);

  }

  // test si categorie partie interesse est modifier
  @Test
  @DirtiesContext
  @Rollback(false)
  public void testModifierCategoriePartieInteressee() {

    // objet valeur Catégorie
   // CategorieValue pCategoriePartieInteresseValue = new CategorieValue();
   // pCategoriePartieInteresseValue.setId(24L);
   // pCategoriePartieInteresseValue.setDesignation("test");

    // modifier entity catigorie
    //String vIdCategorieResulatant = categoriePartieInteresseeDomaine.modifierCategoriePartieInteressee(pCategoriePartieInteresseValue);

    // verification si categorie est modifier
    //assertNotNull(vIdCategorieResulatant);

  }

  public ICategoriePartieInteresseeDomaine getCategoriePartieInteresseeDomaine() {
    return categoriePartieInteresseeDomaine;
  }

  public void setCategoriePartieInteresseeDomaine(ICategoriePartieInteresseeDomaine categoriePartieInteresseeDomaine) {
    this.categoriePartieInteresseeDomaine = categoriePartieInteresseeDomaine;
  }

  public DataSource getDataSource() {
    return dataSource;
  }

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }

}
