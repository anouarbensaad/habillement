package com.gpro.consulting.tiers.commun.service.test.impl;
import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
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

import com.gpro.consulting.tiers.commun.coordination.value.DocumentValue;
import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.RechercheMulticriterePartieInteresseeValue;
import com.gpro.consulting.tiers.commun.coordination.value.RepresentantValue;
import com.gpro.consulting.tiers.commun.coordination.value.ResultatRechechePartieInteresseeValue;
import com.gpro.consulting.tiers.commun.domaine.IPartieInteresseeDomaine;

@ContextConfiguration(locations = {"/spring/application-context.xml", 
  "/spring/config-mt-gpro-commun-service-test.xml",
  "/spring/config-mt-gpro-commun-persistance.xml", "/spring/config-mt-gpro-commun-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class PartieInteresseeServiceTest {
	
  //injection partieInteresseeDomaine
  @Autowired
  private IPartieInteresseeDomaine partieInteresseeDomaine;
  /** La datasource. */
  @Autowired
  private DataSource dataSource;
  // declaration des variables 
  DocumentValue documentValue = new DocumentValue();
  DocumentValue documentValue2 = new DocumentValue();
  RepresentantValue represantentValue1 = new RepresentantValue();
  RepresentantValue represantentValue2 = new RepresentantValue();
  PartieInteresseValue partieIntersse = new PartieInteresseValue();
  List < PartieInteresseValue > list ;
  RechercheMulticriterePartieInteresseeValue vRechercheMultiCritere = new RechercheMulticriterePartieInteresseeValue();

  // test si partie interesse est ajouter
  @Test
  @DirtiesContext
  @Rollback(false)
	public void testCreerePartieInteressee() {
		/******** creation list document ***************/
		documentValue.setPath("test");
		documentValue.setTypeDocument(1L);
		documentValue.setvIdPartieInteresse(1L);
		documentValue2.setPath("test2");
		documentValue2.setTypeDocument(1L);
		documentValue2.setvIdPartieInteresse(1L);
		Set<DocumentValue> documentValues = new HashSet<DocumentValue>();
		documentValues.add(documentValue);
		documentValues.add(documentValue2);
		/************* creation liste representant *********************/
		represantentValue1.setNom("mohamed");
		represantentValue1.setEmail("medameur5@gmail.com");
		represantentValue1.setFax("7325140145252");
		represantentValue1.setFonction("PDG");
		represantentValue1.setTelephone("7358475685");
		represantentValue2.setNom("ghazi");
		represantentValue2.setEmail("ghazi@gmail.com");
		represantentValue2.setFax("7325140145252");
		represantentValue2.setFonction("PDG");
		represantentValue2.setTelephone("7358475685");
		Set<RepresentantValue> represantentValues = new HashSet<RepresentantValue>();
		represantentValues.add(represantentValue1);
		represantentValues.add(represantentValue2);
		/************* Creation d'un objet valeur Partie Interesse *************/
		partieIntersse.setReference("PI Test  ");
		partieIntersse.setRaisonSociale("raison  ");
		partieIntersse.setAbreviation("test");
		partieIntersse.setActif(true);
		partieIntersse.setActivite("production ");
		partieIntersse.setAdresse("sousse");
		partieIntersse.setCategoriePartieInteressee(1L);
		partieIntersse.setCodeDouane("4025127");
		partieIntersse.setDateIntroduction(new GregorianCalendar(2013, 10, 28));
		partieIntersse.setEmail("email@gmail.com");
		partieIntersse.setFamillePartieInteressee(1L);
		partieIntersse.setFax("1472");
		partieIntersse.setMatriculeFiscal("matricule 1");
		partieIntersse.setRegimeCommercial("regime ");
		partieIntersse.setSitePartieInteressee(1L);
		partieIntersse.setTelephone("736512477412");
		partieIntersse.setTypePartieInteressee(1L);
		partieIntersse.setRepresentants(represantentValues);
		partieIntersse.setDocuments(documentValues);
		// Lancer la création
		String vIdPartieIntersseResulatant = partieInteresseeDomaine.creerPartieInteressee(partieIntersse);
		// Test et vérifiacation
		assertNotNull(vIdPartieIntersseResulatant);
		fail("erreur d'ajout    " + vIdPartieIntersseResulatant);
    }

	  // test partie interesse est supprimer
	  @Test
	  @DirtiesContext
	  @Rollback(false)
	  public void testSupprimerCategoriePartieInteressee() {
	    /************** lancer la suppression ****************/
	     partieInteresseeDomaine.supprimerPartieInteressee(3L);
	  }
	  
	/*************************************** test unitaire modification partie interesse ***********************************************/
	// test si partie interesse est modifier
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testModifierPartieInteressee() {
		/************* Creation d'un objet valeur Partie Interesse *************/
		partieIntersse.setId(5L);
		PartieInteresseValue partieIntersseModifier = partieInteresseeDomaine
				.recherchePartieInteresseeParId(partieIntersse);
		partieIntersseModifier.setId(2L);
		partieIntersseModifier.setReference("PI Test 12/08/2015 ");
		partieIntersseModifier.setRaisonSociale("raison  ");
		partieIntersseModifier.setAbreviation("test");
		partieIntersseModifier.setActif(true);
		partieIntersseModifier.setActivite("production ");
		partieIntersseModifier.setAdresse("sousse");
		partieIntersseModifier.setCategoriePartieInteressee(1L);
		partieIntersseModifier.setCodeDouane("4025127");
		partieIntersseModifier.setDateIntroduction(new GregorianCalendar(2013,
				10, 28));
		partieIntersseModifier.setEmail("email@gmail.com");
		partieIntersseModifier.setFamillePartieInteressee(1L);
		partieIntersseModifier.setFax("1472");
		partieIntersseModifier.setMatriculeFiscal("matricule 1");
		partieIntersseModifier.setRegimeCommercial("regime ");
		partieIntersseModifier.setSitePartieInteressee(1L);
		partieIntersseModifier.setTelephone("736512477412");
		partieIntersseModifier.setTypePartieInteressee(1L);
		// ajouter document
		DocumentValue nouveauDocument = new DocumentValue();
		nouveauDocument.setPath("c:/documentProduit.pdf");
		partieIntersseModifier.getDocuments().add(nouveauDocument);
		// ajouter document
		RepresentantValue nouveauRepresentant = new RepresentantValue();
		nouveauRepresentant.setEmail("12/08/2015@test.com");
		nouveauRepresentant.setFax("fax 12/08/2015");
		nouveauRepresentant.setFonction("representant 12/08/2015");
		nouveauRepresentant.setNom("representant 12/08/2015");
		partieIntersseModifier.getRepresentants().add(nouveauRepresentant);

		// modifier entity Partie Interesse
		String vIdPartieInterseResulatant = partieInteresseeDomaine
				.modifierPartieInteressee(partieIntersse);
		PartieInteresseValue partieInteresseVerifier = partieInteresseeDomaine
				.recherchePartieInteresseeParId(partieIntersse);
		// verification si Partie Intersse est modifier
		assertNotNull(vIdPartieInterseResulatant);
		assertNotNull(partieInteresseVerifier.getDocuments().size());
		assertNotNull(partieInteresseVerifier.getRepresentants().size());
		fail("erreur modification   " + vIdPartieInterseResulatant);
	}

	// test si partie interesse est modifier supprimer ligne representant et
	// ligne document
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testModifierPartieInteresseeSupprimerDocumentRepresentant() {
		/************* Creation d'un objet valeur Partie Interesse *************/
		partieIntersse.setId(5L);
		PartieInteresseValue partieIntersseModifier = partieInteresseeDomaine
				.recherchePartieInteresseeParId(partieIntersse);
		partieIntersseModifier.setId(2L);
		partieIntersseModifier
				.setReference("PI Test suppression document et represantant  ");
		partieIntersseModifier.setRaisonSociale("raison  ");
		partieIntersseModifier.setAbreviation("test");
		partieIntersseModifier.setActif(true);
		partieIntersseModifier.setActivite("production ");
		// supprimer document
		for (DocumentValue ducumentSupprimer : partieIntersseModifier
				.getDocuments()) {
			if (ducumentSupprimer.getPath().equals("12/08/2015@test.com")) {
				partieIntersseModifier.getDocuments().remove(ducumentSupprimer);
			}
		}
		// supprimer represantant
		for (RepresentantValue representantSupprimer : partieIntersseModifier
				.getRepresentants()) {
			if (representantSupprimer.getEmail().equals(
					"c:/documentProduit.pdf")) {
				partieIntersseModifier.getRepresentants().remove(
						representantSupprimer);
			}
		}

		// modifier entity Partie Interesse
		String vIdPartieInterseResulatant = partieInteresseeDomaine
				.modifierPartieInteressee(partieIntersse);
		PartieInteresseValue partieInteresseVerifier = partieInteresseeDomaine
				.recherchePartieInteresseeParId(partieIntersse);
		// verification si Partie Intersse est modifier
		assertNotNull(vIdPartieInterseResulatant);
		assertEquals(partieInteresseVerifier.getDocuments().size(), 1);
		assertEquals(partieInteresseVerifier.getRepresentants().size(), 1);
		fail("erreur modification   " + vIdPartieInterseResulatant);
	}

	// test si partie interesse est modifier supprimer ligne representant et
	// ligne document
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testModifierPartieInteresseeSupprimerToutDocument() {
		/************* Creation d'un objet valeur Partie Interesse *************/
		partieIntersse.setId(5L);
		PartieInteresseValue partieIntersseModifier = partieInteresseeDomaine
				.recherchePartieInteresseeParId(partieIntersse);
		partieIntersseModifier.setId(2L);
		partieIntersseModifier
				.setReference("PI Test suppression document et represantant  ");
		partieIntersseModifier.setRaisonSociale("raison  ");
		partieIntersseModifier.setAbreviation("test");
		partieIntersseModifier.setActif(true);
		partieIntersseModifier.setActivite("production ");
		// supprimer document
		partieIntersseModifier.setDocuments(null);
		// modifier entity Partie Interesse
		String vIdPartieInterseResulatant = partieInteresseeDomaine
				.modifierPartieInteressee(partieIntersse);
		PartieInteresseValue partieInteresseVerifier = partieInteresseeDomaine
				.recherchePartieInteresseeParId(partieIntersse);
		// verification si Partie Intersse est modifier
		assertNotNull(vIdPartieInterseResulatant);
		assertEquals(partieInteresseVerifier.getDocuments().size(), 0);
		fail("erreur modification   " + vIdPartieInterseResulatant);
	}

	// test si partie interesse est modifier supprimer ligne representant et
	// ligne document
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testModifierPartieInteresseeSupprimerToutRepresantant() {
		/************* Creation d'un objet valeur Partie Interesse *************/
		partieIntersse.setId(5L);
		PartieInteresseValue partieIntersseModifier = partieInteresseeDomaine
				.recherchePartieInteresseeParId(partieIntersse);
		partieIntersseModifier.setId(2L);
		partieIntersseModifier
				.setReference("PI Test suppression document et represantant  ");
		partieIntersseModifier.setRaisonSociale("raison  ");
		partieIntersseModifier.setAbreviation("test");
		partieIntersseModifier.setActif(true);
		partieIntersseModifier.setActivite("production ");
		// supprimer represantant
		partieIntersseModifier.setRepresentants(null);
		// modifier entity Partie Interesse
		String vIdPartieInterseResulatant = partieInteresseeDomaine
				.modifierPartieInteressee(partieIntersse);
		PartieInteresseValue partieInteresseVerifier = partieInteresseeDomaine
				.recherchePartieInteresseeParId(partieIntersse);
		// verification si Partie Intersse est modifier
		assertNotNull(vIdPartieInterseResulatant);
		assertEquals(partieInteresseVerifier.getDocuments().size(), 0);
		fail("erreur modification   " + vIdPartieInterseResulatant);
	}

	// test si partie interesse est modifier
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testModifierCaracteristiquePartieInteressee() {
		/************* Creation d'un objet valeur Partie Interesse *************/
		partieIntersse.setId(5L);
		PartieInteresseValue partieIntersseModifier = partieInteresseeDomaine
				.recherchePartieInteresseeParId(partieIntersse);
		partieIntersseModifier.setId(2L);
		partieIntersseModifier.setReference("PI Test 12/08/2015 ");
		partieIntersseModifier.setRaisonSociale("raison  ");
		partieIntersseModifier.setAbreviation("OBS");
		partieIntersseModifier.setActif(true);
		partieIntersseModifier.setActivite("production ");
		partieIntersseModifier.setAdresse("sousse");
		partieIntersseModifier.setCategoriePartieInteressee(1L);
		partieIntersseModifier.setCodeDouane("4025127");
		partieIntersseModifier.setDateIntroduction(new GregorianCalendar(2013,
				10, 28));
		partieIntersseModifier.setEmail("email@gmail.com");
		partieIntersseModifier.setFamillePartieInteressee(1L);
		partieIntersseModifier.setFax("1472");
		partieIntersseModifier.setMatriculeFiscal("matricule 1");
		partieIntersseModifier.setRegimeCommercial("regime ");
		partieIntersseModifier.setSitePartieInteressee(1L);
		partieIntersseModifier.setTelephone("736512477412");
		partieIntersseModifier.setTypePartieInteressee(1L);
		// modifier entity Partie Interesse
		String vIdPartieInterseResulatant = partieInteresseeDomaine
				.modifierPartieInteressee(partieIntersse);
		PartieInteresseValue partieInteresseVerifier = partieInteresseeDomaine
				.recherchePartieInteresseeParId(partieIntersse);
		// verification si Partie Intersse est modifier
		assertNotNull(vIdPartieInterseResulatant);
		assertEquals(partieInteresseVerifier.getAbreviation(), "OBS");
		assertEquals(partieInteresseVerifier.getActivite(), "production");
		assertEquals(partieInteresseVerifier.getAdresse(), "sousse");
		assertEquals(partieInteresseVerifier.getCategoriePartieInteressee(),
				"test");
		assertEquals(partieInteresseVerifier.getCodeDouane(), "4025127");
		assertEquals(partieInteresseVerifier.getEmail(), "email@gmail.com");
		assertEquals(partieInteresseVerifier.getMatriculeFiscal(),
				"matricule 1");
		assertEquals(partieInteresseVerifier.getRaisonSociale(), "raison");
		assertEquals(partieInteresseVerifier.getReference(), "reference 1");
		assertEquals(partieInteresseVerifier.getSitePartieInteressee()
				.longValue(), 1L);
		fail("erreur modification   " + vIdPartieInterseResulatant);
	}

	/*************************************** Fin test unitaire modification partie interesse ***********************************************/

	/*************************************** test unitaire test recherche multi critere partie interesse ***********************************************/

	// test recherche multi critere 4 critere
	@Test
	@DirtiesContext
	@Rollback(true)
	public void testRechercheMultiCriterePartieInteresseeQuatreCritere() {
		// Creation d'un objet valeur RechercheMulticriterePartieInteresseeValue
		vRechercheMultiCritere = new RechercheMulticriterePartieInteresseeValue();
		vRechercheMultiCritere.setvReference("Ref1");
		vRechercheMultiCritere.setvRaisonSociale("Entreprise");
		//vRechercheMultiCritere.setvFamillePartieInteressee("famille 1");
		ResultatRechechePartieInteresseeValue vResultatRecherche = partieInteresseeDomaine
				.rechercherPartieInteresseMultiCritere(vRechercheMultiCritere);
		// Test et vérifiacation
		assertNotNull(vResultatRecherche);
		assertTrue(vResultatRecherche.getNombreResultaRechercher().equals(2L));
		// assertFalse(vResultatRecherche.getNombreResultaRechercher().equals(1L));
	}

	// test recherche multi critere tous critere
	@Test
	@DirtiesContext
	@Rollback(true)
	public void testRechercheMultiCriterePartieInteresseeTousCritere() {
		// Creation d'un objet valeur RechercheMulticriterePartieInteresseeValue
		vRechercheMultiCritere = new RechercheMulticriterePartieInteresseeValue();
		vRechercheMultiCritere.setvReference("Ref1");
		vRechercheMultiCritere.setvRaisonSociale("Entreprise");
		//vRechercheMultiCritere.setvFamillePartieInteressee("famille 1");
		vRechercheMultiCritere.setActif("oui");
		vRechercheMultiCritere.setvActivite("production");
		//vRechercheMultiCritere.setvCategoriePartieInteressee("cathegorie ");
		ResultatRechechePartieInteresseeValue vResultatRecherche = partieInteresseeDomaine
				.rechercherPartieInteresseMultiCritere(vRechercheMultiCritere);
		// Test et vérifiacation
		assertNotNull(vResultatRecherche);
		assertTrue(vResultatRecherche.getNombreResultaRechercher().equals(1L));
		// assertFalse(vResultatRecherche.getNombreResultaRechercher().equals(1L));
	}

	// test unitaire paritie intersse non actif
	@Test
	@DirtiesContext
	@Rollback(true)
	public void testRechercheMultiCriterePartieInteresseeNonActif() {
		// Creation d'un objet valeur RechercheMulticriterePartieInteresseeValue
		vRechercheMultiCritere.setActif("non");
		//vRechercheMultiCritere.setvCategoriePartieInteressee("cathegorie ");
		ResultatRechechePartieInteresseeValue vResultatRecherche = partieInteresseeDomaine
				.rechercherPartieInteresseMultiCritere(vRechercheMultiCritere);
		// Test et vérifiacation
		assertNotNull(vResultatRecherche);
		assertTrue(vResultatRecherche.getNombreResultaRechercher().equals(1L));
		// assertFalse(vResultatRecherche.getNombreResultaRechercher().equals(1L));
	}

	// test unitaire paritie intersse actif
	@Test
	@DirtiesContext
	@Rollback(true)
	public void testRechercheMultiCriterePartieInteresseeActif() {
		// Creation d'un objet valeur RechercheMulticriterePartieInteresseeValue
		vRechercheMultiCritere.setActif("oui");
		//vRechercheMultiCritere.setvCategoriePartieInteressee("cathegorie ");
		ResultatRechechePartieInteresseeValue vResultatRecherche = partieInteresseeDomaine
				.rechercherPartieInteresseMultiCritere(vRechercheMultiCritere);
		// Test et vérifiacation
		assertNotNull(vResultatRecherche);
		assertTrue(vResultatRecherche.getNombreResultaRechercher().equals(1L));
		// assertFalse(vResultatRecherche.getNombreResultaRechercher().equals(1L));
	}

	/*************************************** Fin test unitaire test recherche multi critere partie interesse ***********************************************/

	  // test recherche d'une liste
	  @Test
	  @DirtiesContext
	  @Rollback(false)
	  public void testListerPartieInteressee() {
	    list = partieInteresseeDomaine.listePartieInteressee();
	    assertNotNull(list);
	   // assertNull(list);
	    fail("liste vide ");
	  }
	  public IPartieInteresseeDomaine getPartieInteresseeDomaine() {
	    return partieInteresseeDomaine;
	  }
	
	  public void setPartieInteresseeDomaine(IPartieInteresseeDomaine partieInteresseeDomaine) {
	    this.partieInteresseeDomaine = partieInteresseeDomaine;
	  }
	
	  public DataSource getDataSource() {
	    return dataSource;
	  }
	
	  public void setDataSource(DataSource dataSource) {
	    this.dataSource = dataSource;
	  }

}
