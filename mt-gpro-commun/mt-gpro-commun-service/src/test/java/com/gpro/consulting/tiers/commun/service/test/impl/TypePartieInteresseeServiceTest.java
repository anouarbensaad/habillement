package com.gpro.consulting.tiers.commun.service.test.impl;

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
import com.gpro.consulting.tiers.commun.coordination.value.TypeValue;
import com.gpro.consulting.tiers.commun.domaine.ITypePartieInteresseeDomaine;

@ContextConfiguration(locations = {"/spring/application-context.xml", "/spring/config-mt-gpro-commun-service-test.xml",
  "/spring/config-mt-gpro-commun-persistance.xml", "/spring/config-mt-gpro-commun-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class TypePartieInteresseeServiceTest {

  @Autowired
  private ITypePartieInteresseeDomaine typePartieInteresseeDomaine;
  /** La datasource. */
  @Autowired
  private DataSource dataSource;

  // test si categorie partie interesse est ajouter
  @Test
  @DirtiesContext
  @Rollback(false)
  public void testCreereTypePartieInteressee() {

    // Creation d'un objet valeur type PI
    //TypeValue typePi = new TypeValue();
    // typePi.setId(2L);
    //typePi.setDesignation("textil");
    // Lancer la création

    // String vIdCategorieResulatant =
    // typePartieInteresseeDomaine.creerTypePartieInteressee(typePi);

    // Test et vérifiacation

    // assertNotNull(vIdCategorieResulatant);
  }

  public ITypePartieInteresseeDomaine getTypePartieInteresseeDomaine() {
    return typePartieInteresseeDomaine;
  }

  public void setTypePartieInteresseeDomaine(ITypePartieInteresseeDomaine typePartieInteresseeDomaine) {
    this.typePartieInteresseeDomaine = typePartieInteresseeDomaine;
  }

  public DataSource getDataSource() {
    return dataSource;
  }

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }

}
